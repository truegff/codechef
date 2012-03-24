import java.io.*;

public class Main {

    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out));

        int arr[];
        int arrLength = 0;

        boolean ambigous;

        while ((arrLength = Integer.parseInt(bufferedReader.readLine())) != 0) {
            arr = new int[arrLength];

            String tok[] = bufferedReader.readLine().split("[ ]+");
            for (int i = 0; i < arrLength; i++) arr[i] = Integer.parseInt(tok[i]);

            ambigous = true;
            for (int i = 0; i < arrLength; i++) {
                if (arr[i] != (i + 1)) {
                    if (arr[arr[i] - 1] != (i + 1)) {
                        ambigous = false;
                        printWriter.println("not ambiguous");
                        break;
                    }
                }
            }

            if (ambigous) printWriter.println("ambiguous");
        }

        printWriter.flush();
    }
}

//Sample Input
//
//4
//1 4 3 2
//5
//2 3 4 5 1
//1
//1
//0
//
//Sample Output
//
//ambiguous
//not ambiguous
//ambiguous
