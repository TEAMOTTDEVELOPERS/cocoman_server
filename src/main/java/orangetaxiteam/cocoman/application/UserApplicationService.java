package orangetaxiteam.cocoman.application;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
>>>>>>> user id duplicate chek
import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserSignInDTO;
import orangetaxiteam.cocoman.application.dto.UserUpdateRequestDTO;
import orangetaxiteam.cocoman.config.JwtTokenProvider;
import orangetaxiteam.cocoman.domain.PasswordValidator;
<<<<<<< HEAD
import orangetaxiteam.cocoman.domain.SocialInfoService;
import orangetaxiteam.cocoman.domain.SocialInfoServiceSupplier;
import orangetaxiteam.cocoman.domain.SocialProvider;
=======
>>>>>>> user id duplicate chek
import orangetaxiteam.cocoman.domain.User;
import orangetaxiteam.cocoman.domain.UserRepository;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
=======
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> user id duplicate chek
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
<<<<<<< HEAD
=======
        } else {
            String userId = this.getSocialId(userCreateRequestDTO.getAccessToken(), provider);
            User user = User.social(
                    userId,
                    userCreateRequestDTO.getNickName(),
                    userCreateRequestDTO.getAge(),
                    userCreateRequestDTO.getGender(),
                    userCreateRequestDTO.getPhoneNum(),
                    userCreateRequestDTO.getProfileImg(),
                    userCreateRequestDTO.getPushToken()
            );
            return UserDTO.from(this.userRepository.save(user));
>>>>>>> user id duplicate chek
        }

<<<<<<< HEAD
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

=======
    public UserDTO signIn(UserSignInDTO userSignInDTO) {
        User user = this.userRepository.findByUserId(userSignInDTO.getUserId()).orElseThrow(
                () -> new BadRequestException(
                        ErrorCode.ID_DOES_NOT_EXIST,
                        "Invalid user id")
        );

        this.signInValidator.validate(
                user,
                userSignInDTO.getPassword(),
                userSignInDTO.getAccessToken()
        );
        String jwtToken = this.jwtTokenProvider.createToken(user.getUserId());
        return UserDTO.from(user, jwtToken);
    }


    public String getSocialId(String accessToken, String provider) throws JsonProcessingException {

        if (provider.equals("facebook")) {
            return "FACEBOOK_" + this.getFacebookIdInfo(accessToken);
        } else if (provider.equals("google")) {
            return "GOOGLE_" + this.getGoogleIdInfo(accessToken);
        } else if (provider.equals("kakao")) {
            return "KAKAO_" + this.getKakaoIdInfo(accessToken);
        } else if (provider.equals("naver")) {
            return "NAVER_" + this.getNaverIdInfo(accessToken);
        } else {
            return null;    // TODO : exception handling
        }
    }

    // TODO : to modify url, httpmethod, response
    private String getFacebookIdInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, String>> profileRequest = new HttpEntity<>(headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://www.facebook.com/~",
                HttpMethod.GET,
                profileRequest,
                String.class
        );

        return this.getIdFromResponse(response, "facebook");
    }

    private String getGoogleIdInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, String>> profileRequest = new HttpEntity<>(headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://www.googleapis.com/auth/userinfo",
                HttpMethod.GET,
                profileRequest,
                String.class
        );

        return this.getIdFromResponse(response, "google");
    }

    private String getKakaoIdInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> profileRequest = new HttpEntity<>(headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                profileRequest,
                String.class
        );

        return this.getIdFromResponse(response, "kakao");
    }

    private String getNaverIdInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, String>> profileRequest = new HttpEntity<>(headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.GET,
                profileRequest,
                String.class
        );

        return this.getIdFromResponse(response, "naver");
    }

    private String getIdFromResponse(ResponseEntity<String> response, String provider) throws JsonProcessingException {
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.readValue(response.getBody(), ObjectNode.class);
            if (provider.equals("naver")) {
                JsonNode res = node.get("response");
                return res.get("id").toString();
            } else {
                return node.get("id").asText();
            }
        }
        // TODO : status code exception handling
        else {    // statusCode.isError()
            return null;
        }
>>>>>>> user id duplicate chek
    }

    public UserDTO findById(String id) {
        return UserDTO.from(this.userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorCode.ROW_DOES_NOT_EXIST, "Invalid user id")));
    }

    public UserDTO updateUser(String id, UserUpdateRequestDTO userUpdateRequestDTO) {
        User user = this.userRepository.findById(id)
<<<<<<< HEAD
                .orElseThrow(() -> new BadRequestException(ErrorCode.ROW_DOES_NOT_EXIST, "Invalid user id"));
=======
                .orElseThrow(() -> new BadRequestException(ErrorCode.ID_DOES_NOT_EXIST, "Invalid user id"));
>>>>>>> user id duplicate chek
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
<<<<<<< HEAD
                .orElseThrow(() -> new BadRequestException(ErrorCode.ROW_DOES_NOT_EXIST, "Invalid user id"));
        this.userRepository.delete(user);
=======
                .orElseThrow(() -> new BadRequestException(ErrorCode.ID_DOES_NOT_EXIST, "Invalid user id"));
        this.userRepository.delete(user);
    }

    public void existsByUserId(String userId) {
        if (this.userRepository.existsByUserId(userId)) {
            throw new BadRequestException(ErrorCode.ID_ALREADY_EXIST, "user id already exists");
        }
>>>>>>> user id duplicate chek
    }
}
