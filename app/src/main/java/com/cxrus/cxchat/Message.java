package com.cxrus.cxchat;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("_sender")
    private String nickname;
    @SerializedName("_messageContent")
    private String message;

    public Message() {
    }

    public Message(String nickname, String message) {
        this.nickname = nickname;
        this.message = message;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
