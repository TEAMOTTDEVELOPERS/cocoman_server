package orangetaxiteam.cocoman.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import orangetaxiteam.cocoman.domain.RatePlan;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RatePlanDTO {
    private String id;
    private String name;
    private String cost;
    private String resolution;
    private Integer numOfScreens;
    private String ottId;

    public static RatePlanDTO from(RatePlan ratePlan){
        RatePlanDTO ratePlanDTO = new RatePlanDTO();
        ratePlanDTO.id = ratePlan.getId();
        ratePlanDTO.name = ratePlan.getName();
        ratePlanDTO.cost = ratePlan.getCost();
        ratePlanDTO.resolution = ratePlan.getResolution();
        ratePlanDTO.numOfScreens = ratePlan.getNumOfScreens();
        ratePlanDTO.ottId = ratePlan.getOtt().getId();

        return ratePlanDTO;
    }
}
