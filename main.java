import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class main {
	public static void main(String[]args){
//	Arraylists for process ID, arrival time, and burst time
		ArrayList<String> processID = new ArrayList<String>();
		ArrayList<Integer> arrivalTime = new ArrayList<Integer>();
		ArrayList<Integer> burstTime = new ArrayList<Integer>();
		
		try {
// File location
			File txtFile = new File("C:\\Users\\puffy\\Desktop\\cmp426file.txt");
// Open file
			Scanner myReader = new Scanner(txtFile);
				while(myReader.hasNext()) {
// Store contents of the file into three different lists
					processID.add(myReader.next());
					arrivalTime.add(myReader.nextInt());
					burstTime.add(myReader.nextInt());
				}
// Close file
				myReader.close();
				
// Catch if there is an error
		} catch (FileNotFoundException e) {
			System.out.println("Error has occured.");
			e.printStackTrace();
		}
// store how many processes there are onto numOfProcesses int var
		int numOfProcesses = processID.size();
// create arrays of ids, arrival time, and burst time (this is so I can sort them all together)
		String[] processIDArr = new String[numOfProcesses];
		int[] arrivalTimeArr = new int[numOfProcesses];
		int[] burstTimeArr = new int[numOfProcesses];
		
// transfer all the data from the arraylists to the arrays
		for(int i = 0; i<numOfProcesses;i++) {
			processIDArr[i] = processID.get(i);
			arrivalTimeArr[i] = arrivalTime.get(i);
			burstTimeArr[i] = burstTime.get(i);
		}
		
// Sort array of processes based on arrival time
	for(int j=0;j<numOfProcesses;j++) {
		for (int i=0;i<numOfProcesses-1; i++) {
			if (arrivalTimeArr[i]>arrivalTimeArr[i+1] ) {
		// arrival time sort
				int tempNum = arrivalTimeArr[i];
				arrivalTimeArr[i] = arrivalTimeArr[i+1];
				arrivalTimeArr[i+1] = tempNum;
		// process ID sort
				String tempID = processIDArr[i];
				processIDArr[i] = processIDArr[i+1];
				processIDArr[i+1] = tempID;
		// Burst time sort
				int tempNum2 = burstTimeArr[i];
				burstTimeArr[i] = burstTimeArr[i+1];
				burstTimeArr[i+1] = tempNum2;
			}
		}
	}

// Clear all the arraylists 
		processID.clear();
		arrivalTime.clear();
		burstTime.clear();
// fill them up with the newly sorted data
		for(int i = 0; i<numOfProcesses;i++) {
			processID.add(processIDArr[i]);
			arrivalTime.add(arrivalTimeArr[i]);
			burstTime.add(burstTimeArr[i]);
		}
		
// ******************* FCFS CALCULATIONS **********************************************************
// Arraylists to store each calculation
		ArrayList<Integer> fcfsTurnaroundTime = new ArrayList<Integer>();
		ArrayList<Integer> fcfsWaitTime = new ArrayList<Integer>();
		ArrayList<Integer> fcfsResponseTime = new ArrayList<Integer>();
		ArrayList<Integer> fcfsCompletionTime = new ArrayList<Integer>();
		double avgFCFSResponseTime=0;
		double avgFCFSTurnaroundTime=0;
		double avgFCFSWaitTime=0;
		// Response Time
				
				fcfsResponseTime.add(arrivalTime.get(0));
				for(int i=1;i<numOfProcesses; i++) {
					//if process starts after wait time
					if(i!=0 && arrivalTime.get(i)>(fcfsResponseTime.get(i-1)+burstTime.get(i-1))){
						fcfsResponseTime.add(arrivalTime.get(i));
					}else {
				fcfsResponseTime.add(fcfsResponseTime.get(i-1)+burstTime.get(i-1));
				}
					avgFCFSResponseTime += fcfsResponseTime.get(i);
				}	
				avgFCFSResponseTime = avgFCFSResponseTime/numOfProcesses;
				
				
// Completion time
		
				for(int i=0;i<numOfProcesses; i++) {
					fcfsCompletionTime.add(fcfsResponseTime.get(i)+burstTime.get(i));
					}
// TurnAround time
		
		for(int i=0;i<numOfProcesses; i++) {
			fcfsTurnaroundTime.add(fcfsCompletionTime.get(i)-arrivalTime.get(i));
			avgFCFSTurnaroundTime += fcfsTurnaroundTime.get(i);
		}
		avgFCFSTurnaroundTime = avgFCFSTurnaroundTime/numOfProcesses;
		
// Wait time
		
		for(int i=0;i<numOfProcesses; i++) {
		fcfsWaitTime.add(fcfsResponseTime.get(i)-arrivalTime.get(i));
			avgFCFSWaitTime += fcfsWaitTime.get(i);
		}
		avgFCFSWaitTime = avgFCFSWaitTime/numOfProcesses;
		

	

// ******************* Round Robin Calculation ****************************************************
		
		// to measure how much of the process is left to run increment down based on the burst time
		//Ask user quantum time
		ArrayList<Integer> rrTurnaroundTime = new ArrayList<Integer>();
		ArrayList<Integer> rrWaitTime = new ArrayList<Integer>();
		ArrayList<Integer> rrResponseTime = new ArrayList<Integer>();
		ArrayList<Integer> rrCompletionTime = new ArrayList<Integer>();
		int quantumTime = 2;
		double avgRRResponseTime=0;
		double avgRRTurnaroundTime=0;
		double avgRRWaitTime=0;
		int[] burstTimeCopy = new int[numOfProcesses];
		//making copy of burst times for calculation use
		
		for(int i=0;i< numOfProcesses; i++) {
			burstTimeCopy[i] = burstTime.get(i);
		}
		
		
//Wait time & Completion Time 
		
// 
	
		
		
		
		
		
		
		
		
		
		
// ******************* Printing everything to console ************************************************		
		
System.out.println("-------------------------------------------------");
System.out.println("            CPU Scheduling Simulation");
System.out.println("-------------------------------------------------");
System.out.println("");
System.out.println("-------------------------------------------------");
System.out.println("       First Come First Served Scheduling");
System.out.println("-------------------------------------------------");	
		
// Gantt Graph

for(int i = 0; i<numOfProcesses;i++){
	
	if(i==0 && fcfsResponseTime.get(i)>0) {
		System.out.println("[0-"+fcfsResponseTime.get(i)+"] idle");
	}
	else if(i>0&&fcfsResponseTime.get(i)>fcfsCompletionTime.get(i-1)) {
		System.out.println("["+fcfsCompletionTime.get(i-1) +"-"+fcfsResponseTime.get(i)+"] idle");
	}
	System.out.println("["+fcfsResponseTime.get(i)+"-"+fcfsCompletionTime.get(i)+"] "+processID.get(i)+" running");

}

// Printing Turnaround Times
System.out.println("");
System.out.println("Turnaround times:");
for(int i=0;i<numOfProcesses; i++) {
	System.out.println(processID.get(i)+ " = "+ fcfsTurnaroundTime.get(i));
}
// Printing Wait times
System.out.println("");
System.out.println("Wait times:");
for(int i=0;i<numOfProcesses; i++) {
	System.out.println(processID.get(i)+ " = "+ fcfsWaitTime.get(i));
}
// Printing Response times
System.out.println("");
System.out.println("Response times:");
for(int i=0;i<numOfProcesses; i++) {
	System.out.println(processID.get(i)+ " = "+ fcfsResponseTime.get(i));
}
// Printing the Averages of turnaround, wait, and response times
System.out.println("");
System.out.println("Average turnaround time: "+avgFCFSTurnaroundTime);
System.out.println("Average wait time: "+avgFCFSWaitTime);
System.out.println("Average response time: "+avgFCFSResponseTime);

System.out.println("");
System.out.println("");
System.out.println("-------------------------------------------------");
System.out.println("                  Round Robin");
System.out.println("-------------------------------------------------");

// Gantt Chart





//Printing Turnaround Times
System.out.println("");
System.out.println("Turnaround times:");
for(int i=0;i<numOfProcesses; i++) {
	System.out.println(processID.get(i)+ " = " );
}
//Printing Wait times
System.out.println("");
System.out.println("Wait times:");
for(int i=0;i<numOfProcesses; i++) {
	System.out.println(processID.get(i)+ " = " );
}
//Printing Response times
System.out.println("");
System.out.println("Response times:");
for(int i=0;i<numOfProcesses; i++) {
	System.out.println(processID.get(i)+ " = ");
}
//Printing the Averages of turnaround, wait, and response times
System.out.println("");
System.out.println("Average turnaround time: ");
System.out.println("Average wait time: ");
System.out.println("Average response time: ");
		
	}
}
