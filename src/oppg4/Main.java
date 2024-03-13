package oppg4;

import java.util.Arrays;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) {

		HashSet<Integer> hashset = new HashSet<>();
		
		int antElementer = 100_000;
		int[] table = new int[antElementer];

		setTable(hashset, table, antElementer);
		Arrays.sort(table);

		int[] rndTbl = randomTableGenerator(antElementer/10);
		
		{
            long now = System.nanoTime();
			int counter = 0;
			for (int e : rndTbl) {
				if (SearchTable.binarySearchTable(table, e)) {
					counter++;
				}
			}
			System.out.println("Numbers found: " + counter);
		    long elapsed = System.nanoTime() - now;
		    System.out.println("Time elapsed: " + elapsed + " nanoseconds");
		    System.out.println();
        }

		{
            long now = System.nanoTime();
            int counter = 0;
            for (int e : rndTbl) {
                if (SearchTable.binarySearchTableRecursion(table, e, 0, table.length-1)) {
                    counter++;
                }
            }
			System.out.println("Numbers found: " + counter);
		    long elapsed = System.nanoTime() - now;
		    System.out.println("Time elapsed: " + elapsed + " nanoseconds");
		    System.out.println();
		}
		
		{
		    long now = System.nanoTime();
			int counter = 0;
			for (int e : rndTbl) {
				if(hashset.contains(e)) {
					counter++;
				}
			}
			System.out.println("Numbers found: " + counter);
		    long elapsed = System.nanoTime() - now;
		    System.out.println("Time elapsed: " + elapsed + " nanoseconds");
		    System.out.println();
		}


	}

	public static void setTable(HashSet<Integer> hashset, int[] table, int antElementer) {
		int tall = 376; // Her kan vi bruke et vilkårlig tall

		for (int i = 0; i < antElementer; i++) {
			// legger tall til i HashSet og tabell
			tall = (tall + 45713) % 1000000; // Se nedenfor om 45713
			hashset.add(tall);
			table[i] = tall;
		}
	}

	public static void printTable(int[] table) {

		System.out.print("[");
		for (int i = 0; i < table.length; i++) {
			if (i < table.length - 1) {
				System.out.print(table[i] + ", ");
			} else {
				System.out.print(table[i]);
			}
		}
		System.out.println("]"); 
	}

	public static boolean isSorted(int[] table) {
		for (int i = 0; i < table.length - 1; i++) {
			if (table[i] > table[i + 1]) {
				return false;
			}
		}
		return true;
	}

	public static int[] randomTableGenerator(int amountRandomNumbers) {
		int[] randomTable = new int[amountRandomNumbers];
		int min = 0;
		int max = 999999;
		for (int i = 0; i < randomTable.length; i++) {
			randomTable[i] = (int) Math.floor(Math.random() * (max - min + 1) + min);
		}
		return randomTable;
	}

}