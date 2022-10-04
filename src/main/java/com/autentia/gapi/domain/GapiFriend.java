package com.autentia.gapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GapiFriend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Double bill;

    private String description;

    private String date;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "group_friends",
            joinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
    private List<GapiGroup> listGroupsFriends;

}
