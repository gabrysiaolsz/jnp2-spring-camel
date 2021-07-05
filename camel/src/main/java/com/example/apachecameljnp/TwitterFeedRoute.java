package com.example.apachecameljnp;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.twitter.search.TwitterSearchComponent;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class TwitterFeedRoute extends RouteBuilder {

    @Value("${twitter.consumerKey}")
    private String consumerKey;

    @Value("${twitter.consumerSecret}")
    private String consumerSecret;

    @Value("${twitter.accessToken}")
    private String accessToken;

    @Value("${twitter.accessTokenSecret}")
    private String accessTokenSecret;

    @Value("${app.interval}")
    protected String interval;

    @Value("${app.search_terms}")
    protected String searchTerms;

    protected void prepareContextTwitterSearchComponent() {
        TwitterSearchComponent twitterSearchComponent = getContext().getComponent("twitter-search", TwitterSearchComponent.class);

        twitterSearchComponent.setAccessToken(accessToken);
        twitterSearchComponent.setAccessTokenSecret(accessTokenSecret);
        twitterSearchComponent.setConsumerKey(consumerKey);
        twitterSearchComponent.setConsumerSecret(consumerSecret);
    }

    @Override
    public void configure() {
        prepareContextTwitterSearchComponent();

        String[] terms = searchTerms.split(",");

        for (String term : terms) {
            fromF("twitter-search://%s?delay=%s", term, interval)
                    .process(new ProcessTweet(term)) //Translates the tweet.
                    .process(exchange -> log.info("Body is: {}", exchange.getIn()))
                    .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                    .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                    .to("http://springapp:8080/messages")
                    .process(exchange -> log.info("The response code is: {}", exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE)));
        }
    }
}

