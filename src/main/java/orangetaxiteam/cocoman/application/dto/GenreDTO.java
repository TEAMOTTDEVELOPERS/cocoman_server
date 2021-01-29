package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Genre;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {
    private Long id;
    private String name;

    public static GenreDTO fromDAO(Genre genre){
        GenreDTO v = new GenreDTO();
        v.id = genre.getId();
        v.name = genre.getName();

        return v;
    }
}