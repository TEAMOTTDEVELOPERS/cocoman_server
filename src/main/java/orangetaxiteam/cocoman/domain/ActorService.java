package orangetaxiteam.cocoman.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    private ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Optional<Actor> findById(String id) {
        return actorRepository.findById(id);
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public Actor create(String name, String imagePath) {
        return actorRepository.save(Actor.of(name, imagePath));
    }
}
