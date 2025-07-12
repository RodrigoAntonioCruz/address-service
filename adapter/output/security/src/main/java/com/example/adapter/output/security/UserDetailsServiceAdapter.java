package com.example.adapter.output.security;

import com.example.adapter.output.client.utils.Constants;
import com.example.adapter.output.persistence.user.UserPersistenceAdapter;
import com.example.adapter.output.security.mapper.UserDetailsOutputMapper;
import com.example.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceAdapter implements UserDetailsService {

    private final UserDetailsOutputMapper mapper;

    private final UserPersistenceAdapter adapter;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = adapter.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(Constants.USER_NOT_FOUND, username)));
        return mapper.toCustomUserDetails(user);
    }
}
