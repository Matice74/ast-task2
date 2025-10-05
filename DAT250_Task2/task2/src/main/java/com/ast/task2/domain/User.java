package com.ast.task2.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // oder AUTO
    private Long id;
    private String username;
    private String email;
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private Set<Poll> created = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Vote> voted = new LinkedHashSet<>();
    public User() {this.created = new LinkedHashSet<>();
        this.voted = new LinkedHashSet<>();}

public User(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.created = new LinkedHashSet<>();
        this.voted = new LinkedHashSet<>();
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.created = new LinkedHashSet<>();
        this.voted = new LinkedHashSet<>();
    }
    public Poll createPoll(String question) {
        Poll poll = new Poll(question, null, null, new java.util.ArrayList<>());
        created.add(poll);
        poll.setCreatedBy(this);
        return poll;
    }

    public Vote voteFor(VoteOption option) {
        Vote vote = new Vote(java.time.Instant.now(), option, this, null);
        vote.setVotesOn(option);
        voted.add(vote);
        return vote;
    }

    // Getter & Setter
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

}
