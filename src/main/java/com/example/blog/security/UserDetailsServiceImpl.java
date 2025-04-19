package com.example.blog.security;

import com.example.blog.entity.User;
import com.example.blog.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User blogUser = userRepository.findByEmail(username);
      if(blogUser == null){
          throw new UsernameNotFoundException("Email not found");
      }
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(blogUser.getEmail())
                .password(blogUser.getPassword())
                .build();
    }

}
