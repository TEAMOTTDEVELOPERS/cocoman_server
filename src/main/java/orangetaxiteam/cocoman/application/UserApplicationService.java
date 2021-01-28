package orangetaxiteam.cocoman.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import orangetaxiteam.cocoman.application.dto.UserCreateRequestDTO;
import orangetaxiteam.cocoman.application.dto.UserDTO;
import orangetaxiteam.cocoman.application.dto.UserSignInDTO;
import orangetaxiteam.cocoman.application.dto.UserUpdateRequestDTO;
import orangetaxiteam.cocoman.config.JwtTokenProvider;
import orangetaxiteam.cocoman.domain.User;
import orangetaxiteam.cocoman.domain.UserService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class UserApplicationService {

    private UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserApplicationService(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public UserDTO create(UserCreateRequestDTO userCreateRequestDTO) throws Exception { //Exception 수정 필요
        String provider = userCreateRequestDTO.getProvider();
        if (provider.equals("coconut"))
            return UserDTO.from(userService.create(
                    userCreateRequestDTO.getUserId(),
                    userCreateRequestDTO.getNickName(),
                    userCreateRequestDTO.getPassword(),
                    userCreateRequestDTO.getAge(),
                    userCreateRequestDTO.getGender(),
                    userCreateRequestDTO.getPhoneNum(),
                    userCreateRequestDTO.getProfileImg(),
                    userCreateRequestDTO.getPushToken()));
        else {
            String userId = getSocialId(userCreateRequestDTO.getAccessToken(), provider);
            System.out.println("userId = " + userId);
            return UserDTO.from(userService.create(
                    userId,
                    userCreateRequestDTO.getNickName(),
                    "",
                    userCreateRequestDTO.getAge(),
                    userCreateRequestDTO.getGender(),
                    userCreateRequestDTO.getPhoneNum(),
                    userCreateRequestDTO.getProfileImg(),
                    userCreateRequestDTO.getPushToken()));}
    }

    public UserDTO signIn(UserSignInDTO userSignInDTO) throws JsonProcessingException {
        String provider = userSignInDTO.getProvider();
        User user;

        if (provider.equals("coconut")) {
            user = userService.signIn(userSignInDTO.getUserId(), userSignInDTO.getPassword());
        }
        else{
            String userId = getSocialId(userSignInDTO.getAccessToken(), provider);
            user = userService.signIn(userId, "");
        }

        String jwtToken = jwtTokenProvider.createToken(user.getUserId());
        return UserDTO.from(
                userService.findByUserId(userSignInDTO.getUserId()),
                jwtToken);
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
            return null;	// TODO : exception handling
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
                HttpMethod.POST,
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
        if(statusCode.is2xxSuccessful()){
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node = objectMapper.readValue(response.getBody(), ObjectNode.class);
            if (provider.equals("naver")){
                JsonNode res = node.get("response");
                return res.get("id").toString();
            }
            else
                return node.get("id").asText();
        }
        // TODO : status code exception handling
        else{	// statusCode.isError()
            return null;
        }
    }
    
    public UserDTO findById(Long id) {
		return UserDTO.fromDAO(userService.findById(id).orElseThrow(() -> new InputValueValidationException("invalid user id")));
	}
	
	public UserDTO updateUser(Long id, UserUpdateRequestDTO userUpdateRequestDTO) {
		return UserDTO.fromDAO(userService.update(id
													, userUpdateRequestDTO.getRoles()
													, userUpdateRequestDTO.getAge()
													, userUpdateRequestDTO.getGender()
													, userUpdateRequestDTO.getPhoneNum()
													, userUpdateRequestDTO.getProfileImg()
													, userUpdateRequestDTO.getPushToken())
												);
	}
	
	public void deleteUser(Long id) {
		userService.delete(id);
	}
}
