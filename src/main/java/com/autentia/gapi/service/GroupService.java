package com.autentia.gapi.service;

import com.autentia.gapi.domain.GapiGroup;
import com.autentia.gapi.domain.GapiUser;
import com.autentia.gapi.dto.GroupInDTO;
import com.autentia.gapi.dto.mapper.MapperGroupDTO;
import com.autentia.gapi.exceptions.GroupException;
import com.autentia.gapi.exceptions.UserException;
import com.autentia.gapi.repository.FriendRepository;
import com.autentia.gapi.repository.GroupRepository;
import com.autentia.gapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private final GroupRepository groupRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final MapperGroupDTO mapperGroupDTO;

    @Autowired
    private final FriendRepository friendRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository, MapperGroupDTO mapperGroupDTO, FriendRepository friendRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.mapperGroupDTO = mapperGroupDTO;
        this.friendRepository = friendRepository;
    }

    public GapiGroup save(GroupInDTO group){
        GapiUser user = userRepository.findByUserName(group.getAdmin().getUsername());
        GapiGroup groupToSave = mapperGroupDTO.map(group);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        groupToSave.setDateCreation(dateFormat.format(new Date()));
        if(user == null){
            return null;
        }

        groupToSave.setAdmin(user);
        return groupRepository.save(groupToSave);
    }

    public GapiGroup editGroup(Long id, GroupInDTO group){
        Optional<GapiGroup> groupToEdit = groupRepository.findById(id);
        GapiGroup groupToSave = mapperGroupDTO.map(group);
        if(!groupToEdit.isPresent()){
            return null;
        }

        groupToEdit.get().setName(groupToSave.getName());
        groupToEdit.get().setTotalBill(groupToSave.getTotalBill());

        return groupRepository.save(groupToEdit.get());
    }

    public List<GapiGroup> findAll(){
        return groupRepository.findAll();
    }

    public GapiGroup findGroupByName(String name){
        GapiGroup group = groupRepository.findByName(name);
        if(group == null){
            return null;
        }
        return group;
    }

    public void deleteById(Long id){
        Optional<GapiGroup> groupToRemove = groupRepository.findById(id);
        if(groupToRemove.isPresent()){
            groupRepository.deleteById(id);
        }
    }

}
