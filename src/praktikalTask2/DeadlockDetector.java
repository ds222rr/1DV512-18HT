package ds222rr_praktikalTask2;

import ds222rr_praktikalTask2.Philosopher.PState;

import java.util.ArrayList;

import ds222rr_praktikalTask2.Philosopher;

public class DeadlockDetector implements Runnable {
	
	ArrayList<Philosopher> philosophers = new ArrayList<Philosopher>();
	private boolean exit = false;
	private boolean deadLock = false;
	
	public DeadlockDetector(ArrayList<Philosopher> philosophers) {
		this.philosophers = philosophers;
	}
	
	public void run() {	
		while (!deadLock || !exit) {
			deadLock = true;
			for (int i = 0; i < philosophers.size(); i++) {
				if (philosophers.get(i).getState() != PState.hungry) {
					deadLock = false;
				}
			}
			if (deadLock) {
				System.out.println("DEADLOCK DETECTED!");
				for (int i = 0; i < philosophers.size(); i++) {
					philosophers.get(i).stop();
				}
				end();
			}
		}
	}
	
	public void end() {
		exit = true;
	}
}
