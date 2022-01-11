package orangetaxiteam.cocoman.domain;

public interface PasswordEncoder {
    public String encode(String var1);

    public boolean matches(String var1, String var2);
}
