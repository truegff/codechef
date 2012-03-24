import java.io.*;
import java.math.BigInteger;

public class Main {
    static BigInteger[] fact = new BigInteger[101];

    public static void main(String... args) {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out));
        try {
            int k = Integer.parseInt(buffer.readLine());

            for (int i = 0; i < k; i++) {
                int crewAmt = Integer.parseInt(buffer.readLine());

                long two = 1;
                while ((two << 1) <= crewAmt) {
                    two <<= 1;
                }

                printWriter.println(two);
            }

            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//Example
//
//Input:
//2
//5
//12
//
//
//Output:
//4
//8


