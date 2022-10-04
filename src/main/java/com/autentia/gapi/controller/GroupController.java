package com.autentia.gapi.controller;

import com.autentia.gapi.domain.GapiGroup;
import com.autentia.gapi.domain.GapiUser;
import com.autentia.gapi.dto.GroupInDTO;
import com.autentia.gapi.repository.GroupRepository;
import com.autentia.gapi.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/group")
public class GroupController {

    @Autowired
    private final GroupService groupService;



    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public ResponseEntity<?> addGroup(@RequestBody GroupInDTO group){
        groupService.save(group);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<GapiGroup> getGroups(){
        return groupService.findAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getGroupById(@PathVariable("name") String name){
        GapiGroup groupByName = groupService.findGroupByName(name);
        if(groupByName != null){
            return new ResponseEntity<GapiGroup>(groupByName, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editGroup(@PathVariable("id") Long id, @RequestBody GroupInDTO group){
        GapiGroup groupEdited = groupService.editGroup(id, group);

        if(groupEdited != null){
            return new ResponseEntity<GapiGroup>(groupEdited, HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        groupService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
