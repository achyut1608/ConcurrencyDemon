import java.util.Scanner;

public class TicketBooking {
	public static void main(String[]args) {
		
		Ticket ticket = new Ticket();
		
		Thread thread1 = new Thread(new Booking(ticket));
		Thread thread2 = new Thread(new Booking(ticket));
		Thread thread3 = new Thread(new Booking(ticket));
	
		thread1.start();
		thread2.start();
		thread3.start();
	}
}

class Ticket {
	
	int totalTicketAvailable = 10;
	Scanner scan = new Scanner(System.in);
	
	Ticket(){
		System.out.println("Enter total Number of ticket");
		totalTicketAvailable = scan.nextInt();
	}
	
	void book() {
		synchronized(this) {
			String name = Thread.currentThread().getName();
			System.out.println("Enter number of ticket do you want to book");
			int book = scan.nextInt();
			
			if(book>totalTicketAvailable)
				System.out.println("sorry ticket is not available");
			else {
				totalTicketAvailable -= book;
				System.out.println(name + " ticket booked successfully");
			}
		}
		
	}
}

class Booking implements Runnable {
	
	Ticket ticket;
	
	Booking(Ticket ticket){
		this.ticket = ticket;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(100);
			ticket.book();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}
