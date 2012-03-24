public class Main {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(new java.io.BufferedInputStream(System.in));
        int a;
        while (scanner.hasNext()) {
            a = scanner.nextInt();
            if (a != 42) System.out.println(a);
            else break;
        }
    }
}

//Example
//
//Input:
//1
//2
//88
//42
//99
//
//Output:
//1
//2
//88
