package socialmediaandjava;


import facebook4j.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterConf {
    //configration parameters

    private final String ConsumerKey = "d5heAw5kuYN9ebhOsWszlVSgR";
    private final String ConsumerSecret = "sSkD79PfzWveZvdRXlaUSWmCKFvNrgveqRJPOwIT3ZHmADsdh5";
//    private final String AccessToken = "424494168-OgHyvWSxq8Zy8SKBDQqlLDSiGPDEfxXupu6eqKPz";
    private final String AccessTokenSecret = "42DEUyDVTT7hJbSYqoBUzoVDd1FErJVhsC6Mj8LyaQKIg";
    private static Twitter twitter;

    public TwitterConf(String tw_access_token) {
        twitter4j.conf.ConfigurationBuilder configurationBuilder = new twitter4j.conf.ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(ConsumerKey)
                .setOAuthConsumerSecret(ConsumerSecret)
                .setOAuthAccessToken(tw_access_token)
                .setOAuthAccessTokenSecret(AccessTokenSecret)
                .setIncludeEntitiesEnabled(true);

        TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
        twitter = tf.getInstance();
        
       
        try {
            System.out.println(twitter.getAccountSettings().getAccessLevel());
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterConf.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    public void getTwitterNewFeeds() {
        List<Status> statuses = null;
        try {
            statuses = twitter.getHomeTimeline();
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterConf.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Showing home timeline.");
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":"
                    + status.getText());
        }
    }

    public void getTwitterUserName() {
        try {
            System.out.println("twitter username:"+twitter.verifyCredentials().getName());
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterConf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void getTwitterUserId() {
        try {
            System.out.println("twitter userId:"+twitter.verifyCredentials().getId());
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterConf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
