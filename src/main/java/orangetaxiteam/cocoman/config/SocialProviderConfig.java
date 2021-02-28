package orangetaxiteam.cocoman.config;

import orangetaxiteam.cocoman.domain.SocialInfoService;
import orangetaxiteam.cocoman.domain.SocialInfoServiceSupplier;
import orangetaxiteam.cocoman.domain.SocialProvider;
import orangetaxiteam.cocoman.infra.GoogleSocialInfoService;
import orangetaxiteam.cocoman.infra.KakaoSocialInfoService;
import orangetaxiteam.cocoman.infra.NaverSocialInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SocialProviderConfig {
    @Bean
    public SocialInfoServiceSupplier socialInfoServiceSupplier() {
        Map<SocialProvider, SocialInfoService> map = new HashMap<>();
        map.put(SocialProvider.GOOGLE, new GoogleSocialInfoService());
        map.put(SocialProvider.KAKAO, new KakaoSocialInfoService());
        map.put(SocialProvider.NAVER, new NaverSocialInfoService());
        return new SocialInfoServiceSupplier(map);
    }
}
