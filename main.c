/*****************QUESTION 1*******************
#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>
 
int value = 5;
 
int main()
{
pid_t pid;
 
  pid = fork();
 
  if (pid == 0) { /* child process */
/*  value += 15;
    return 0;
  }
  else if (pid > 0) { /* parent process */
/*   wait(NULL);
    printf("PARENT: value = %d",value); /* LINE A */
/*    return 0;
  }
}
***********************ANSWER 1*************************************
Line A will output 5, the global variable 'value' is 
equal to 5 and does not change for the parent.

********************QUESTION 2 ****************************
#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>
 
int main()
{
pid_t pid, pid1;
 
    /* fork a child process */
/*    pid = fork();
 
    if (pid < 0) { /* error occurred */
/*    printf("if\n");
      fprintf(stderr, "Fork Failed");
      printf("if printed\n");
     return 1;
    }
    else if (pid == 0) { /* child process */
/*      pid1 = getpid();
      printf("child: pid = %d",pid); /* A */
/*    printf("child: pid1 = %d",pid1); /* B */
      
/*    }
    else { /* parent process */
/*    pid1 = getpid();
      printf("parent: pid = %d",pid); /* C */
/*      printf("parent: pid1 = %d",pid1); /* D */
/*      wait(NULL);
    }
    return 0;
}
***************************ANSWER 2**********************************
    Line A the output would be 0, because the child called on the fork(),
    Line B the output would be 2603 because within the elseif pid1
    is equal to getpib() this grabs
    the childs pib which is to be assumed as 2603
    Line C the output will be 2603 because when the parent calls on fork()
    it receives the pib of its childs
    Line D the output will be 2600 because its grabbing its own pib
    assumed to be 2600.
********************************QUESTION 3******************************
*/
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



