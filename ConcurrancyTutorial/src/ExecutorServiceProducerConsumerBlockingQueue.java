import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class ExecutorServiceProducerConsumerBlockingQueue {
	public static void main(String[]args) {
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		
		
		  ExecutorService service = Executors.newFixedThreadPool(2);
		  service.execute(new Producer6(queue)); 
		  service.execute(new Consumer6(queue));
		 
		
		
	}
	
}



class Producer6 implements Runnable {
	
BlockingQueue<Integer> queue;
	
	Producer6(BlockingQueue<Integer> queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(100);
				queue.put(i);
				System.out.println("Added Successfully!!!");
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			
		}
	}
}

class Consumer6 implements Runnable {
	BlockingQueue<Integer> queue;
	
	Consumer6(BlockingQueue<Integer>queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			try {
				int item = queue.take();
				System.out.println(item);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}
