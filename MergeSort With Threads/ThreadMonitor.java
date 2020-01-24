import java.util.Arrays;
import java.util.Vector;

public class ThreadMonitor implements Monitor{
	private Vector<Integer[]> array;
	private int maxThreads, idleThreads;
	private boolean done;
	
	public ThreadMonitor(Integer[] unsortedArray, int numberOfThreads) {
		array = new Vector<Integer[]>();
		for(int i=0; i<unsortedArray.length;i++) {
			Integer[] temp  = {unsortedArray[i]};
			array.add(temp);
		}
		
		maxThreads = numberOfThreads;
		idleThreads = 0;
		done = false;
	}

	@Override
	public synchronized Integer[] removeCouple() {
		while(array.size()<2 && !done) {
			if(idleThreads == maxThreads-1) {
				done = true;
				notifyAll();
			}
			else {
				idleThreads++;
				try {
					wait();
				} catch(InterruptedException e) {}
				idleThreads--;
			}
		}
		
		// if no more left
		if(done)
			return null;
		// If everything works
		Integer[] couple = new Integer[array.size()-1]; // needs check
		couple = mergeSort(array.remove(0),array.remove(0));
		return couple;
	}

	@Override
	public synchronized void addArray(Integer[] array) {
		this.array.add(array);
	}

	public synchronized Integer[] sortedArray() {
		while(!done)
			try {
				wait();
			} catch(InterruptedException e) {}
		
		return this.array.get(0);
	}
	
	private synchronized Integer[] mergeSort(Integer[] arr1, Integer[] arr2) {
		Integer[] toBeSortedArray = new Integer[(arr1.length) + (arr2.length)];
		
		int i=0;
		// combine the two arrays into one
		for(int j=0; j<arr1.length;j++)
			toBeSortedArray[i++] = arr1[j];
		for(int j=0; j<arr2.length;j++)
			toBeSortedArray[i++] = arr2[j];
		
		Arrays.sort(toBeSortedArray); // This sort is MergeSort according to the JAVA 7 API
		return toBeSortedArray;
	}
}
