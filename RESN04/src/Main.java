import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    static int totalSteps;

    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(System.out);

        int T = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(bufferedReader.readLine());
            totalSteps = 0;
            String[] tok = bufferedReader.readLine().split("[ ]+");
            for (int j = 0; j < N; j++) {
                totalSteps += (int) Math.floor(Integer.parseInt(tok[j]) / ((j + 1) * 1.0));
            }

            printWriter.println(totalSteps % 2 == 1 ? "ALICE" : "BOB");
        }

        printWriter.flush();
    }
}

//Example
//
//Input:
//2
//1
//1
//2
//1 1
//
//Output:
//ALICE
//ALICE
