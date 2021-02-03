package orangetaxiteam.cocoman.domain;

public interface ImageStorageService {
    public String upload(String imageBinary);

    public String download(String imagePath);
}
