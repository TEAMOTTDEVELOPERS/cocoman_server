package orangetaxiteam.cocoman.domain;


import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.web.exceptions.InputValueValidationException;
import org.hibernate.cfg.NotYetImplementedException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import orangetaxiteam.cocoman.web.exceptions.InputValueValidationException;

@Service

public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String userId, String nickName, String rawPassword, int age, String gender, String phoneNum, String profileImg, String pushToken) throws Exception {
        //username unique check
        User user = userRepository.findByUserId(userId);
        if (user != null) {
            throw new Exception();//Exception 수정 필요
        }
        String password = new BCryptPasswordEncoder().encode(rawPassword);
        return userRepository.save(new User(
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
    public User createWithSocial(String provider, String uid, int age, String gender){
        throw new NotYetImplementedException();
    }

    // TODO : exception handling
    public User signIn(String userId, String password){
        User user = userRepository.findByUserId(userId);
        if(user == null);
            //throw new Exception();
        else if(!new BCryptPasswordEncoder().matches(password, user.getPassword())) {}
        return user;
    }

    public User findByUserId(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new InputValueValidationException("invalid user id"));
    }

    public User findById(String id){
        return userRepository.findById(id).orElseThrow(() -> new InputValueValidationException("invalid user id"));
        
        
    }
    
    public User update(Long id, List<String> roles, int age, String gender, String phoneNum, String profileImg, String pushToken) {
		User user = findById(id).orElseThrow(() -> new InputValueValidationException("invalid user id"));
		return userRepository.save(User.builder()
									.id(id)
									.username(user.getUsername())
									.password(user.getPassword())
									.roles(roles)
									.age(age)
									.gender(gender)
									.phoneNum(phoneNum)
									.profileImg(profileImg)
									.pushToken(pushToken)
									.build());
	}
	
	public void delete(Long id) {
		findById(id).orElseThrow(() -> new InputValueValidationException("invalid user id"));
		userRepository.deleteById(id);
	}

}
