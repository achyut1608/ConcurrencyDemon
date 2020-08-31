import java.util.concurrent.Semaphore;

public class SemaphoreProducerConsumer {
	public static void main(String[]args) {
		Product3 product = new Product3();
		
		Thread t1 = new Thread(new Producer3(product));
		Thread t2 = new Thread(new Consumer3(product));
		
		t1.start();
		t2.start();
		
	}
}


class Product3 {
	
	Semaphore producer = new Semaphore(1);
	Semaphore consumer = new Semaphore(0);
	
	int number;
	boolean isWaiting;
	
	void add(int number) {
		try {
			producer.acquire();
			
			this.number = number;
			System.out.println("Added Successfully!!!");
			consumer.release();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	void get() {
		try {
			consumer.acquire();
			Thread.sleep(100);
			System.out.println(number+" printing");
			producer.release();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		
	}
}

class Producer3 implements Runnable {
	Product3 product3;
	
	Producer3(Product3 product3){
		this.product3 = product3;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			product3.add(i);
		}
	}
	
}

class Consumer3 implements Runnable {
	
	Product3 product3;
	
	Consumer3(Product3 product3){
		this.product3 = product3;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			product3.get();
		}
	}
	
}