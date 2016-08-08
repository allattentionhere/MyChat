package com.allattentionhere.mychat.Model;


import android.support.v4.util.ArrayMap;

public class Chat {
    private int count;
    private Message[] messages;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Message[] getMessages() {
        return messages;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }

    public ChatCount[] getEachUserMessageCount() {
        ArrayMap<String, ChatCount> aMap = new ArrayMap<>();
        for (Message message : messages) {
            if (aMap.get(message.getUsername()) != null) {
                ChatCount temp = aMap.get(message.getUsername());
                aMap.put(message.getUsername(), new ChatCount(message.getUsername(), temp.getTotal() + 1, message.isFavorite() ? (temp.getFavourite() + 1) : temp.getFavourite()));
            } else {
                aMap.put(message.getUsername(), new ChatCount(message.getUsername(), 1, message.isFavorite() ? 1 : 0));
            }
        }
        return aMap.values().toArray(new ChatCount[0]);
    }


}
