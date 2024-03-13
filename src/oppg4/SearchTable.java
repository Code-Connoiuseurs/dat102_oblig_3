package oppg4;

public class SearchTable {
	
	public static boolean binarySearchTable(int[] table, int searchNumber) {

		int startIndex = 0;
		int endIndex = table.length - 1;

		while (true) {
			int midIndex = startIndex + (endIndex - startIndex) / 2;
			if (searchNumber == table[midIndex]) {
				return true;
			} else if (endIndex <= startIndex) {
				return false;
			} else if (searchNumber > table[midIndex]) {
				startIndex = midIndex+1;
			} else {
				endIndex = midIndex-1;
			}
		}
	}

	public static boolean binarySearchTableRecursion(int[] table, int searchNumber, int startIndex, int endIndex) {
		int midIndex = startIndex + (endIndex - startIndex) / 2;
		if (searchNumber == table[midIndex]) {
			return true;
		} else if (startIndex >= endIndex) {
			return false;
		} else if (searchNumber > table[midIndex]) {
			return binarySearchTableRecursion(table, searchNumber, midIndex+1, endIndex);
		} else {
			return binarySearchTableRecursion(table, searchNumber, startIndex, midIndex-1);
		}
	}

	
}