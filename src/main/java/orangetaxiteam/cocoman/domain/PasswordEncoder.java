package orangetaxiteam.cocoman.domain;

public interface PasswordEncoder {
    public String encode(CharSequence var1);

    public boolean matches(CharSequence var1, String var2);
}
