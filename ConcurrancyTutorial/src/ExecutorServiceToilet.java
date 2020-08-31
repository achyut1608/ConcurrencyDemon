import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ExecutorServiceToilet {
	public static void main(String[]args) {
		Task1 task = new Task1();
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		for(int i=0;i<5;i++) 
			service.execute(task);
		
	}
}

class Task1 implements Runnable {
	
	Semaphore semaphore = new Semaphore(1);
	
	@Override
	public void run() {
		
			try {
				semaphore.acquire();
				String name = Thread.currentThread().getName();
				System.out.println(name + " is entering...");
				Thread.sleep(100);
				System.out.println(name + " is using...");
				Thread.sleep(100);
				System.out.println(name + " is executing...");
				Thread.sleep(100);
				System.out.println();
				semaphore.release();
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		
	}	
}

