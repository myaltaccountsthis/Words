import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Wordle wordle = new Wordle(Wordle.words.get((int) (Math.random() * Wordle.words.size())));
        System.out.println(Wordle.words.size());
        wordle.read("arose10000");
        wordle.read("light10000");
        wordle.read("palmy02100");
        wordle.read("canal22001");
        wordle.printWordList();
    }

    /*
    public static void adjust() throws Exception {

        FileReader reader = new FileReader("library.txt");

        StringBuilder builder = new StringBuilder();
        int i;
        while ((i = reader.read()) != -1) {
            builder.append((char) i);
        }
        reader.close();

        String str = builder.toString();
        str = str.replace("\"", "");

        FileWriter writer = new FileWriter("library.txt");
        writer.write(str);
    }
     */
}
