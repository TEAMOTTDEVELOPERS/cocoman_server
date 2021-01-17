package orangetaxiteam.cocoman.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    @ApiOperation(value = "Sample api for this app", tags = "Sample!")
    public String test(){
        return "Hello";
    }

}
