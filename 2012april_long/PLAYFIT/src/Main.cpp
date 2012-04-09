#include <cstdio>
using namespace std;
 
int main()
{
    int T=0;
    scanf("%d\n", &T);
    int N=0;
    int current_value=0;
    int min=0, max=0, gl_max=0;

    while(T>0) {
        T--;
        gl_max=0;

        scanf("%d\n", &N);
        scanf("%d", &min);

        max = min;
        N--;
        while (N>0) {
            N--;
            scanf(" %d", &current_value);

            if (current_value < min) {
                max = min = current_value;
            }
            if (current_value > max) {
                max = current_value;
                gl_max = (gl_max < (max-min)) ? (max-min) : gl_max;
            }
        }

        if (gl_max > 0) {
            printf("%d\n", gl_max);
        } else {
            printf("UNFIT\n");
        }
    }
 
    return 0;
}