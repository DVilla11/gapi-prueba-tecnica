package com.autentia.gapi.dto.mapper;

import com.autentia.gapi.domain.GapiUser;
import com.autentia.gapi.dto.UserOutDTO;
import org.springframework.stereotype.Component;

@Component
public class MapperUserDTO implements Mapper<GapiUser, UserOutDTO> {
    @Override
    public UserOutDTO map(GapiUser in) {
        UserOutDTO userOut = new UserOutDTO();
        userOut.setId(in.getId());
        userOut.setUsername(in.getUsername());
        userOut.setEmail(in.getEmail());
        userOut.setListGroup(in.getListGroup());
        userOut.setUserRole(in.getUserRole());
        return userOut;
    }
}
