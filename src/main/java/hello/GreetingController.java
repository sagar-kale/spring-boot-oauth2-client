package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {
    @Autowired
    private OAuth2RestTemplate guestRestTemplate;

    @GetMapping(value = "/greeting")
    public String greeting(User user, Model model) {
        model.addAttribute("user", user);
        return "greeting";
    }
}
