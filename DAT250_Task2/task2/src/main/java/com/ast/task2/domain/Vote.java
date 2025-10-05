package com.ast.task2.domain;

import java.time.Instant;
import jakarta.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant publishedAt;

    @ManyToOne(optional = false)
    private VoteOption votesOn;  // UML: votedOn

    @ManyToOne
    private User user;

    public Vote() {}

    public Vote(Instant publishedAt, VoteOption votesOn, User user, Long id) {
        this.id = id;
        this.publishedAt = publishedAt;
        this.votesOn = votesOn;
        this.user = user;
    }

    // Getter & Setter
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Instant getPublishedAt() { return publishedAt; }
    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }

    public VoteOption getOption() { return votesOn; }
    public void setOption(VoteOption option) { this.votesOn = option; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public void setVotesOn(VoteOption option) { this.votesOn = option; }
    
}
