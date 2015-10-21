package labs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableRace implements Callable<String> {
	static List<String> theList;
	static int nextId = 0;
	
	public String call() {
			int id;
			synchronized(this){
				nextId++;
				id = nextId;
			}
			
			int shortStraw = (int)(Math.random() * 3) + 1;
			if(id==shortStraw)
				Thread.yield();
			
			for (int i = 0; i < 200000; i++) {
				theList.add(id + ":" + i);
			}
			return "Done: " + Integer.toString(id);
	}

	public static void main(String[] args) throws Exception {
		CallableRace race = new CallableRace();
		theList = Collections.synchronizedList(new ArrayList<String>());

		ExecutorService ex = Executors.newFixedThreadPool(3);

		Future<String> status1 = ex.submit(race);
		Future<String> status2 = ex.submit(race);
		Future<String> status3 = ex.submit(race);
		
		System.out.println(status1.get());
		System.out.println(status2.get());
		System.out.println(status3.get());
		// Iterator<String> it = theList.iterator();
		// while (it.hasNext()) {
		// System.out.println(it.next());
		// }

		System.out.println("Size of the list " + theList.size());
	}
}