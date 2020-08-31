
public class ProducerConsumer {
	public static void main(String[]args) {
		Product1 product1 = new Product1();
		
		Thread producer = new Thread(new Producer1(product1));
		Thread consumer = new Thread(new Consumer1(product1));
		
		producer.start();
		consumer.start();
	}
}

class Product1 {
	int count;
	boolean isWaiting = false;
	
	synchronized void add(int count) {
		try {
			while(isWaiting) {
				wait();
			}
			this.count = count;
			System.out.println(count+" Added Successfully!!!");
			isWaiting = true;
			notifyAll();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		
	} 
	
	synchronized int get() {
		try {
			while(!isWaiting) {
				wait();
			}
			isWaiting = false;
			notifyAll();
			
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		return count;
	}
	
}


class Producer1 implements Runnable {
	
	Product1 product;
	
	Producer1(Product1 product){
		this.product = product;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			product.add(i);
		}
	}
}

class Consumer1 implements Runnable {
	
	Product1 product;
	
	Consumer1(Product1 product){
		this.product = product;
	}
	
	@Override
	public void run() {
		for(int i=0;i<5;i++) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
			System.out.println(product.get());
		}
	}
}