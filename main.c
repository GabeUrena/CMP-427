#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>
#include <wait.h>

int main(){
pid_t pid;
int num = -1;

    /* fork a child process */
    pid = fork();

    if (pid < 0) { /* error occurred */
      fprintf(stderr, "Fork Failed");
     return 1;
    }
    else if (pid == 0) { /* child process */
    
     // get user input 
        while (num<=0){
            printf("Input an integer(>0):");
            scanf("%d",&num);
        }
    
    printf("%d ",num);
    //even algorithm
        while(num !=1){
            if(num%2==0){
                num = num/2;
                printf("%d ",num);
    //odd algorithm
            }else{
                num=3*num+1;
                printf("%d ",num);
            }
        }
    }
    else { /* parent process */
      printf("Waiting...");
      wait(NULL);
      printf("Process Completed.");
    }
    return 0;

}
