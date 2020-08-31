import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureToilet {
	public static void main(String[]args) {
		ExecutorService service = Executors.newFixedThreadPool(3);
		CallableToilet toilet = new CallableToilet();
		for(int i=0;i<3;i++)
			service.submit(toilet);
		
	
	}
}

class CallableToilet implements Callable<Void>{

	@Override
	public Void call() throws Exception {
		synchronized(this) {
			try {
				String name = Thread.currentThread().getName();
				System.out.println(name + " is entering...");
				Thread.sleep(100);
				System.out.println(name + " is using...");
				Thread.sleep(100);
				System.out.println(name + " is exiting...");
				Thread.sleep(100);
				System.out.println();
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		return null;
	}
	
}
