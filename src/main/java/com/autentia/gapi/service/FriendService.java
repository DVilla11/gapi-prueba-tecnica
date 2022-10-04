package com.autentia.gapi.service;

import com.autentia.gapi.domain.GapiFriend;
import com.autentia.gapi.domain.GapiGroup;
import com.autentia.gapi.dto.GroupInDTO;
import com.autentia.gapi.repository.FriendRepository;
import com.autentia.gapi.repository.GroupRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final GroupRepository groupRepository;

    public FriendService(FriendRepository friendRepository, GroupRepository groupRepository) {
        this.friendRepository = friendRepository;
        this.groupRepository = groupRepository;
    }

    public GapiFriend save(GapiFriend friend){
        Optional<GapiGroup> group = groupRepository.findById(friend.getListGroupsFriends().get(0).getId());

        if(!group.isPresent()){
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        friend.setDate(dateFormat.format(new Date()));
        return friendRepository.save(friend);
    }

    public List<GapiFriend> findAll(){
        return friendRepository.findAll();
    }

    public GapiFriend findGroupById(Long id){
        Optional<GapiFriend> friend = friendRepository.findById(id);
        if(!friend.isPresent()){
            return null;
        }
        return friend.get();
    }

    @Transactional
    public GapiFriend editFriendBill(Long id, Map<String, Double> objectMap){
        Optional<GapiFriend> friendToEdit = friendRepository.findById(id);

        if(!friendToEdit.isPresent()){
            return null;
        }

        friendToEdit.get().setBill(objectMap.get("bill"));
        return friendRepository.save(friendToEdit.get());
    }


    public void deleteById(Long id){
        Optional<GapiFriend> friendToRemove = friendRepository.findById(id);
        if(friendToRemove.isPresent()){
            friendRepository.deleteById(id);
        }
    }

}
