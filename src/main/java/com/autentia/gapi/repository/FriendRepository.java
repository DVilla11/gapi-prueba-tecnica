package com.autentia.gapi.repository;

import com.autentia.gapi.domain.GapiFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<GapiFriend, Long> {
}
