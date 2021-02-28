package orangetaxiteam.cocoman.application;

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
            User user = this.userRepository.findByEmailOrElseThrow(userSignInDTO.getEmail());
            this.passwordValidator.validate(
                    user,
                    userSignInDTO.getPassword()
            );
            String jwtToken = this.jwtTokenProvider.createToken(user.getEmail());
            return UserDTO.from(user, jwtToken);
        }

        SocialInfoService socialInfoService = this.socialInfoServiceSupplier.supply(provider);
        String socialId = socialInfoService.getSocialId(userSignInDTO.getAccessToken());
        User user = this.userRepository.findBySocialIdOrElseThrow(socialId);
        String jwtToken = this.jwtTokenProvider.createToken(user.getSocialId());
        return UserDTO.from(user, jwtToken);

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
}
