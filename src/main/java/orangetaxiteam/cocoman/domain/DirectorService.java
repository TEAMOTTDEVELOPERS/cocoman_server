package orangetaxiteam.cocoman.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DirectorService {
    private DirectorRepository directorRepository;

    @Autowired
    public DirectorService(DirectorRepository directorRepository){
        this.directorRepository = directorRepository;
    }

    public Optional<Director> findById(Long id){
        return directorRepository.findById(id);
    }

    public Director create(String name, String imagePath) {
        return directorRepository.save(new Director(name, imagePath));
    }
}
