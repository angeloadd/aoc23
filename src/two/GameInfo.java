package two;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class GameInfo
{
    public int gameId;
    public int biggestRedPull;
    public int biggestGreenPull;
    public int biggestBluePull;
    public Integer power;

    public static GameInfo create(String line)
    {
        String[] info = line.split("[:;,]");

        HashMap<String, Integer> bigValues = new HashMap<>(Map.of("red", 0, "green", 0, "blue", 0));
        for(String st: info) {
            setNewValueIfBigger("red", st, bigValues);
            setNewValueIfBigger("green", st, bigValues);
            setNewValueIfBigger("blue", st, bigValues);
        }

        var gameInfo = new GameInfo();
        gameInfo.gameId = Integer.parseInt(info[0].replaceAll("[a-zA-Z\\s]", ""));
        gameInfo.biggestRedPull = bigValues.get("red");
        gameInfo.biggestGreenPull = bigValues.get("green");
        gameInfo.biggestBluePull = bigValues.get("blue");
        gameInfo.power = (0 != bigValues.get("blue") ? bigValues.get("blue") : 1) * (0 != bigValues.get("red") ? bigValues.get("red") : 1) * (0 != bigValues.get("green") ? bigValues.get("green") : 1);

        return gameInfo;
    }

    private static void setNewValueIfBigger(String key, String st, HashMap<String, Integer> values) {
        if(st.contains(key)){
            int currentRedValue = Integer.parseInt(st.replaceAll("[a-zA-Z\\s]", ""));
            if(currentRedValue > values.get(key)){
                values.put(key, currentRedValue);
            }
        }
    }
}
