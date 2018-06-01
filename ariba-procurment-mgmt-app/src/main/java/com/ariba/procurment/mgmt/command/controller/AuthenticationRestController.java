package com.ariba.procurment.mgmt.command.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ariba.procurment.mgmt.security.JwtAuthenticationRequest;
import com.ariba.procurment.mgmt.security.JwtTokenUtil;
import com.ariba.procurment.mgmt.security.JwtUser;
import com.ariba.procurment.mgmt.service.TrackerUserDetailsService;
import com.ariba.procurment.mgmt.util.JwtAuthenticationResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthenticationRestController 
{
    @Value("${jwt.header}")
    private String tokenHeader;
    

    @Autowired private JwtTokenUtil jwtTokenUtil;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private TrackerUserDetailsService userDetailsService;

    @RequestMapping(value = "/${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException 
    {
    	log.info("username "+ authenticationRequest.getUsername());
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if (userDetails != null)
        {
            final String token = jwtTokenUtil.generateToken(userDetails, device);
            return ResponseEntity.ok(new JwtAuthenticationResponse(token,"success"));
        }
        return ResponseEntity.ok(new JwtAuthenticationResponse("","Failure"));
    }

    
    @RequestMapping(value = "auth/userDetails", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) 
    {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }
    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) 
    {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) 
        {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken,"Success"));
        } 
        else 
        {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
