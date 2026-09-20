import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<String> passwordList = new ArrayList<String>();
        String filename = "passwordList.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String curWord = reader.readLine();
            while (curWord != null)
            {
                if (curWord.length() == 5)
                {
                    passwordList.add(curWord);
                }
                curWord = reader.readLine();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }


        String password = passwordList.get(randomInt(0, passwordList.size() - 1));
        String internalPassword = password.toLowerCase();

        int index = randomInt(0, passwordList.size() - 1);
        String pass = passwordList.get(index);
        String internalPass = password.toLowerCase();
        int maxedAttempts = 5;
        int attempts = maxedAttempts;
        boolean validPassword = false;

        do {
            hp(maxedAttempts);
            String input = rules();
            if (input.toLowerCase().equals(internalPass)) {
                System.out.println("You have got the password, Nice!!");
                success(maxedAttempts - attempts + 1, pass);
                validPassword = true;
            } else {
                feedBack(input, internalPass);

                System.out.println("Too bad, try again!");
                maxedAttempts--;
            }

        } while (maxedAttempts > 0 && !validPassword);
        if (!validPassword)
            System.out.println("Out of attempts! The password was: " + pass);
    }

    /*
    Die Methode hp soll die Anzahl der versuche anhand des zeichen # anzeigen
    */
    static void hp(int attempt) {
        System.out.print("Attempts left: ");
        for (int i = 1; i <= attempt; i++) {
            System.out.print("#");
        }
    }

    static String rules() {
        String input = IO.readln(" " + "Please guess the password: ");

        if (input.length() != 5) {
            System.out.println("Password has 5 letter!");
        }
        if (input.contains("ä") || input.contains("ö") || input.contains("ü") || input.contains("ß")) {
            System.err.println("Use an English word please!");
        }

        return input;
    }


    static int randomInt(int min, int max) {
        double random = Math.random();
        int randomInt = (int) (min + (random * (max - min)) + 0.5);
        return randomInt;
    }

    static boolean containsLetter(String word, char letter) {
        for (char c : word.toCharArray()){
            if (c == letter){
                return true;
            }
        }
        return false;
    }


    static char getHint(String word, char letter, int index){
        return word.charAt(index) == letter ? '+' : containsLetter(word, letter) ? '#' : '-';
    }

    
    static void feedBack(String input, String internalPass){
        System.out.print("Hint: ");
        for (int i = 0; i < Math.min(internalPass.length(), input.length()); i++) {
            char guessChar = input.toLowerCase().charAt(i);
            System.out.print(getHint(internalPass, guessChar, i));
        }
        System.out.println();
    }

    static void success(int usedAttempts, String password)
    {
        String saveFile = "history.txt";

        try (FileWriter writer = new FileWriter(saveFile, true))
        {
            writer.write(password + "was solved after " + usedAttempts + " attempts at date " + LocalDateTime().toString);

        }
        catch (Exception x){
            throw new RuntimeException(x);
        }
    }
}
