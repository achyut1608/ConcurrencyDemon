import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExecutorServiceToilet {
	public static void main(String[]args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		Future<Long> future = service.submit(new Factorial());
		try {
			System.out.println(future.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}

class Factorial implements Callable<Long>{

	@Override
	public Long call() throws Exception {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter number to get Factorial");
		int number = scan.nextInt();
		long result = 1;
		while(number!=0) {
			result *= number;
			number--;
			try {
				Thread.sleep(100);
			}catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		return result;
	}
	
}