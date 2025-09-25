package com.ast.task2.service;

import com.ast.task2.domain.Poll;
import com.ast.task2.domain.User;
import com.ast.task2.domain.Vote;
import com.ast.task2.domain.VoteOption;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class PollManager {

    private final Map<Long, User> users = new HashMap<>();
    private final Map<Long, Poll> polls = new HashMap<>();
    private final Map<Long, Vote> votes = new HashMap<>();
    private final Map<Long, VoteOption> voteOptions = new HashMap<>();

    private long userIdCounter = 1;
    private long pollIdCounter = 1;
    private long voteIdCounter = 1;
    private long optionIdCounter = 1;

    // --- User ---
    public User createUser(String username, String email) {
        User user = new User(userIdCounter++, username, email);
        users.put(user.getId(), user);
        return user;
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }

    public Optional<User> getUserById(long id) {
        return Optional.ofNullable(users.get(id));
    }

    public User updateUser(long id, String username, String email) {
        User user = users.get(id);
        if (user != null) {
            user.setUsername(username);
            user.setEmail(email);
        }
        return user;
    }

    public void deleteUser(long id) {
        users.remove(id);

        // Optional: auch alle Votes des Users löschen
        votes.values().removeIf(v -> v.getUser() != null && v.getUser().getId() == id);
    }

    // --- Poll ---
    public Poll createPoll(String question, Instant publishedAt, Instant validUntil, List<VoteOption> options) {
        options.forEach(opt -> {
            opt.setId(optionIdCounter++);
            voteOptions.put(opt.getId(), opt);
        });

        Poll poll = new Poll(pollIdCounter++, question, publishedAt, validUntil, options);
        polls.put(poll.getId(), poll);
        return poll;
    }

    public Collection<Poll> getAllPolls() {
        return polls.values();
    }

    public Optional<Poll> getPollById(long id) {
        return Optional.ofNullable(polls.get(id));
    }

    public Poll updatePoll(long id, String question, Instant publishedAt, Instant validUntil) {
        Poll poll = polls.get(id);
        if (poll != null) {
            poll.setQuestion(question);
            poll.setPublishedAt(publishedAt);
            poll.setValidUntil(validUntil);
        }
        return poll;
    }

    public void deletePoll(long id) {
        // Poll löschen
        Poll removed = polls.remove(id);
        if (removed != null) {
            // alle Votes löschen, die auf Optionen dieses Polls verweisen
            Set<Long> optionIds = new HashSet<>();
            for (VoteOption opt : removed.getOptions()) {
                optionIds.add(opt.getId());
                voteOptions.remove(opt.getId());
            }
            votes.values().removeIf(v -> optionIds.contains(v.getOption().getId()));
        }
    }

    // --- Vote ---
    public Vote createVote(User user, VoteOption option) {
        // Sicherstellen, dass VoteOption existiert
        VoteOption existingOption = voteOptions.get(option.getId());
        if (existingOption == null) {
            throw new RuntimeException("VoteOption not found");
        }

        // Wenn der User bereits abgestimmt hat, alte Stimme entfernen
        if (user != null) {
            votes.values().removeIf(v -> v.getUser() != null && v.getUser().getId() == user.getId());
        }

        Vote vote = new Vote(Instant.now(), existingOption, user, voteIdCounter++);
        votes.put(vote.getId(), vote);

        return vote;
    }

    public Collection<Vote> getAllVotes() {
        return votes.values();
    }

    public Optional<Vote> getVoteById(long id) {
        return Optional.ofNullable(votes.get(id));
    }

    public void deleteVote(long id) {
        votes.remove(id);
    }
}
