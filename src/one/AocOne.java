package one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class AocOne
{
    private final Map<String, String> numericWords = Map.of(
        "one", "1",
        "two", "2",
        "three", "3",
        "four", "4",
        "five", "5",
        "six", "6",
        "seven", "7",
        "eight", "8",
        "nine", "9"
    );

    private final Map<String, String> numericWordsCombinations = Map.of(
        "oneight", "18",
        "twone", "21",
        "threeight", "38",
        "fiveight", "58",
        "sevenine", "79",
        "eightwo", "82",
        "eighthree", "83",
        "nineight", "98"
    );

    public static void main(String[] args)
    {
        var output = new AocOne().readFromInputStream(
            AocOne.class.getResourceAsStream("./input.txt")
        );

        System.out.println(output);
    }

    private Integer readFromInputStream(InputStream inputStream)
    {
        int acc = 0;
        try (var br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arrayWrapperToUseInLambda = {line};

                numericWordsCombinations.forEach((key, number) -> {
                    arrayWrapperToUseInLambda[0] = arrayWrapperToUseInLambda[0].replaceAll(key, number);
                });
                numericWords.forEach((key, number) -> {
                    arrayWrapperToUseInLambda[0] = arrayWrapperToUseInLambda[0].replaceAll(key, number);
                });

                arrayWrapperToUseInLambda[0] = arrayWrapperToUseInLambda[0].replaceAll("[A-Za-z]", "");

                acc += Integer.parseInt(
                    String.valueOf(arrayWrapperToUseInLambda[0].charAt(0) +
                        String.valueOf(arrayWrapperToUseInLambda[0].charAt(arrayWrapperToUseInLambda[0].length() - 1)))
                );
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
        return acc;
    }
}
