import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;

public class Tweeter {
    Twitter t;
    TwitterFactory tf;

    public Tweeter(String CKey, String CSecret, String AToken, String ASecret) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CKey)
                .setOAuthConsumerSecret(CSecret)
                .setOAuthAccessToken(AToken)
                .setOAuthAccessTokenSecret(ASecret);

        this.tf = new TwitterFactory(cb.build());
        this.t = tf.getInstance();
    }

    public void sendTweet() {
        ZonedDateTime z = ZonedDateTime.now(ZoneId.systemDefault());
        StatusUpdate s;
        if (z.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
            s = new StatusUpdate("Es ist Mittwoch, meine Duden.");
            s.setMedia(new File("res/mittwochmeineduden.png"));
        } else {
            s = new StatusUpdate("Es ist nicht Mittwoch, meine Duden.");
        }
        try {
            t.updateStatus(s);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
