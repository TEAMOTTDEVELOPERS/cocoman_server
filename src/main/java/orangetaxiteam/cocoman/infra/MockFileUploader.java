package orangetaxiteam.cocoman.infra;

import orangetaxiteam.cocoman.domain.FileUploader;
import org.springframework.web.multipart.MultipartFile;

public class MockFileUploader implements FileUploader {
    @Override
    public String upload(MultipartFile multipartFile, String dirName) {
        return null;
    }
}
