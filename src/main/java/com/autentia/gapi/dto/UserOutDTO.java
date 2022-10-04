package com.autentia.gapi.dto;

import com.autentia.gapi.domain.GapiGroup;
import com.autentia.gapi.domain.GapiRole;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOutDTO {

    private Long id;
    private String username;
    private String email;
    private Collection<GapiRole> userRole;
    private List<GapiGroup> listGroup;

}
