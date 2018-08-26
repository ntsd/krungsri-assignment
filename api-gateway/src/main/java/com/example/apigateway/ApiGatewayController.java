package com.example.apigateway;

import com.example.apigateway.security.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ApiGatewayController {
    @Lazy
    @Autowired
    private JwtConfig jwtConfig;

    @GetMapping(path="/me")
    public RedirectView getMe(HttpServletRequest request) {
        String header = request.getHeader(jwtConfig.getHeader());

        String token = header.replace(jwtConfig.getPrefix(), "");
        Claims claims = Jwts.parser()
                .setSigningKey(jwtConfig.getSecret().getBytes())
                .parseClaimsJws(token)
                .getBody();

        String userId = claims.getSubject();

        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8002").path(userId).build()
                .toUriString();

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }
}
