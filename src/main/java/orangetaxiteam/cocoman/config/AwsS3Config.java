package orangetaxiteam.cocoman.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import orangetaxiteam.cocoman.domain.FileUploader;
import orangetaxiteam.cocoman.infra.MockFileUploader;
import orangetaxiteam.cocoman.infra.S3Uploader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class AwsS3Config {
    private final static String TEMP_FILE_PATH = "src/main/resources/";

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    @ConfigurationProperties(AwsCredentialProperties.AWS_CREDENTIAL_PROPERTIES_PREFIX)
    public AwsCredentialProperties awsCredentialProperties() {
        return new AwsCredentialProperties();
    }

    @Bean
    @Profile({"local"})
    public FileUploader mockFileUploader() {
        return new MockFileUploader();
    }

    @Bean
    @Profile({"dev, prod"})
    public BasicAWSCredentials basicAWSCredentials(
            AwsCredentialProperties credentialProperties
    ) {
        return new BasicAWSCredentials(
                credentialProperties.getAccessKey(),
                credentialProperties.getSecretKey()
        );
    }

    @Bean
    @Profile({"dev, prod"})
    public FileUploader s3Uploader(AWSCredentials awsCredentials) {
        AmazonS3Client amazonS3Client = new AmazonS3Client(awsCredentials);
        amazonS3Client.setRegion(Region.getRegion(Regions.fromName(this.region)));

        return new S3Uploader(
                amazonS3Client,
                TEMP_FILE_PATH,
                this.bucket
        );
    }
}
