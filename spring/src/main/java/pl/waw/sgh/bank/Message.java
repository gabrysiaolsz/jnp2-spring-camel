package pl.waw.sgh.bank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageID;

    private String topic;
    private String text;
    private String username;
    private Long favoriteCount;
    private Date creationDate;


    public Message() {
    }

    public Message(String topic, String text, String username, Long favoriteCount, Date creationDate) {
        this.topic = topic;
        this.text = text;
        this.username = username;
        this.favoriteCount = favoriteCount;
        this.creationDate = creationDate;
    }

    public Message(Long messageID, String topic, String text, String username, Long favoriteCount, Date creationDate) {
        this.messageID = messageID;
        this.topic = topic;
        this.text = text;
        this.username = username;
        this.favoriteCount = favoriteCount;
        this.creationDate = creationDate;
    }

    public Long getMessageID() {
        return messageID;
    }

    public String getTopic() {
        return topic;
    }

    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }

    public Long getFavoriteCount() {
        return favoriteCount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "Received new tweet:\n" + text + "\nWritten by: " + username +
                "\nIt received " + favoriteCount + " favs\nWas written on: " + creationDate;
    }
}
