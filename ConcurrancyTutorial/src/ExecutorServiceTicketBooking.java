import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ExecutorServiceTicketBooking {
	public static void main(String[]args) {
		Ticket1 ticket = new Ticket1();
		
		ExecutorService service = Executors.newFixedThreadPool(3);
		
		for(int i=0;i<3;i++)
			service.execute(ticket);
		
	}
	
}

class Ticket1 implements Runnable {
	Scanner scan;
	int totalTicket;
	
	Ticket1(){
		scan = new Scanner(System.in);
		System.out.println("Enter total number of ticket");
		totalTicket = scan.nextInt();
	}
	Semaphore semaphore = new Semaphore(1);
	@Override
	public void run() {
			
		try {
			semaphore.acquire();
			System.out.println("Enter number of ticket do you want to book");
			int book = scan.nextInt();
			String name = Thread.currentThread().getName();
			if(totalTicket>=book) {
				totalTicket -= book;
				System.out.println(name + " booked successfully!!!");
			} else {
				System.out.println("sorry can't book ticket!!! ticket not available ");
			}
			semaphore.release();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	
	}
	
	
	
}