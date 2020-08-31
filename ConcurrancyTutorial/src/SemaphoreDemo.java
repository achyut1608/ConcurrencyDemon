import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	public static void main(String[]args) {
		
		SemaphoreToiletThread toilet = new SemaphoreToiletThread();
		
		Thread t1 = new Thread(toilet);
		Thread t2 = new Thread(toilet);
		Thread t3 = new Thread(toilet);
		
		t1.start();
		t2.start();
		t3.start();
		
	}
}

class SemaphoreToiletThread implements Runnable {
		
	Semaphore semaphore = new Semaphore(1);
	
	@Override
	public void run() {
		try {	
			semaphore.acquire();
			
			String name = Thread.currentThread().getName();
			System.out.println(name + " entering");
			Thread.sleep(100);
			System.out.println(name+ " using");
			Thread.sleep(100);
			System.out.println(name+" coming out");
			Thread.sleep(100);
			System.out.println();
	
			semaphore.release();
				
		} catch(InterruptedException ie) {
				ie.printStackTrace();
		}
	}
	
}
