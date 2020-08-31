import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureProducerConsumer {
	public static void main(String[]args) {
		Product7 product = new Product7();
		ExecutorService service = Executors.newFixedThreadPool(2);
		Future<Void> producer = service.submit(new Producer7(product));
		Future<Void> consumer = service.submit(new Consumer7(product));
		
	}
}

class Product7 {
	int number;
	boolean isWaiting;
	
	void add(int number) {
		synchronized(this) {
			while(isWaiting) {
				try {
					wait();
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
			}
			this.number = number;
			isWaiting = true;
			System.out.println("Added Successfully!!!");
			notifyAll();
		}
	}
	
	void get() {
		synchronized(this) {
			try {
				while(!isWaiting) {
					wait();
				}
				isWaiting = false;
				System.out.println("Number : "+number);
				notifyAll();
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}

class Producer7 implements Callable<Void>{

	Product7 product;
	
	Producer7(Product7 product){
		this.product = product;
	}
	
	@Override
	public Void call() throws Exception {
		for(int i=0;i<5;i++) {
			product.add(i);
		}
		return null;
	}
	
}

class Consumer7 implements Callable<Void>{

	Product7 product;
	
	Consumer7(Product7 product){
		this.product = product;
	}
	
	@Override
	public Void call() throws Exception {
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(100);
			}catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			product.get();
		}
		return null;
	}
	
}