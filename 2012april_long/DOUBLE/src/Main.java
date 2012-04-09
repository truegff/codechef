import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(System.out);

        int T = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < T; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            if (num % 2 == 0) printWriter.println(num);
            else printWriter.println(num - 1);
        }

        printWriter.flush();
    }
}
