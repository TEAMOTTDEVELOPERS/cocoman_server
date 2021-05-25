package orangetaxiteam.cocoman.domain;

import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultPasswordValidator implements PasswordValidator {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void validate(User user, String password) {
        if (!passwordEncoder.matches(password,user.getPassword())) {
            throw new BadRequestException(
                    ErrorCode.SIGNIN_PASSWORD_DOES_NOT_MATCH,
                    "Password does not match");
        }
    }
}
