package com.ast.task2.controller;

import com.ast.task2.domain.User;
import com.ast.task2.domain.Vote;
import com.ast.task2.domain.VoteOption;
import com.ast.task2.service.PollManager;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/votes")
public class VoteController {

    private final PollManager manager;

    public VoteController(PollManager manager) {
        this.manager = manager;
    }

    @GetMapping
    public Collection<Vote> getAllVotes() {
        return manager.getAllVotes();
    }

    @GetMapping("/{id}")
    public Vote getVote(@PathVariable long id) {
        return manager.getVoteById(id).orElse(null);
    }

    @PostMapping
    public Vote createVote(@RequestBody Map<String, Object> payload) {
    System.out.println("Incoming vote payload: " + payload);
    Long userId = payload.get("user") != null ? ((Number)((Map)payload.get("user")).get("id")).longValue() : null;
    Long optionId = ((Number)((Map)payload.get("option")).get("id")).longValue();

    User user = userId != null ? manager.getUserById(userId).orElse(null) : null;
    VoteOption option = manager.getVoteOptionById(optionId)
        .orElseThrow(() -> new RuntimeException("VoteOption not found"));
    long count = manager.getAllVotes().stream()
        .filter(v -> v.getOption().getId() == option.getId())
        .count();
    System.out.println("Option '" + option.getCaption() + "' hat jetzt " + count + " Votes.");

    return manager.createVote(user, option);
}


    @DeleteMapping("/{id}")
    public void deleteVote(@PathVariable long id) {
        manager.deleteVote(id);
    }
}
