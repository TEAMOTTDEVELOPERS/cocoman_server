package orangetaxiteam.cocoman.domain;

public interface PasswordValidator {
    public void validate(User user, String password, String accessToken);
}
