package com.ariba.procurment.mgmt.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ariba.procurment.mgmt.data.model.Roles;
import com.ariba.procurment.mgmt.data.model.Users;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(Users user) 
    {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                mapToGrantedAuthorities(user.getAuthorities()),
                user.getPassword(),
                user.getIsEnabled(),
                user.getMobileNumber(),
                user.getFirstName(),
                user.getLastName(),
                user.getLastPasswordResetDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Roles> authorities) 
    {
        return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
    }
}
