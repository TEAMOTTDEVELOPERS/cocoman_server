package orangetaxiteam.cocoman.config;

import orangetaxiteam.cocoman.domain.ImageStorageService;
import orangetaxiteam.cocoman.infra.AwsS3ImageStorageService;
import orangetaxiteam.cocoman.infra.GcpImageStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class CocomanConfig {
    @Bean
    public ImageStorageService awsS3ImageStorageService() {
        return new AwsS3ImageStorageService();
    }

    @Bean
    @Primary
    @Profile({"gcp"})
    public ImageStorageService gcpImageStorageService() {
        return new GcpImageStorageService();
    }
}
