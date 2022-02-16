import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Wordle {
    public static final ArrayList<String> words;
    private final ArrayList<String> possibleWords;
    private final ArrayList<Guess> guesses;
    private final String answer;

    public Wordle(String answer) {
        this.guesses = new ArrayList<>();
        this.possibleWords = new ArrayList<>(words);
        this.answer = answer;
    }

    public boolean check() {
        return guesses.contains(new Guess(answer + "00000"));
    }

    public int getNumPossibleWords() {
        return possibleWords.size();
    }

    public boolean read(String s) {
        if (!validGuess(s)) return false;
        Guess guess = new Guess(s);
        System.out.println(guess);
        guesses.add(guess);
        possibleWords.removeIf(word -> {
            for (int i = 0; i < 5; i++) {
                char a = word.charAt(i);
                char b = s.charAt(i);
                // TODO add index check for 0
                if (a != b && guess.getNum(i) == 2) return true;
                if ((a == b || !word.contains(b + "")) && guess.getNum(i) == 1) return true;
                if (a == b && guess.getNum(i) == 0) return true;
            }
            return false;
        });
        System.out.println(possibleWords.size());
        return true;
    }

    public void printWordList() {
        System.out.println(possibleWords);
    }

    /**
     * string like arose01211
     * 0 = not there
     * 1 = wrong spot
     * 2 = right spot
     * @param s string to check
     * @return success
     */
    public static boolean validGuess(String s) {
        if (s.length() != 10) return false;
        for (int i = 0; i < 5; i++) {
            char a = s.charAt(i);
            char b = s.charAt(i + 5);
            if (!Character.isLetter(a)) return false;
            if (!Character.isDigit(b)) return false;
            int num = Integer.parseInt(b + "");
            if (num < 0 || num > 2) return false;
        }
        return true;
    }

    private static class Guess {
        private final String str;
        private final int[] nums;

        private Guess(String s) {
            this.str = s.substring(0, 5);
            this.nums = new int[5];
            String sub = s.substring(5);
            for (int i = 0; i < 5; i++) {
                nums[i] = Integer.parseInt(sub.substring(i, i + 1));
            }
        }

        public String getStr() {
            return str;
        }

        public int getNum(int i) {
            return nums[i];
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof String) return obj.equals(str);
            return ((Guess) obj).str.equals(str);
        }

        @Override
        public String toString() {
            return "Guess{" +
                    "str='" + str + '\'' +
                    ", nums=" + Arrays.toString(nums) +
                    '}';
        }
    }

    static {
        String line = null;
        try {
            line = new Scanner(new File("library.txt")).nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert line != null;
        words = new ArrayList<>(Arrays.asList(line.split(",")));
    }
}
