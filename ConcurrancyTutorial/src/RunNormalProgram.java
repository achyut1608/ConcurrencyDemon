
public class RunNormalProgram {
	public static void main(String[]args) {
		Thread1 t1 = new Thread1();
		t1.start();
		
		System.out.println("running in main thread");
	}
}

class Thread1 extends Thread{
	
	
	@Override
	public void run() {
		System.out.println("in new thread");
	}
}
