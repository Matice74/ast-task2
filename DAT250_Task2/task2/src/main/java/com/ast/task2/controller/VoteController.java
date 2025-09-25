package com.ast.task2.controller;

import com.ast.task2.domain.User;
import com.ast.task2.domain.Vote;
import com.ast.task2.domain.VoteOption;
import com.ast.task2.service.PollManager;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    // --- POST mit JSON ---
    @PostMapping
    public Vote createVote(@RequestBody Vote vote) {
        User user = null;
        if (vote.getUser() != null) {
            user = manager.getUserById(vote.getUser().getId()).orElse(null);
        }

        VoteOption option = vote.getOption();
        if (option == null) throw new RuntimeException("VoteOption missing");

        return manager.createVote(user, option);
    }

    @DeleteMapping("/{id}")
    public void deleteVote(@PathVariable long id) {
        manager.deleteVote(id);
    }
}
