package com.ast.task2.domain;

import java.time.Instant;

public class Vote {
    private long id;
    private Instant publishedAt;
    private VoteOption option; // 1..1
    private User user; // 0..1, falls anonym

    public Vote() {}

    public Vote(Instant publishedAt, VoteOption option, User user, Long id) {
        this.id = id;
        this.publishedAt = publishedAt;
        this.option = option;
        this.user = user;
    }

    // Getter & Setter
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Instant getPublishedAt() { return publishedAt; }
    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }

    public VoteOption getOption() { return option; }
    public void setOption(VoteOption option) { this.option = option; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
