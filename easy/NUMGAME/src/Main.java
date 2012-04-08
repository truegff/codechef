import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(System.out);

        int T = Integer.parseInt(bufferedReader.readLine());
        int numSteps;

        for (int i = 0; i < T; i++) {
            numSteps = 0;
            long num = Long.parseLong(bufferedReader.readLine());
            while (num != 1) {
                if ((num % 2) == 1) {
                    num = num - 1;
                    numSteps++;
                } else {
                    int dec = 0;
                    long num2 = num;
                    while ((num2 % 2) != 1) {
                        num2 >>= 1;
                        if (dec == 0) dec++;
                        else
                            dec <<= 1;
                    }
                    num -= dec;
                    numSteps++;
                }
            }
            printWriter.println(numSteps % 2 == 1 ? "ALICE" : "BOB");
        }

        printWriter.flush();
    }
}
