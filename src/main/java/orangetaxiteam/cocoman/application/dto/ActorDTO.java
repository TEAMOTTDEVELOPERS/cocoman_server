package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import orangetaxiteam.cocoman.domain.Actor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActorDTO {
    private String id;
    private String name;
    private String imagePath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ActorDTO from(Actor actor){
        ActorDTO v = new ActorDTO();
        v.id = actor.getId();
        v.name = actor.getName();
        v.imagePath = actor.getImagePath();
        v.createdAt = actor.getCreatedAt();
        v.updatedAt = actor.getUpdatedAt();

        return v;
    }
}
