import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerUsingBlockingQueue {
	public static void main(String[]args) {
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		
		Thread producer2 = new Thread(new Producer2(queue));
		Thread consumer2 = new Thread(new Consumer2(queue));
		
		producer2.start();
		consumer2.start();
		
	}
}


class Producer2 implements Runnable {

	BlockingQueue<Integer> queue;
	
	Producer2(BlockingQueue<Integer> queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(100);
				queue.put(i);
				System.out.println(i+" Added successfully!!!");
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
	
}


class Consumer2 implements Runnable {
	
	BlockingQueue<Integer> queue;
	
	Consumer2(BlockingQueue<Integer>queue){
		this.queue = queue;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(100);
				System.out.println(queue.take());
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			
		}
	}
	
}