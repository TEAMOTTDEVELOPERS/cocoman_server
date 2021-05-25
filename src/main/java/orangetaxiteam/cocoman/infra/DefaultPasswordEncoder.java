package orangetaxiteam.cocoman.infra;

import orangetaxiteam.cocoman.domain.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultPasswordEncoder implements PasswordEncoder {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encode(String var1) {
        return bCryptPasswordEncoder.encode(var1);
    }

    @Override
    public boolean matches(String var1, String var2) {
        if (bCryptPasswordEncoder.matches(var1,var2)) return true;
        else return false;
    }
}
