import twitter4j.TwitterException;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TweetCaller extends TimerTask {
    Tweeter tweeter;
    Timer timer = new Timer();
    Random rd = new Random();
    LocalTime time;

    public TweetCaller(Tweeter tweeter, LocalTime Time) {
        this.time = Time;
        this.tweeter = tweeter;
    }

    @Override
    public void run() {
        tweeter.sendTweet();
        ZonedDateTime z =  ZonedDateTime.of(LocalDate.now(),this.time, ZoneId.systemDefault()).isAfter(ZonedDateTime.now()) ? ZonedDateTime.of(LocalDate.now(),this.time, ZoneId.systemDefault()) : ZonedDateTime.of(LocalDate.now(),this.time, ZoneId.systemDefault()).plusDays(1);
        timer.schedule(this, Date.from(z.toInstant()));
    }
}