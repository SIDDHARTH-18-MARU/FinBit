package com.finbit.repo;

import com.finbit.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
