import java.io.*;
import java.time.LocalTime;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        File configFile = new File("config.txt");
        if (configFile.createNewFile()) {
            PrintWriter pw = new PrintWriter(new FileWriter(configFile));
            pw.println("# Fill in your Twitter app credentials here:");
            pw.println("ConsumerKey=");
            pw.println("ConsumerSecret=");
            pw.println("AccessToken=");
            pw.println("AccessTokenSecret=");
            pw.println("# Configure the bot here. Set the time (HHMM) for the bot to tweet at:");
            pw.println("CheckTime=1200");
            pw.close();
            System.out.println("Please fill in the config file.");
            System.exit(0);
        }
        BufferedReader cfgReader = new BufferedReader(new InputStreamReader(new FileInputStream(configFile)));
        HashMap<String,String> configVals = new HashMap<>();
        String line;
        while ((line = cfgReader.readLine()) != null) {
            if (line.charAt(0) != '#') {
                String[] lineA = line.split("=");
                if (lineA.length == 2) configVals.put(lineA[0],lineA[1]);
            }
        }
        if (!configVals.containsKey("ConsumerKey") || !configVals.containsKey("ConsumerSecret") || !configVals.containsKey("AccessToken") || !configVals.containsKey("AccessTokenSecret") || !configVals.containsKey("CheckTime")) System.exit(0);
        Tweeter tweeter = new Tweeter(configVals.get("ConsumerKey"),configVals.get("ConsumerSecret"),configVals.get("AccessToken"),configVals.get("AccessTokenSecret"));
        TweetCaller tweetCaller = new TweetCaller(tweeter, LocalTime.of(Integer.parseInt(configVals.get("CheckTime").substring(0,2)),Integer.parseInt(configVals.get("CheckTime").substring(2,4))));
        tweetCaller.run();
    }
}
