package orangetaxiteam.cocoman.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import orangetaxiteam.cocoman.domain.PasswordValidator;
import orangetaxiteam.cocoman.domain.UserRepository;
import orangetaxiteam.cocoman.domain.exceptions.BadRequestException;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserSignInDTO;
import orangetaxiteam.cocoman.application.dto.UserUpdateRequestDTO;
import orangetaxiteam.cocoman.config.JwtTokenProvider;
import orangetaxiteam.cocoman.domain.User;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class UserApplicationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordValidator signInValidator;

    @Autowired
    public UserApplicationService(
            UserRepository userRepository,
            JwtTokenProvider jwtTokenProvider,
            PasswordValidator signInValidator
    ) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.signInValidator = signInValidator;
    }

    @Transactional
    public UserDTO create(UserCreateRequestDTO userCreateRequestDTO) throws Exception { //Exception 수정 필요
        String provider = userCreateRequestDTO.getProvider();
        if (provider.equals("coconut")) {
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
            return UserDTO.from(userRepository.save(user));
        } else {
            String userId = getSocialId(userCreateRequestDTO.getAccessToken(), provider);
            User user = User.social(
                    userId,
                    userCreateRequestDTO.getNickName(),
                    userCreateRequestDTO.getAge(),
                    userCreateRequestDTO.getGender(),
                    userCreateRequestDTO.getPhoneNum(),
                    userCreateRequestDTO.getProfileImg(),
                    userCreateRequestDTO.getPushToken()
            );
            return UserDTO.from(userRepository.save(user));
        }
    }

    public UserDTO signIn(UserSignInDTO userSignInDTO) {
        User user = userRepository.findByUserId(userSignInDTO.getUserId());
        signInValidator.validate(
                user,
                userSignInDTO.getPassword(),
                userSignInDTO.getAccessToken()
        );
        String jwtToken = jwtTokenProvider.createToken(user.getUserId());
        return UserDTO.from(user, jwtToken);
    }


    public String getSocialId(String accessToken, String provider) throws JsonProcessingException {

        if (provider.equals("facebook"))
            return "FACEBOOK_" + getFacebookIdInfo(accessToken);
        else if (provider.equals("google"))
            return "GOOGLE_" + getGoogleIdInfo(accessToken);
        else if (provider.equals("kakao"))
            return "KAKAO_" + getKakaoIdInfo(accessToken);
        else if (provider.equals("naver"))
            return "NAVER_" + getNaverIdInfo(accessToken);
        else
            return null;    // TODO : exception handling
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

        return getIdFromResponse(response, "facebook");
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

        return getIdFromResponse(response, "google");
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

        return getIdFromResponse(response, "kakao");
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

        return getIdFromResponse(response, "naver");
    }

    private String getIdFromResponse(ResponseEntity<String> response, String provider) throws JsonProcessingException {
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.readValue(response.getBody(), ObjectNode.class);
            if (provider.equals("naver")) {
                JsonNode res = node.get("response");
                return res.get("id").toString();
            } else
                return node.get("id").asText();
        }
        // TODO : status code exception handling
        else {    // statusCode.isError()
            return null;
        }
    }

    public UserDTO findById(String id) {
        return UserDTO.from(this.userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorCode.ID_DOES_NOT_EXIST, "Invalid user id")));
    }

    public UserDTO updateUser(String id, UserUpdateRequestDTO userUpdateRequestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorCode.ID_DOES_NOT_EXIST, "Invalid user id"));
        user.update(
                userUpdateRequestDTO.getAge(),
                userUpdateRequestDTO.getGender(),
                userUpdateRequestDTO.getPhoneNum(),
                userUpdateRequestDTO.getProfileImg()
        );
        return UserDTO.from(userRepository.save(user));
    }

    public void deleteUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorCode.ID_DOES_NOT_EXIST, "Invalid user id"));
        userRepository.delete(user);
    }
}
