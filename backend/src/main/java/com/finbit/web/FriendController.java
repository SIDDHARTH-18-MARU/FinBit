package com.finbit.web;

import com.finbit.domain.Friend;
import com.finbit.repo.FriendRepository;
import com.finbit.web.dto.FriendRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendController {
    private final FriendRepository friends;

    public FriendController(FriendRepository friends) {
        this.friends = friends;
    }

    @GetMapping
    public List<Friend> list() {
        return friends.findAll();
    }

    @PostMapping
    public Friend create(@RequestBody @Valid FriendRequest req) {
        Friend f = Friend.builder()
                .name(req.name())
                .email(req.email())
                .phone(req.phone())
                .build();
        return friends.save(f);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!friends.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        friends.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
