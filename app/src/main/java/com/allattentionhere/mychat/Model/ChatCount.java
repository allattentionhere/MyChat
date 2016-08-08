package com.allattentionhere.mychat.Model;


public class ChatCount {

    String username;
    int total = 0;
    int favourite = 0;

    public ChatCount(String username,int total, int favourite) {
        this.username=username;
        this.total = total;
        this.favourite = favourite;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }
}
