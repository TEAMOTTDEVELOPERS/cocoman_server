package orangetaxiteam.cocoman.domain;

import java.util.Map;

public class SocialInfoServiceSupplier {
    private final Map<SocialProvider, SocialInfoService> map;

    public SocialInfoServiceSupplier(Map<SocialProvider, SocialInfoService> map) {
        this.map = map;
    }

    public SocialInfoService supply(SocialProvider socialProvider) {
        return this.map.get(socialProvider);
    }
}
