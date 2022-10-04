package com.autentia.gapi.service;

import com.autentia.gapi.domain.GapiRole;
import com.autentia.gapi.domain.GapiUser;
import com.autentia.gapi.dto.UserOutDTO;
import com.autentia.gapi.dto.mapper.MapperUserDTO;
import com.autentia.gapi.exceptions.UserException;
import com.autentia.gapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("UserService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final MapperUserDTO mapperUserDTO;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, MapperUserDTO mapperUserDTO) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapperUserDTO = mapperUserDTO;
    }



    public UserOutDTO save(GapiUser gapiUser){
        GapiUser userDb = userRepository.findByUserName(gapiUser.getUsername());
        if(userDb == null){
            gapiUser.setPassword(passwordEncoder.encode(gapiUser.getPassword()));
            gapiUser.setUserRole(Arrays.asList(new GapiRole("ROLE_USER"), new GapiRole("ROLE_ADMIN")));
            userRepository.save(gapiUser);

            return mapperUserDTO.map(gapiUser);
        }
        throw new UserException("Username exist", HttpStatus.CONFLICT);
    }

    public UserOutDTO logged(String username){
        GapiUser userDb = userRepository.findByUserName(username);
        if(userDb != null){
            return mapperUserDTO.map(userDb);
        }
        return null;
    }

    public List<GapiUser> findAll(){
        return userRepository.findAll();
    }

    public GapiUser findUserByUsername(String username){
        GapiUser user = userRepository.findByUserName(username);
        if(user != null){
            return user;
        }
        throw new UserException("User not found", HttpStatus.NOT_FOUND);
    }

    public void deleteById(Long id){
        Optional<GapiUser> userToRemove = userRepository.findById(id);
        if(userToRemove.isEmpty()){
            throw new UserException("User not found", HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        GapiUser user = userRepository.findByUserName(username);

        if(user != null){

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthoritiesToMap(user.getUserRole()));
        }
        throw new UsernameNotFoundException("User " + username + " not found");
    }
}
