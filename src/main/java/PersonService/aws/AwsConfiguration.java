package PersonService.aws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class AwsConfiguration {

    private String keyId = "YCAJEStU_FVlizdLyCEEBqdgD";

    private String secret = "YCNNNq9Ak-P1I58T_S81SCjMqjAZolNIORVRtd17";

    @Bean
    public S3Client s3Client(){
        Region region = Region.US_WEST_2;
        AwsCredentials awsCredentials = AwsBasicCredentials.create(keyId, secret);
        S3Client s3 = S3Client.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .endpointOverride(URI.create("https://storage.yandexcloud.net/"))
                .build();
        return s3;
    }
}
