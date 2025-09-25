package com.ast.task2.domain;

import java.time.Instant;
import java.util.List;

public class Poll {
    private long id;
    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    private List<VoteOption> options; // 2..*

    public Poll() {}

    public Poll(Long id, String question, Instant publishedAt,
                Instant validUntil, List<VoteOption> options) {
        this.id = id;
        this.question = question;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.options = options;
    }

    // Getter & Setter
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public Instant getPublishedAt() { return publishedAt; }
    public void setPublishedAt(Instant publishedAt) { this.publishedAt = publishedAt; }

    public Instant getValidUntil() { return validUntil; }
    public void setValidUntil(Instant validUntil) { this.validUntil = validUntil; }

    public List<VoteOption> getOptions() { return options; }
    public void setOptions(List<VoteOption> options) { this.options = options; }
}
