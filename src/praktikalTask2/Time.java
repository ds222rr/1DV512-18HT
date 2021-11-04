package ds222rr_praktikalTask2;

import java.util.Random;

public class Time {
	public static void main(String[] args) {
		Random randomGenerator = new Random();
		randomGenerator.setSeed(100);
		for (int i = 0; i < 20; i++) {
			System.out.println(randomGenerator.nextInt(1000));
		}
	}
}
