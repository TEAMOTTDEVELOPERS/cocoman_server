package orangetaxiteam.cocoman.domain;

import org.springframework.stereotype.Component;

@Component
public class DefaultPasswordValidator implements PasswordValidator {
    @Override
    public void validate(User user, String password, String accessToken) {
        // TODO: implement me
    }
}
