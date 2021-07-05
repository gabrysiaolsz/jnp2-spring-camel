package com.example.apachecameljnp;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.json.*;

public class TweetInfo {

    private String topic;
    private String text;
    private String username;
    private Long favoriteCount;
    private Date creationDate;

    public TweetInfo() {
    }

    public TweetInfo(String text, String username, Long favoriteCount, Date creationDate) {
        this.text = text;
        this.username = username;
        this.favoriteCount = favoriteCount;
        this.creationDate = creationDate;
    }

    public void setTopic(String topic){
        this.topic = topic;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFavoriteCount(Long favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }



    @Override
    public String toString() {
        return "Received new tweet:\n" + text + "\nWritten by: " + username +
                "\nIt received " + favoriteCount + " favs\nWas written on: " + creationDate;
    }

    public String toJson(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String strDate = dateFormat.format(creationDate) + "+0000";

        JSONObject jsonObject = new JSONObject();
        System.out.println("topic to" + topic);
        jsonObject.put("topic", topic);
        jsonObject.put("text", text);
        jsonObject.put("username", username);
        jsonObject.put("favoriteCount", favoriteCount);
        jsonObject.put("creationDate", strDate);

        return jsonObject.toString();
    }

}
