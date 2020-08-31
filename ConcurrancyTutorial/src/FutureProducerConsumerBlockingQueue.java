import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class FutureProducerConsumerBlockingQueue {
	public static void main(String[]args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		
		Future<Void> producer = service.submit(new Producer8(queue));
		Future<Void> consumer = service.submit(new Consumer8(queue));
	}
}



class Producer8 implements Callable<Void> {
	
	BlockingQueue<Integer> queue;
	
	Producer8(BlockingQueue<Integer> queue){
		this.queue = queue;
	}

	@Override
	public Void call() throws Exception {
		
		
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(100);
				queue.put(i);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			System.out.println(i+ " Added Successfully!!!");
		}
			
		
		
		return null;
	}
	
}

class Consumer8 implements Callable<Void> {

	BlockingQueue<Integer> queue;
	
	Consumer8(BlockingQueue<Integer> queue){
		this.queue = queue;
	}
	
	@Override
	public Void call() throws Exception {
		for(int i=0;i<5;i++) {
			try {
				int item = queue.take();
				System.out.println(item);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		
		return null;
	}
	
}
