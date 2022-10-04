package com.autentia.gapi.controller;

import com.autentia.gapi.domain.GapiFriend;
import com.autentia.gapi.domain.GapiGroup;
import com.autentia.gapi.dto.GroupInDTO;
import com.autentia.gapi.service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/friend")
public class FriendController {

    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PostMapping
    public ResponseEntity<?> addFriend(@RequestBody GapiFriend friend){
        friendService.save(friend);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<GapiFriend> getFriends(){
        return friendService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupById(@PathVariable("id") Long id){
        GapiFriend friendById = friendService.findGroupById(id);
        if(friendById != null){
            return new ResponseEntity<GapiFriend>(friendById, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editFriend(@PathVariable("id") Long id, @RequestBody Map<String, Double> objectMap){
        GapiFriend friendEdited = friendService.editFriendBill(id, objectMap);

        if(friendEdited != null){
            return new ResponseEntity<GapiFriend>(friendEdited, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        friendService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
