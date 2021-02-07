package orangetaxiteam.cocoman.domain;

import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String userId, String nickName, String rawPassword, int age, Gender gender, String phoneNum, String profileImg, String pushToken) throws Exception {
        //username unique check
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            throw new Exception();//Exception 수정 필요
        }
        String password = new BCryptPasswordEncoder().encode(rawPassword);
        return userRepository.save(User.of(
                userId,
                nickName,
                password,
                age,
                gender,
                phoneNum,
                profileImg,
                pushToken
        ));
    }

    // TODO : implement
    public User createWithSocial(String provider, String uid, int age, Gender gender) {
        throw new NotYetImplementedException();
    }

    // TODO : exception handling
    public User signIn(String userId, String password) {
        User user = userRepository.findByUserId(userId);
        if (user == null) ;
            //throw new Exception();
        else if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
        }
        return user;
    }

    public User findByUserId(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new BadRequestException("invalid user id"));
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new BadRequestException("invalid user id"));
    }
}