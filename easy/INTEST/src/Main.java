import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String... args) {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        int answer = 0;
        try {
            String[] tokens = buffer.readLine().split("[ ]+");

            int n = Integer.parseInt(tokens[0]);
            int k = Integer.parseInt(tokens[1]);

            for (int i = 0; i < n; i++) {
                if (Double.parseDouble(buffer.readLine()) % k == 0) answer++;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(answer);
    }
}


//Example
//
//Input:
//7 3
//1
//51
//966369
//7
//9
//999996
//11
//
//Output:
//4
//
