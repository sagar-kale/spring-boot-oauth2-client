package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {

    @Value("${users.base-uri}")
    private String userBaseUri;


    @Autowired
    private OAuth2RestTemplate userRestTemplate;

    @Autowired
    private OAuth2RestTemplate guestRestTemplate;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/user")
    public User getUser() {
        User user = this.userRestTemplate.getForObject(this.userBaseUri + "user", User.class);
        return user;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/guest")
    public String guest() {
        return this.guestRestTemplate.getForObject(this.userBaseUri + "guest", String.class);
    }


}
