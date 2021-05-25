package orangetaxiteam.cocoman.domain;

import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import orangetaxiteam.cocoman.infra.DefaultPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultPasswordValidator implements PasswordValidator {
    private DefaultPasswordEncoder defaultPasswordEncoder = new DefaultPasswordEncoder();

    @Override
    public void validate(User user, String password) {
        if (!defaultPasswordEncoder.matches(password,user.getPassword())) {
            throw new BadRequestException(
                    ErrorCode.SIGNIN_PASSWORD_DOES_NOT_MATCH,
                    "Password does not match");
        }
    }
}
