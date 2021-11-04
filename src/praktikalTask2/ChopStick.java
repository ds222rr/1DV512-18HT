package ds222rr_praktikalTask2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {
	private final int id;
	Lock myLock = new ReentrantLock();
	
	public ChopStick(int id) {
		this.id = id;
	}
	
	public long pickUp(Philosopher philosopher) {		
		long hungryTime = 0;
		// If myLock is locked
		if (!myLock.tryLock()) {
			hungryTime = System.currentTimeMillis();
			if (philosopher.debug) {
				if (id == philosopher.getId()) {
					System.out.println("Philosopher_"+philosopher.getId()+ " is hungry and waiting for right ChopStick("+id+") ...");
				}
				else {
					System.out.println("Philosopher_"+philosopher.getId()+ " is hungry and waiting for left ChopStick("+id+") ...");
				}
			}
			// Wait until myLock can be acquired
			while (!myLock.tryLock()) {	
			}
		}
		if (philosopher.debug) {
			if (id == philosopher.getId()) {
				System.out.println("Philosopher_"+philosopher.getId()+ " picks right ChopStick("+id+") ...");
			}
			else {
				System.out.println("Philosopher_"+philosopher.getId()+ " picks left ChopStick("+id+") ...");
			}
		}
		// In case Philosopher had to wait for at least one ChopStick
		if (hungryTime > 0) {
			return (System.currentTimeMillis()-hungryTime);
		}
		// In case Philosopher did not had to wait for any ChopStick
		else {
			return 0;
		}
	}
	
	public void putDown(Philosopher philosopher) {
		if (philosopher.debug) {
			if (id == philosopher.getId()) {
				System.out.println("Philosopher_"+philosopher.getId()+ " puts right ChopStick("+id+") ...");
			}
			else {
				System.out.println("Philosopher_"+philosopher.getId()+ " puts left ChopStick("+id+") ...");
			}
		}
		// Release myLock
		myLock.unlock();
	}
}
