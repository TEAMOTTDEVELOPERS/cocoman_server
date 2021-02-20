package orangetaxiteam.cocoman.infra;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import orangetaxiteam.cocoman.domain.FileUploader;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import orangetaxiteam.cocoman.domain.exceptions.InternalServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Slf4j
public class S3Uploader implements FileUploader {
    private AmazonS3Client amazonS3Client;
    private String tempFilePath;
    private String bucket;

    public S3Uploader(AmazonS3Client amazonS3Client, String tempFilePath, String bucket) {
        this.amazonS3Client = amazonS3Client;
        this.tempFilePath = tempFilePath;
        this.bucket = bucket;
    }

    @Override
    public String upload(MultipartFile multipartFile, String dirName) {
        File convertedFile = this.convert(multipartFile);

        return this.upload(convertedFile, dirName);
    }

    private String upload(File uploadFile, String dirName) {
        String fileName = dirName + "/" + uploadFile.getName();
        String uploadUrl = this.putS3(uploadFile, fileName);
        this.removeTempFile(uploadFile);

        return uploadUrl;
    }

    private File convert(MultipartFile multipartFile) {
        File convertFile = new File(this.tempFilePath + multipartFile.getOriginalFilename());

        try {
            if (!convertFile.createNewFile()) {
                throw new InternalServerErrorException(
                        ErrorCode.INTERNAL_SERVER,
                        String.format("Failed converting image file : %s", multipartFile.getName())
                );
            }

            FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
            fileOutputStream.write(multipartFile.getBytes());
        } catch (Exception e) {
            throw new InternalServerErrorException(
                    ErrorCode.INTERNAL_SERVER,
                    String.format("Failed converting image file : %s", multipartFile.getName())
            );
        }
        return convertFile;
    }

    private String putS3(File uploadFile, String fileName) {
        try {
            this.amazonS3Client.putObject(new PutObjectRequest(this.bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            this.removeTempFile(uploadFile);
            throw new InternalServerErrorException(
                    ErrorCode.INTERNAL_SERVER,
                    "Unauthenticated bucket access key"
            );
        }

        return this.amazonS3Client.getUrl(this.bucket, fileName).toString();
    }

    private void removeTempFile(File targetFile) {
        if (!targetFile.delete()) {
            S3Uploader.log.error(String.format("Failed deleting file : %s", targetFile.getName()));
        }
    }
}
