package com.autentia.gapi.repository;

import com.autentia.gapi.domain.GapiUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<GapiUser, Long> {

    GapiUser findByUserName(String userName);

}
