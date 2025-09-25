package com.ast.task2.domain;

public class VoteOption {
    private long id;
    private String caption;
    private int presentationOrder;

    public VoteOption() {}

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
}
