package com.ast.task2.domain;

import jakarta.persistence.*;

@Entity
public class VoteOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caption;
    private int presentationOrder;
    @ManyToOne
    private Poll poll;
    public VoteOption() {}

    public VoteOption(String caption, int presentationOrder) {
        this.caption = caption;
        this.presentationOrder = presentationOrder;
    }

    public VoteOption(long id, String caption, int presentationOrder) {
        this.id = id;
        this.caption = caption;
        this.presentationOrder = presentationOrder;
    }

    // Getter & Setter
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }

    public int getPresentationOrder() { return presentationOrder; }
    public void setPresentationOrder(int presentationOrder) { this.presentationOrder = presentationOrder; }
    public Poll getPoll() { return poll; }
    public void setPoll(Poll poll) { this.poll = poll; }

}