package com.autentia.gapi.repository;

import com.autentia.gapi.domain.GapiGroup;
import com.autentia.gapi.domain.GapiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GapiGroup, Long> {

    GapiGroup findByName(String name);

}
