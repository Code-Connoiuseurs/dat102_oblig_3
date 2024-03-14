package oppgave_4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class Hashset_Binaersok {
	public static void main(String[] args) {
		int size = 10_000;
		int maxValue = 100_000;

		HashSet<Integer> hashSet = new HashSet<>();
		Random random = new Random();

		int next = random.nextInt(1_000_000);
		while (hashSet.size() < 100_000) {
			hashSet.add(next);

			do {
				next = (next + 45713) % 1_000_000;

			} while (next % 2 == 0 || next % 5 == 0);
		}
		// setup for sorted array
		Integer[] sortedArray = new Integer[maxValue];
		hashSet.toArray(sortedArray);
		Arrays.sort(sortedArray);

		// random generator
		int[] searchNumbers = new int[size];
		for (int i = 0; i < size; i++) {
			searchNumbers[i] = random.nextInt(1_000_000);
		}

		// search for the HashSet
		long startTime = System.nanoTime();

		int hashSetCount = 0;
		for (int numb : searchNumbers) {
			if (hashSet.contains(numb)) {
				hashSetCount++;
			}
		}
		long endTime = System.nanoTime();
		long elapsedTimeHash = (endTime - startTime);

		// search for sortedArray (binary search)
		startTime = System.nanoTime();
		int binarySearchCount = 0;
		for (int numb : searchNumbers) {
			if (Arrays.binarySearch(sortedArray, numb) >= 0) {
				binarySearchCount++;
			}
		}
		endTime = System.nanoTime();
		long elapsedTimeBinary = (endTime - startTime);

		System.out.println("HashSet search time: " + elapsedTimeHash + " ns " + "\n" + "elements found : "
				+ hashSetCount + "\n" + (double) elapsedTimeHash / 1_000_000 + " ms");

		System.out.println();

		System.out.println("Binary search time: " + elapsedTimeBinary + " ns " + "\n" + "elements found : "
				+ binarySearchCount + "\n" + (double) elapsedTimeBinary / 1_000_000 + " ms");

	}
}
