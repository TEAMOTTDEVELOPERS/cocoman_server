package orangetaxiteam.cocoman.application;

import orangetaxiteam.cocoman.application.dto.ContentsCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.ContentsDTO;
import orangetaxiteam.cocoman.domain.Contents;
import orangetaxiteam.cocoman.domain.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentsApplicationService {
    @Autowired
    ContentsService contentsService;

    public ContentsDTO create(ContentsCreateRequestDTO contentsCreateRequestDTO) {
        return ContentsDTO.FromDao(
                contentsService.create(
                        contentsCreateRequestDTO.getTitle()
                )
        );
    }

    public List<ContentsDTO> findAll(){
        return contentsService.findAll()
                .stream()
                .map(ContentsDTO::FromDao)
                .collect(Collectors.toList());
    }
}
