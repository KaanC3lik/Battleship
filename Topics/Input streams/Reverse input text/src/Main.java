import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        StringBuilder input = new StringBuilder();

        int charAsNumber = reader.read();
        while(charAsNumber != -1) {
            char character = (char) charAsNumber;
            input.append(character);
            charAsNumber = reader.read();
        }
        reader.close();
        StringBuilder reversedInput = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            reversedInput.append(input.charAt(i));
        }
        System.out.println(reversedInput);
        reader.close();
    }
}