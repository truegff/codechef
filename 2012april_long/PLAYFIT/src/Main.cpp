 #include <cstdio>
 using namespace std;

 int main()
 {

     int T;
     scanf("%d\n", &T);
     int N;
     int current_value;
     int min, max, gl_max;

     while(T>0) {
        T--;

        scanf("%d\n", &N);
        scanf("%d", &min);
        max = min;
        while (N>0) {
            N--;
            scanf(" %d", &current_value);

            if (current_value < min) {
                max = min = current_value;
            }
            if (current_value > max) {
                max = current_value;
                gl_max = (gl_max < max) ? max : gl_max;
            }
        }
        scanf("\n");
        if (gl_max > 0) printf("%d\n", gl_max); else printf("UNFIT\n");
     }

    return 0;
 }
