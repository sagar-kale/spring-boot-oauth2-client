package hello;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class Oauth2ClientConfig {

    @ConfigurationProperties(prefix = "security.oauth2.client.user-read")
    @Bean
    public OAuth2ProtectedResourceDetails userWithRead() {
        return new AuthorizationCodeResourceDetails();
    }

    @ConfigurationProperties(prefix = "security.oauth2.client.guest-read")
    @Bean
    public OAuth2ProtectedResourceDetails guestWithRead() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    public OAuth2RestTemplate userRestTemplate(@Qualifier("userWithRead") OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails, OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails, clientContext);
    }

    @Bean
    public OAuth2RestTemplate guestRestTemplate(@Qualifier("guestWithRead") OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails, OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(oAuth2ProtectedResourceDetails, clientContext);
    }
}
