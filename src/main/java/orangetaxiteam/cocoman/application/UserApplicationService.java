package orangetaxiteam.cocoman.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import orangetaxiteam.cocoman.application.dto.CreateProfileRequest;
import orangetaxiteam.cocoman.application.dto.ProfileDTO;
import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserSignInDTO;
import orangetaxiteam.cocoman.config.JwtTokenProvider;
import orangetaxiteam.cocoman.domain.ImageStorageService;
import orangetaxiteam.cocoman.domain.User;
import orangetaxiteam.cocoman.domain.UserProfile;
import orangetaxiteam.cocoman.domain.UserProfileRepository;
import orangetaxiteam.cocoman.domain.UserRepository;
import orangetaxiteam.cocoman.domain.UserService;
import orangetaxiteam.cocoman.domain.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class UserApplicationService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final ImageStorageService imageStorageService;

    @Autowired
    public UserApplicationService(
            UserService userService,
            JwtTokenProvider jwtTokenProvider,
            UserRepository userRepository,
            UserProfileRepository userProfileRepository,
            ImageStorageService imageStorageService
    ) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.imageStorageService = imageStorageService;
    }

    @Transactional
    public UserDTO create(UserCreateRequestDTO userCreateRequestDTO) throws Exception { //Exception 수정 필요
        String provider = userCreateRequestDTO.getProvider();
        if (provider.equals("coconut")) {
            return UserDTO.from(this.userService.create(
                    userCreateRequestDTO.getUserId(),
                    userCreateRequestDTO.getNickName(),
                    userCreateRequestDTO.getPassword(),
                    userCreateRequestDTO.getAge(),
                    userCreateRequestDTO.getGender(),
                    userCreateRequestDTO.getPhoneNum(),
                    userCreateRequestDTO.getProfileImg(),
                    userCreateRequestDTO.getPushToken()));
        } else {
            String userId = this.getSocialId(userCreateRequestDTO.getAccessToken(), provider);
            System.out.println("userId = " + userId);
            return UserDTO.from(this.userService.create(
                    userId,
                    userCreateRequestDTO.getNickName(),
                    "",
                    userCreateRequestDTO.getAge(),
                    userCreateRequestDTO.getGender(),
                    userCreateRequestDTO.getPhoneNum(),
                    userCreateRequestDTO.getProfileImg(),
                    userCreateRequestDTO.getPushToken()));
        }
    }

    public UserDTO signIn(UserSignInDTO userSignInDTO) throws JsonProcessingException {
        String provider = userSignInDTO.getProvider();
        User user;

        if (provider.equals("coconut")) {
            user = this.userService.signIn(userSignInDTO.getUserId(), userSignInDTO.getPassword());
        } else {
            String userId = this.getSocialId(userSignInDTO.getAccessToken(), provider);
            user = this.userService.signIn(userId, "");
        }

        String jwtToken = this.jwtTokenProvider.createToken(user.getUserId());
        return UserDTO.from(
                this.userService.findByUserId(userSignInDTO.getUserId()),
                jwtToken);
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
                HttpMethod.POST,
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
    }

    @Transactional
    public ProfileDTO createUserProfile(String userId, CreateProfileRequest request) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(String.format("user '%s' does not exist", userId)));

        String profileImagePath = this.imageStorageService.upload(request.getImageBinary());
        UserProfile createdProfile = user.createProfile(request.getName(), request.getIsKid(), profileImagePath);

        this.userRepository.save(user);
        return ProfileDTO.of(createdProfile, request.getImageBinary());
    }

    @Transactional(readOnly = true)
    public ProfileDTO getUserProfile(String userId, String profileId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException(String.format("user '%s' does not exist", userId)));
        UserProfile userProfile = this.userProfileRepository.findByIdAndUserOrElseThrow(profileId, user);
        return ProfileDTO.of(
                userProfile,
                this.imageStorageService.download(userProfile.getImagePath())
        );
    }
}
