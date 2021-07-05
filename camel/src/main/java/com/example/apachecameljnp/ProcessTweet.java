package com.example.apachecameljnp;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import twitter4j.Status;

public class ProcessTweet implements Processor {

    private final String topic;

    public ProcessTweet(String topic) {
        super();
        this.topic = topic;
    }

    public void process(Exchange exchange) {
        Status bodyIn = (Status) exchange.getIn().getBody();

        TweetInfo tweetEntity = new TweetInfo();

        tweetEntity.setText(bodyIn.getText());

        if (bodyIn.getUser() != null) {
            tweetEntity.setUsername(bodyIn.getUser().getName());
        } else {
            tweetEntity.setUsername("");
        }

        tweetEntity.setFavoriteCount(Long.valueOf(bodyIn.getFavoriteCount()));
        tweetEntity.setCreationDate(bodyIn.getCreatedAt());
        tweetEntity.setTopic(topic);

        exchange.getIn().setBody(tweetEntity.toJson());
    }
}