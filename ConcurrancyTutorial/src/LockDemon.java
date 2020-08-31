import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemon {
	public static void main(String[]args) {
		
		ToiletLock toilet = new ToiletLock();
		
		Thread t1 = new Thread(toilet);
		Thread t2 = new Thread(toilet);
		Thread t3 = new Thread(toilet);
		
		t1.start();
		t2.start();
		t3.start();
	
	}
}

class ToiletLock implements Runnable {
	Lock lock = new ReentrantLock();
	
	@Override
	public void run() {
		try {
			lock.lock();
			String name = Thread.currentThread().getName();
			System.out.println(name + " is entering");
			Thread.sleep(100);
			System.out.println(name +" is using");
			Thread.sleep(100);
			System.out.println(name + " is exiting");
			Thread.sleep(100);
			System.out.println();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
}