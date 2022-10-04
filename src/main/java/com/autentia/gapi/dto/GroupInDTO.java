package com.autentia.gapi.dto;

import com.autentia.gapi.domain.GapiFriend;
import com.autentia.gapi.domain.GapiUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupInDTO {

    private String name;
    private GapiUser admin;
    private Double totalBill;
    private List<GapiFriend> userGroups;
}
