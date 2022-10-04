package com.autentia.gapi.dto.mapper;

import com.autentia.gapi.domain.GapiGroup;
import com.autentia.gapi.dto.GroupInDTO;
import org.springframework.stereotype.Component;

@Component
public class MapperGroupDTO implements Mapper<GroupInDTO, GapiGroup> {
    @Override
    public GapiGroup map(GroupInDTO in) {
        GapiGroup gapiGroup = new GapiGroup();
        gapiGroup.setName(in.getName());
        gapiGroup.setAdmin(in.getAdmin());
        gapiGroup.setTotalBill(in.getTotalBill());
        gapiGroup.setUserGroups(in.getUserGroups());
        return gapiGroup;
    }
}
