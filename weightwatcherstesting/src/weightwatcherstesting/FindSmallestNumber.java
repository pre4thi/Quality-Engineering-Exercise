package weightwatcherstesting;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Preethi
 *
 */
public class FindSmallestNumber {
	
	/**
	 * Main Program
	 * @param args
	 */
	public static void main(String[] args) {
		new FindSmallestNumber().findNthSmallestNumber();
	}

	/**
	 * Finds nth smallest number from a random array
	 */
	public void findNthSmallestNumber() {
		final int ARRAY_SIZE = 500;
		final int NUMBER_MAX_LIMIT = 100000;

		int[] integerArray = new int[ARRAY_SIZE];
		populateRandomValues(integerArray, NUMBER_MAX_LIMIT);
		Arrays.sort(integerArray);
		System.out.println("Random Array Values : " + Arrays.toString(integerArray));
		System.out.print("Please enter a number between 1 and " + ARRAY_SIZE + ":");
		
		try (Scanner scanner = new Scanner(System.in)) {
			int nthNumber = scanner.nextInt();
			int nthNumberValue = integerArray[nthNumber - 1];
			System.out.println("The nth smallest number is " + nthNumberValue);
		}
	}

	/**
	 * Populates random values to the integer array
	 * @param integerArray
	 * @param limit
	 */
	private void populateRandomValues(int[] integerArray, int limit) {
		for (int i = 0; i < integerArray.length; i++) {
			integerArray[i] = (int) (Math.random() * limit);
		}
	}
}
