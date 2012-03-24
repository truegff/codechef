import java.io.*;
import java.util.Arrays;

public class Main {
    static int[] arr;
    public static void main(String... args) {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out));
        try {
            int k = Integer.parseInt(buffer.readLine());
            arr = new int[k];
            for (int i = 0; i < k; i++)
                arr[i]=Integer.parseInt(buffer.readLine());

            Arrays.sort(arr);

            for (int i = 0; i < arr.length; i++)
                printWriter.println(arr[i]);

            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//Example
//
//Input:
//
//5
//5
//3
//6
//7
//1
//
//Output:
//
//1
//3
//5
//6
//7

