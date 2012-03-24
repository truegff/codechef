import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static BigInteger[] fact = new BigInteger[101];

    public static void main(String... args) {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        for (int i = 0; i < 101; i++) fact[i] = new BigInteger("0");
        fact[0] = new BigInteger("1");

        try {
            int k = Integer.parseInt(buffer.readLine());

            for (int i = 0; i < k; i++) {
                System.out.println(getFact(Integer.parseInt(buffer.readLine())));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BigInteger getFact(int i) {
        if (fact[i].intValue() == 0) fact[i] = getFact(i - 1).multiply(new BigInteger(new Integer(i).toString()));
        return fact[i];
    }
}

//Example
//Sample input:
//
//4
//1
//2
//5
//3
//
//Sample output:
//
//1
//2
//120
//6
