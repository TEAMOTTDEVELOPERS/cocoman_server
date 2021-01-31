package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Genre;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static GenreDTO from(Genre genre){
        GenreDTO v = new GenreDTO();
        v.id = genre.getId();
        v.name = genre.getName();
        v.createdAt = genre.getCreatedAt();
        v.updatedAt = genre.getUpdatedAt();

        return v;
    }
}