package ds222rr_praktikalTask1;

/*
 * File:	Process.java
 * Course: 	Operating Systems
 * Code: 	1DV512
 * Author: 	Suejb Memeti
 * Date: 	November, 2018
 */

import java.util.ArrayList;

public class FCFS{

	// The list of processes to be scheduled
	public ArrayList<Process> processes;
	
	// Queue list for processes in run()
	private ArrayList<Process> queue = new ArrayList<Process>();
	
	// StringBuilder for Line 4 in printGanttChart()
	StringBuilder sBTime = new StringBuilder();

	// Class constructor
	public FCFS(ArrayList<Process> processes) {
		this.processes = processes;
	}
	
	public void run() {
		// List for completed processes
		ArrayList<Process> completed = new ArrayList<Process>();
		int counter = processes.size();		// processed Processes
		int time = 0;	// Total elapsed time
		
		// While unprocessed processes are in the list
		while (counter > 0) {
			checkForNewProcesses(time);	// Look for arriving processes
			// If processes are in the queue
			if (queue.size() > 0) {				
				// Perform processes in the queue
				while (queue.size() > 0) {
					// Processor working time
					for (int j = 0; j < queue.get(0).getBurstTime(); j++) {
						time++;	// Increment time
						checkForNewProcesses(time);	// Look for arriving processes
					}
					// Determine numbers
					queue.get(0).setCompletedTime(time); // set Completed Time
					// Set Turnaround Time
					queue.get(0).setTurnaroundTime(queue.get(0).getCompletedTime()-queue.get(0).getArrivalTime());
					// Set Waiting Time
					queue.get(0).setWaitingTime(queue.get(0).getTurnaroundTime()-queue.get(0).getBurstTime());
					counter--;	// Decrement counter (one process accomplished)
					completed.add(queue.get(0));	// move completed process to "completed" array
					queue.remove(0);	// remove completed process from queue
				}
			}
			// Processor idle time
			else {
				time++;	// Increment time
			}
			// Last process completed: assign completed list of processes to processes
			if (counter == 0) {
				processes = completed;
			}
		}
	}
	// Private method to check for new Processes to process
	private void checkForNewProcesses(int t) {
		// Loop over all remaining processes
		for (int i = 0; i < processes.size(); i++) {
			// In case process has arrived
			if (processes.get(i).getArrivalTime() == t) {
				queue.add(processes.get(i));	// Add process to queue
				processes.remove(i);	// Remove process from previous list
				i--;	// Decrement i
			}
		}
	}

	public void printTable() {
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
		for (int i = 0; i < processes.size(); i++) {
			System.out.println(processes.get(i).getProcessId()+"\t"+processes.get(i).getArrivalTime()+"\t"+
							   processes.get(i).getBurstTime()+"\t"+processes.get(i).getCompletedTime()+"\t"+
							   processes.get(i).getTurnaroundTime()+"\t"+processes.get(i).getWaitingTime());
		}
		System.out.println("------------------------------------------");
	}

	public void printGanttChart() {
		StringBuilder sBLine = new StringBuilder();	// StringBuilder for Line 1 and 3
		StringBuilder sBValues = new StringBuilder();	// StringBuilder for Line 2
		sBTime.append(0);	// Start with 0
		int time = 0;	// Total elapsed time
	
		for (int i = 0; i < processes.size(); i++) {
			sBValues.append("|");	// Chart border after Process beginning
			for (int j = 0; j < processes.get(i).getBurstTime()+1; j++) {
				// In case no process is available
				if (processes.get(i).getArrivalTime() > time) {
					// Processor idle time
					while (processes.get(i).getArrivalTime() > time) {
						sBValues.append("*");	// * -> symbol for idle
						sBTime.append(" ");
						time++;
					}
					sBValues.append("||");	// Chart border after idle time
					addTime(time);	// Add time at end of Process idle period
				}
				
				// Add Process description in Chart
				if (j == processes.get(i).getBurstTime()/2) {
					sBValues.append("P"+(processes.get(i).getProcessId()));
					sBTime.append("  ");
				}
				// Add space sign and increase time
				else {
					sBValues.append(" ");
					sBTime.append(" ");
					time++;
				}
			}
			sBValues.append("|");	// Chart border after Process end
			addTime(time);	// Add time at end of Process
		}

		// Construct sBLine
		for (int i = 0; i < sBValues.length(); i++) {
			sBLine.append("=");
		}
		// Gantt char printout
		System.out.println();
		System.out.println("%%%%%%%%%%%%%%% GANTT CHART %%%%%%%%%%%%%%%");	// Header
		System.out.println();
		System.out.println(sBLine.toString());
		System.out.println(sBValues.toString());
		System.out.println(sBLine.toString());
		System.out.print(sBTime.toString());
	}
	
	// Private method to add the time to sBTime in printGanttChart()
	private void addTime(int t) {
		// In case time is below 10
		if (t < 10) {
			sBTime.append(0);
		}
		sBTime.append(t);
	}
}
