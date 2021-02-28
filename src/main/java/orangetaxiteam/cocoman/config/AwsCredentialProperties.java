package orangetaxiteam.cocoman.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AwsCredentialProperties {
    public static final String AWS_CREDENTIAL_PROPERTIES_PREFIX = "cloud.aws.credentials";

    private String accessKey;
    private String secretKey;
}
