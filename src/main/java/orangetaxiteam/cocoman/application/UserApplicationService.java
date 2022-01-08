package orangetaxiteam.cocoman.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserSignInDTO;
import orangetaxiteam.cocoman.application.dto.UserUpdateRequestDTO;
import orangetaxiteam.cocoman.config.JwtTokenProvider;
import orangetaxiteam.cocoman.domain.PasswordValidator;
import orangetaxiteam.cocoman.domain.SocialInfoService;
import orangetaxiteam.cocoman.domain.SocialInfoServiceSupplier;
import orangetaxiteam.cocoman.domain.SocialProvider;
import orangetaxiteam.cocoman.domain.User;
import orangetaxiteam.cocoman.domain.UserRepository;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserApplicationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordValidator passwordValidator;
    private final SocialInfoServiceSupplier socialInfoServiceSupplier;

    @Autowired
    public UserApplicationService(
            UserRepository userRepository,
            JwtTokenProvider jwtTokenProvider,
            PasswordValidator passwordValidator,
            SocialInfoServiceSupplier socialInfoServiceSupplier
    ) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordValidator = passwordValidator;
        this.socialInfoServiceSupplier = socialInfoServiceSupplier;
    }

    @Transactional
    public UserDTO create(UserCreateRequestDTO userCreateRequestDTO) {
        // TODO : id duplication check
        SocialProvider provider = userCreateRequestDTO.getProvider();
        if (provider == SocialProvider.COCONUT) {
            User user = User.of(
                    userCreateRequestDTO.getUserId(),
                    userCreateRequestDTO.getNickName(),
                    userCreateRequestDTO.getPassword(),
                    userCreateRequestDTO.getAge(),
                    userCreateRequestDTO.getGender(),
                    userCreateRequestDTO.getPhoneNum(),
                    userCreateRequestDTO.getProfileImg(),
                    userCreateRequestDTO.getPushToken()
            );
            return UserDTO.from(this.userRepository.save(user));
        }

        SocialInfoService socialInfoService = this.socialInfoServiceSupplier.supply(provider);
        String socialId = socialInfoService.getSocialId(userCreateRequestDTO.getAccessToken());

        User user = User.social(
                socialId,
                userCreateRequestDTO.getNickName(),
                userCreateRequestDTO.getAge(),
                userCreateRequestDTO.getGender(),
                userCreateRequestDTO.getPhoneNum(),
                userCreateRequestDTO.getProfileImg(),
                userCreateRequestDTO.getPushToken()
        );
        return UserDTO.from(this.userRepository.save(user));

    }

    public UserDTO signIn(UserSignInDTO userSignInDTO) {
        SocialProvider provider = userSignInDTO.getProvider();

        if (provider == SocialProvider.COCONUT) {
            User user = this.userRepository.findByUserId(userSignInDTO.getUserId()).orElseThrow(
                    () -> new BadRequestException(ErrorCode.SIGNIN_DATA_DOES_NOT_MATCH, "invalid user data")
            );
            this.passwordValidator.validate(
                    user,
                    userSignInDTO.getPassword()
            );

            return UserDTO.from(
                    user,
                    this.jwtTokenProvider.createToken(user.getUserId())
            );
        }
        SocialInfoService socialInfoService = this.socialInfoServiceSupplier.supply(provider);
        String socialId = socialInfoService.getSocialId(userSignInDTO.getAccessToken());

        User user = this.userRepository.findByUserId(socialId).orElseThrow(
                () -> new BadRequestException(ErrorCode.SIGNIN_DATA_DOES_NOT_MATCH, "invalid user data")
        );

        return UserDTO.from(
                user,
                this.jwtTokenProvider.createToken(user.getUserId())
        );
    }

    public UserDTO findById(String id) {
        return UserDTO.from(this.userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorCode.ROW_DOES_NOT_EXIST, "Invalid user id")));
    }

    public UserDTO updateUser(String id, UserUpdateRequestDTO userUpdateRequestDTO) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorCode.ROW_DOES_NOT_EXIST, "Invalid user id"));
        user.update(
                userUpdateRequestDTO.getAge(),
                userUpdateRequestDTO.getGender(),
                userUpdateRequestDTO.getPhoneNum(),
                userUpdateRequestDTO.getProfileImg()
        );
        return UserDTO.from(this.userRepository.save(user));
    }

    public void deleteUser(String id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorCode.ROW_DOES_NOT_EXIST, "Invalid user id"));
        this.userRepository.delete(user);
    }

    public void validateUserId(String userId) {
        if (this.userRepository.existsByUserId(userId)) {
            throw new BadRequestException(ErrorCode.ID_ALREADY_EXIST, "user id already exists");
        }
    }
}
