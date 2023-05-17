package dk.shadow.discordbot.utils;

public class GenerateKode {

    public static String kode() {
        int length = 6;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            // Generate a random number between 0 and 35 (inclusive)
            int randomNumber = (int) (Math.random() * 36);

            // Convert the random number to a character
            char randomChar = (randomNumber < 10) ? (char) (randomNumber + '0') : (char) (randomNumber - 10 + 'A');

            builder.append(randomChar);
        }

        return ColorUtils.getColored(builder.toString());
    }
}
