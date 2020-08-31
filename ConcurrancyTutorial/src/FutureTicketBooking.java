import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

public class FutureTicketBooking {
	public static void main(String[]args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		FutureTicket ticket = new FutureTicket();
		Future<Void> result=null;
		
		for(int i=0;i<3;i++)
			result = service.submit(ticket);
		
		
	}
}


class FutureTicket implements Callable<Void>{
	int totalTicket;
	int bookTicket;
	Scanner scan = new Scanner(System.in);
	
	FutureTicket(){
		
		System.out.println("Enter total Ticket");
		totalTicket = scan.nextInt();
	}
	Semaphore semaphore = new Semaphore(1);
	@Override
	public Void call() throws Exception {
		semaphore.acquire();
		System.out.println("how many tickets do you want to book");
		bookTicket = scan.nextInt();
		
		if(bookTicket<=totalTicket) {
			totalTicket -= bookTicket;
			System.out.println("Ticket book successfully");
		} else 
			System.out.println("ticket not booked");
		semaphore.release();
		return null;
	}
	
}