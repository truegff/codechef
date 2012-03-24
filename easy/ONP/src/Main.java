import java.io.*;

public class Main {
    static int[] arr;

    public static void main(String... args) {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out));
        try {
            int k = Integer.parseInt(buffer.readLine());

            for (int i = 0; i < k; i++) {
                printWriter.println(new Operation(buffer.readLine()).getAsRPN());
            }

            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Operation {
        public Operation leftPart, rightPart;
        public char symb;

        public static String unfold(String s) {
            if (s.startsWith("(") && s.endsWith(")")) return s.substring(1, s.length() - 1).toString();
            return s;
        }

        public Operation(String folded) {

            String unfolded = unfold(folded);

            if (unfolded.length() > 1) {
                int depth = 0;
                for (int i = 0; i < unfolded.length(); i++) {
                    char cchar = unfolded.charAt(i);
                    if (cchar == '(') depth++;
                    if (cchar == ')') depth--;
                    if ((depth == 0) && ((cchar == '+') || (cchar == '-') || (cchar == '*') || (cchar == '/') || (cchar == '^'))) {
                        symb = cchar;
                        leftPart = new Operation(unfolded.substring(0, i));
                        rightPart = new Operation(unfolded.substring(i + 1, unfolded.length()));
                        break;
                    }
                }
            } else {
                symb = unfolded.charAt(0);
            }
        }

        public String getAsRPN() {
            if (leftPart != null && rightPart != null) return leftPart.getAsRPN() + rightPart.getAsRPN() + symb;
            return "" + symb;
        }
    }
}

//Example
//
//Input:
//3
//(a+(b*c))
//((a+b)*(z+x))
//((a+t)*((b+(a+c))^(c+d)))
//
//Output:
//abc*+
//ab+zx+*
//at+bac++cd+^*
