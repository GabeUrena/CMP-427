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
			File txtFile = new File("C:\\Users\\puffy\\OneDrive\\Desktop\\cmp426file.txt");
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
		
	
// ******************* FCFS CALCULATIONS ********************
// Arraylists to store each calculation
		ArrayList<Integer> fcfsTurnaroundTime = new ArrayList<Integer>();
		ArrayList<Integer> fcfsWaitTime = new ArrayList<Integer>();
		ArrayList<Integer> fcfsResponseTime = new ArrayList<Integer>();
		ArrayList<Integer> fcfsCompletionTime = new ArrayList<Integer>();
		ArrayList<Integer> fcfsProcessStart = new ArrayList<Integer>();




// Completion time
		
		for(int i=0;i<numOfProcesses; i++) {
		if(i==0) {
			fcfsCompletionTime.add(burstTime.get(i));
		}else if(arrivalTime.get(i)>fcfsCompletionTime.get(i-1)){
			fcfsCompletionTime.add(arrivalTime.get(i)+burstTime.get(i));
		}else{
			fcfsCompletionTime.add(burstTime.get(i)+fcfsCompletionTime.get(i-1));
		}
		}
		
// TurnAround time
		double avgFCFSTurnaroundTime=0;
		for(int i=0;i<numOfProcesses; i++) {
			fcfsTurnaroundTime.add(fcfsCompletionTime.get(i)-arrivalTime.get(i));
			avgFCFSTurnaroundTime += fcfsTurnaroundTime.get(i);
		}
		avgFCFSTurnaroundTime = avgFCFSTurnaroundTime/numOfProcesses;
		
// Wait time
		double avgFCFSWaitTime=0;
		for(int i=0;i<numOfProcesses; i++) {
			if(i==0) {
				fcfsWaitTime.add(0);
			}else {
			fcfsWaitTime.add(fcfsCompletionTime.get(i-1)-arrivalTime.get(i));
		}
			avgFCFSWaitTime += fcfsWaitTime.get(i);
		}
		avgFCFSWaitTime = avgFCFSWaitTime/numOfProcesses;
		
// Response Time
		double avgFCFSResponseTime=0;
		for(int i=0;i<numOfProcesses; i++) {
		fcfsResponseTime.add(fcfsWaitTime.get(i));
		avgFCFSResponseTime += fcfsResponseTime.get(i);
		}
		avgFCFSResponseTime = avgFCFSResponseTime/numOfProcesses;
		
// getting the start of process
		for(int i=0;i<numOfProcesses; i++) {
			if(i==0) {
				fcfsProcessStart.add(arrivalTime.get(i));
			}else
		fcfsProcessStart.add(fcfsCompletionTime.get(i)-burstTime.get(i));
		
		}

		
		
System.out.println("-------------------------------------------------");
System.out.println("            CPU Scheduling Simulation");
System.out.println("-------------------------------------------------");
System.out.println("");
System.out.println("-------------------------------------------------");
System.out.println("       First Come First Served Scheduling");
System.out.println("-------------------------------------------------");	
		
// Gantt Graph
for(int i = 0; i<numOfProcesses;i++){
	if(i<1 && fcfsProcessStart.get(i)>0) {
		System.out.println("[0-"+fcfsProcessStart.get(i)+"] idle");
	}
	else if(fcfsProcessStart.get(i)>fcfsCompletionTime.get(i-1)) {
		System.out.println("["+fcfsCompletionTime.get(i-1) +"-"+fcfsProcessStart.get(i)+"] idle");
	}
	System.out.println("["+fcfsProcessStart.get(i)+"-"+fcfsCompletionTime.get(i)+"] "+processID.get(i)+" running");

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




		/*
		 * 
		 * 
		 * 
		 * The simulator first reads task information from the input file and stores all data
		 * in a data structure such as an ArrayList. Then it starts simulating one scheduling
		 * algorithm in a time-driven manner. At each time unit (or slot), it adds any newly
		 * arrived task(s) into the ready queue and calls a specific scheduler algorithm in
		 * order to select appropriate tasks from the ready queue. When a task is chosen to 
		 * run, the simulator prints out a message indicating what process ID is chosen to
		 * execute for this time slot. If no task is running (i.e. empty ready queue), it 
		 * prints out an “idle” message. Before advancing to the next time unit, the 
		 * simulator should update all necessary changes in task and ready queue status.
		
		The program is run as follows:

		java Driver fcfs schedule.txt

		 */
		
		
		
	}
	
	
}
