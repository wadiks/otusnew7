package ru.otus.spring.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.service.ServiceSecurity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {


    private ServiceSecurity securityDao;

    public MyUserDetailsService(ServiceSecurity securityDao) {
        this.securityDao = securityDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = securityDao.findByUser(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoleSecurity());
        return new MyUserPrincipal(user.getId(), user.getUserSecurity(), user.getPasswordSecurity(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(String userRole) {

        var setAuths = new HashSet<GrantedAuthority>();
        setAuths.add(new SimpleGrantedAuthority(userRole));
        final var Result = new ArrayList<>(setAuths);
        return Result;
    }

}