package com.ast.task2.controller;

import com.ast.task2.domain.Poll;
import com.ast.task2.domain.VoteOption;
import com.ast.task2.service.PollManager;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/polls")
public class PollController {

    private final PollManager manager;

    public PollController(PollManager manager) {
        this.manager = manager;
    }

    @GetMapping
    public Collection<Poll> getAllPolls() {
        return manager.getAllPolls();
    }

    @GetMapping("/{id}")
    public Poll getPoll(@PathVariable long id) {
        return manager.getPollById(id).orElse(null);
    }

    // --- POST mit JSON ---
    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        return manager.createPoll(
                poll.getQuestion(),
                poll.getPublishedAt(),
                poll.getValidUntil(),
                poll.getOptions()
        );
    }

    @PutMapping("/{id}")
    public Poll updatePoll(@PathVariable long id,
                           @RequestBody Poll poll) {
        return manager.updatePoll(
                id,
                poll.getQuestion(),
                poll.getPublishedAt(),
                poll.getValidUntil()
        );
    }

    @DeleteMapping("/{id}")
    public void deletePoll(@PathVariable long id) {
        manager.deletePoll(id);
    }
}
