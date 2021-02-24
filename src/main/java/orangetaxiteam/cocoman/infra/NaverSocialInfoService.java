package orangetaxiteam.cocoman.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import orangetaxiteam.cocoman.domain.SocialInfoService;
import orangetaxiteam.cocoman.domain.exceptions.ErrorCode;
import orangetaxiteam.cocoman.domain.exceptions.InternalServerErrorException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class NaverSocialInfoService implements SocialInfoService {
    @Override
    public String getSocialId(String accessToken) {
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

        HttpStatus statusCode = response.getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode node;
            try {
                node = objectMapper.readValue(response.getBody(), ObjectNode.class);
            } catch (JsonProcessingException e) {
                throw new InternalServerErrorException(ErrorCode.INTERNAL_SERVER, "json processing exception");
            }
            JsonNode res = node.get("response");
            return "NAVER_" + res.get("id").toString();
        }

        if (statusCode.isError()) {
            throw new InternalServerErrorException(ErrorCode.INTERNAL_SERVER, "social resource server error");
        }

        return null;
    }
}
