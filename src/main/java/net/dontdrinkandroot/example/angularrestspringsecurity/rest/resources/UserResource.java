package net.dontdrinkandroot.example.angularrestspringsecurity.rest.resources;

import net.dontdrinkandroot.example.angularrestspringsecurity.rest.TokenUtils;
import net.dontdrinkandroot.example.angularrestspringsecurity.transfer.TokenTransfer;
import net.dontdrinkandroot.example.angularrestspringsecurity.transfer.UserTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by win7 on 2016/10/3.
 */
@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private AuthenticationManager authManager;

    @RequestMapping(method = RequestMethod.GET)
    public UserTransfer getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
        }

        UserDetails userDetails = (UserDetails) principal;
        return new UserTransfer(userDetails.getUsername(), createRoleMap(userDetails));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authenticate")
    public TokenTransfer authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);
        Authentication authentication = authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userService.loadUserByUsername(username);

        return new TokenTransfer(TokenUtils.createToken(userDetails));
    }


    private Map<String, Boolean> createRoleMap(UserDetails userDetails) {
        Map<String, Boolean> roles = new HashMap<String, Boolean>();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            roles.put(authority.getAuthority(), Boolean.TRUE);
        }

        return roles;
    }
}
