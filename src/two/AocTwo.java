package two;

import one.AocOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AocTwo
{
    private Integer maxRedCubeAllowed = 12;

    private Integer maxBlueCubeAllowed = 14;

    private Integer maxGreeCubeAllowed = 13;

    public static void main(String[] args)
    {
        var output = new AocTwo().readFromInputStream(
                AocTwo.class.getResourceAsStream("./input.txt")
        );

        System.out.println(output);
    }

    private Integer readFromInputStream(InputStream inputStream)
    {
        int acc = 0;
        int acc2 = 0;
        try (var br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                var gameInfo = GameInfo.create(line);
                acc2 += gameInfo.power;
                if(gameInfo.biggestRedPull > maxRedCubeAllowed || gameInfo.biggestGreenPull > maxGreeCubeAllowed || gameInfo.biggestBluePull > maxBlueCubeAllowed ){
                    continue;
                }

                acc += gameInfo.gameId;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(acc2);
        return acc;
    }
}
