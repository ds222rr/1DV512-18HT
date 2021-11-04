package ds222rr_praktikalTask2;

import java.util.Random;

public class Philosopher implements Runnable {
	
	private int id;
	
	private final ChopStick leftChopStick;
	private final ChopStick rightChopStick;
	
	private Random randomGenerator = new Random();
	
	private int numberOfEatingTurns = 0;
	private int numberOfThinkingTurns = 0;
	private int numberOfHungryTurns = 0;

	private double thinkingTime = 0;
	private double eatingTime = 0;
	private double hungryTime = 0;
	
	// Philosopher's state
	private PState state = PState.thinking;
	
	// Debug variable
	public boolean debug = false;
	
	// Volatile variable
	private boolean exit = false;
	
	// Enumeration for Philosopher's state
	public enum PState {thinking, eating, hungry}
	
	public Philosopher(int id, ChopStick leftChopStick, ChopStick rightChopStick, int seed, boolean debug) {
		this.id = id;
		this.leftChopStick = leftChopStick;
		this.rightChopStick = rightChopStick;
		// Assign debug variable
		this.debug = debug;
		
		/*
		 * set the seed for this philosopher. To differentiate the seed from the other philosophers, we add the philosopher id to the seed.
		 * the seed makes sure that the random numbers are the same every time the application is executed
		 * the random number is not the same between multiple calls within the same program execution 
		 
		 * NOTE
		 * In order to get the same average values use the seed 100, and set the id of the philosopher starting from 0 to 4 (0,1,2,3,4). 
		 * Each philosopher sets the seed to the random number generator as seed+id. 
		 * The seed for each philosopher is as follows:
		 * 	 	P0.seed = 100 + P0.id = 100 + 0 = 100
		 * 		P1.seed = 100 + P1.id = 100 + 1 = 101
		 * 		P2.seed = 100 + P2.id = 100 + 2 = 102
		 * 		P3.seed = 100 + P3.id = 100 + 3 = 103
		 * 		P4.seed = 100 + P4.id = 100 + 4 = 104
		 * Therefore, if the ids of the philosophers are not 0,1,2,3,4 then different random numbers will be generated.
		 */
		
		randomGenerator.setSeed(id+seed);
	}
	public int getId() {
		return id;
	}

	public double getAverageThinkingTime() {
		double averageThinkingTime = (int) (thinkingTime/numberOfThinkingTurns);
		return averageThinkingTime;
	}

	public double getAverageEatingTime() {
		double averageEatingTime = (int) (eatingTime/numberOfEatingTurns);
		return averageEatingTime;
	}

	public double getAverageHungryTime() {
		double averageHungryTime = (int) (hungryTime/numberOfHungryTurns);
		return averageHungryTime;
	}
	
	public int getNumberOfThinkingTurns() {
		return numberOfThinkingTurns;
	}
	
	public int getNumberOfEatingTurns() {
		return numberOfEatingTurns;
	}
	
	public int getNumberOfHungryTurns() {
		return numberOfHungryTurns;
	}

	public double getTotalThinkingTime() {
		return thinkingTime;
	}

	public double getTotalEatingTime() {
		return eatingTime;
	}

	public double getTotalHungryTime() {
		return hungryTime;
	}

	@Override
	public void run() {
		// As long as stop() is not called
		while (!exit) {
			thinking();
			hungry();
			eating();
		}
	}

	// Get Philosopher's status
    public PState getState(){
		return this.state;
    }
	
    // Change Philosopher's status
    public void setStatus(PState state){
		this.state = state;
    }
	
	// Stop philosopher
    public void stop(){
        exit = true;
    }
	
	private void thinking() {
		setStatus(PState.thinking);
		numberOfThinkingTurns++;
		int thinkTime = randomGenerator.nextInt(1000);
		printState();
		// Thinking process
		waiting(thinkTime);	
		thinkingTime += thinkTime;

	}
	
	private void eating() {
		setStatus(PState.eating);
		numberOfEatingTurns++;
		int eatTime = randomGenerator.nextInt(1000);
		printState();
		// Eating process
		waiting(eatTime);
		eatingTime += eatTime;
		// Put down ChopSticks
		leftChopStick.putDown(this);
		rightChopStick.putDown(this);
	}
	
	// Method for Thinking and Eating process
	private void waiting(long t) {
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
		}
	}
	
	private void hungry() {
		setStatus(PState.hungry);
		// Pick up ChopSticks and measure hungrytime
		long hungryStart = leftChopStick.pickUp(this);
		long hungryEnd  = rightChopStick.pickUp(this);
		// In case hungrytime > 0
		if (hungryStart > 0 || hungryEnd  > 0) {
			numberOfHungryTurns++;
			hungryTime += (hungryStart + hungryEnd);
		}
	}
	
	// Print philosopher's state
	private void printState() {
		if (debug) {
			System.out.println("Philosopher_"+id+ " is "+getState().toString() +"...");
		}
	}
}
