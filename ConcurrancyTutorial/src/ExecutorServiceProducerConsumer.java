import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ExecutorServiceProducerConsumer {
	public static void main(String[]args) {
		Product5 product = new Product5();
		
		
		ExecutorService service = Executors.newFixedThreadPool(5);
		service.execute(new Producer5(product));
		service.execute(new Consumer5(product));
	}
}

class Product5 {
	Semaphore producer = new Semaphore(1);
	Semaphore consumer = new Semaphore(0);
	int number;
	
	void add(int number) {
		try {
			producer.acquire();
			this.number = number;
			System.out.println(number + " is added successfully!!!");
			consumer.release();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	void get() {
		try {
			consumer.acquire();
			System.out.println(number);
			producer.release();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
}

class Producer5 implements Runnable {
	
	Product5 product5;
	
	Producer5(Product5 product5){
		this.product5 = product5;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			product5.add(i);
		}
	}
}

class Consumer5 implements Runnable {
	
	Product5 product5;
	
	Consumer5(Product5 product5){
		this.product5 = product5;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			product5.get();
			
			
		}
	}
}