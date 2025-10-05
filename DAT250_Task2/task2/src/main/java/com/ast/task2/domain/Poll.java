package com.ast.task2.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    @ManyToOne
    private User createdBy;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<VoteOption> options = new ArrayList<>();

    public Poll() {}

    public Poll(String question, Instant publishedAt,
                Instant validUntil, List<VoteOption> options) {
        this.question = question;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.options = options;
    }

    public Poll(Long id, String question, Instant publishedAt,
                Instant validUntil, List<VoteOption> options) {
        this.id = id;
        this.question = question;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.options = options;
    }

    public VoteOption addVoteOption(String caption) {
        int order = options.size(); // 0,1,2,...
        VoteOption option = new VoteOption(caption, order);
        option.setPoll(this);
        options.add(option);
        return option;
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
    public User getCreatedBy() { return createdBy; }
    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }
}
