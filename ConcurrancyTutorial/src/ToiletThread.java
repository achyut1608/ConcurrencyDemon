
public class ToiletThread {
	public static void main(String[]args) {
		
		ToiletUsingJoin toiletJoin = new ToiletUsingJoin();
		ToiletUsingSynchronized toiletSynchronized = new ToiletUsingSynchronized();
		//using join
		Thread t1 = new Thread(toiletJoin,"Thread1");
		Thread t2 = new Thread(toiletJoin,"Thread2");
		Thread t3 = new Thread(toiletJoin,"Thread3");
		try {
			
			t1.start();
			t1.join();
		
			t2.start();
			t2.join();
		
			t3.start();
			t3.join();
			
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
		
		Thread t4 = new Thread(toiletSynchronized,"Thread4");
		Thread t5 = new Thread(toiletSynchronized,"Thread5");
		Thread t6 = new Thread(toiletSynchronized,"Thread6");
		
		t4.start();
		t5.start();
		t6.start();
	}
	
}

	
class ToiletUsingJoin implements Runnable {
	
	@Override
	public void run() {
		try {
			String name = Thread.currentThread().getName();
			System.out.println(name + " entered...");
			Thread.sleep(1000);
			System.out.println(name + " using...");
			Thread.sleep(1000);
			System.out.println(name + " exiting...");
			Thread.sleep(1000);
			System.out.println("");
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
}


class ToiletUsingSynchronized implements Runnable {
	
	@Override
	public void run() {
		synchronized(this) {
	
			try {
				String name = Thread.currentThread().getName();
				System.out.println(name + " entered...");
				Thread.sleep(1000);
				System.out.println(name + " using...");
				Thread.sleep(1000);
				System.out.println( name + " exiting...");
				Thread.sleep(1000);
				System.out.println("");
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}