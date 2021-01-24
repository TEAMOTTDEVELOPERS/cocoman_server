package orangetaxiteam.cocoman.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ActorService {
    private ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }

    public Optional<Actor> findById(Long id){
        return actorRepository.findById(id);
    }

    public Actor create(String name, String imagePath) {
        return actorRepository.save(new Actor(name, imagePath));
    }
}
