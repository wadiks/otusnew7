package ru.otus.spring.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.SecurityDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {


    private SecurityDao securityDao;

    public MyUserDetailsService(SecurityDao securityDao) {
        this.securityDao = securityDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = securityDao.findByUser(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
        return new MyUserPrincipal(user.getUser(), user.getPassword(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(String userRole) {

        var setAuths = new HashSet<GrantedAuthority>();
        setAuths.add(new SimpleGrantedAuthority(userRole));
        final var Result = new ArrayList<>(setAuths);
        return Result;
    }

}