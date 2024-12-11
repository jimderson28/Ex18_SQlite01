package com.example.ex18_sqlite01;

public class Comment {
    private long id;
    private String comment;

    public long getId(){
        return id;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public String toString(){
        return comment;
    }
}
