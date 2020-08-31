
public class PrintNumberUsing3Thread {
	public static void main(String[]args) {
		Table table1 = new Table(1);
		Table table2 = new Table(2);
		Table table0 = new Table(0);
		
		Thread t1 = new Thread(table1);
		Thread t2 = new Thread(table2);
		Thread t3 = new Thread(table0);
		
		t1.start();
		t2.start();
		t3.start();
	}
}

class Table implements Runnable {
	
	int maxNumber = 10;
	static int number=1; 
	int remainder;
	static Object lock = new Object();
	
	
	Table(int remainder){
		this.remainder = remainder;
	}
	
	@Override
	public void run() {
		while(number<maxNumber-1) {
			
			synchronized(lock) {
				
				while(number%3 != remainder) {
					try {
						lock.wait();
					} catch(InterruptedException ie) {
						ie.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName()+" " + number);
				number++;
				lock.notifyAll();
				
			}
		}
	}
	
}