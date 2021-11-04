package ds222rr_praktikalTask1;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ArrayList<Process> listOfProcesses = new ArrayList<Process>();

		// Test 1
		System.out.println("Test 1");
		listOfProcesses.add(new Process(1, 0, 2));
		listOfProcesses.add(new Process(2, 3, 1));
		listOfProcesses.add(new Process(3, 5, 6));

		FCFS test1 = new FCFS(listOfProcesses);
		test1.run();
		test1.printTable();
		test1.printGanttChart();
		listOfProcesses.clear();
		System.out.println("\n\n");
		
		
		// Test 2
		System.out.println("Test 2");
		listOfProcesses.add(new Process(1, 0, 4));
		listOfProcesses.add(new Process(2, 1, 3));
		listOfProcesses.add(new Process(3, 2, 1));
		listOfProcesses.add(new Process(4, 3, 2));
		listOfProcesses.add(new Process(5, 4, 5));

		FCFS test2 = new FCFS(listOfProcesses);
		test2.run();
		test2.printTable();
		test2.printGanttChart();
		listOfProcesses.clear();
		System.out.println("\n\n");
		
		
		// Test 3
		System.out.println("Test 3");
		listOfProcesses.add(new Process(1, 0, 4));
		listOfProcesses.add(new Process(2, 2, 3));
		listOfProcesses.add(new Process(3, 1, 1));
		listOfProcesses.add(new Process(4, 3, 2));
		listOfProcesses.add(new Process(5, 4, 5));

		FCFS test3 = new FCFS(listOfProcesses);
		test3.run();
		test3.printTable();
		test3.printGanttChart();
		listOfProcesses.clear();
		System.out.println("\n\n");
		
		
		// Test 4
		System.out.println("Test 4");
		listOfProcesses.add(new Process(1, 0, 18));
		listOfProcesses.add(new Process(2, 0, 5));
		listOfProcesses.add(new Process(3, 0, 7));

		FCFS test4 = new FCFS(listOfProcesses);
		test4.run();
		test4.printTable();
		test4.printGanttChart();
		listOfProcesses.clear();
		System.out.println("\n\n");
		
		
		// Test 5
		System.out.println("Test 5");
		listOfProcesses.add(new Process(1, 0, 18));
		listOfProcesses.add(new Process(2, 3, 2));
		listOfProcesses.add(new Process(3, 25, 5));
		listOfProcesses.add(new Process(4, 29, 2));
		listOfProcesses.add(new Process(5, 33, 7));

		FCFS test5 = new FCFS(listOfProcesses);
		test5.run();
		test5.printTable();
		test5.printGanttChart();
		listOfProcesses.clear();
		System.out.println("\n\n");
		
		
		// Test 6
		System.out.println("Test 6");
		listOfProcesses.add(new Process(1, 0, 18));
		listOfProcesses.add(new Process(2, 2, 5));
		listOfProcesses.add(new Process(3, 4, 7));

		FCFS test6 = new FCFS(listOfProcesses);
		test6.run();
		test6.printTable();
		test6.printGanttChart();
		listOfProcesses.clear();
		System.out.println("\n\n");
	}
}
