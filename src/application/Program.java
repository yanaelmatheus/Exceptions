package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Room Number: ");
		Integer number = sc.nextInt();
		System.out.print("Check-in date (dd/mm/yyyy): ");
		Date checkin = sdf.parse(sc.next());
		System.out.print("Check-out date (dd/mm/yyyy): ");
		Date checkout = sdf.parse(sc.next());
		
		Date now = new Date();
		if(checkin.after(checkout)) {
			
			System.out.println("Error in reservation: Check-out date must be after Check-in date");
			
		}else {
			
			Reservation r = new Reservation(number, checkin, checkout);
			System.out.println(r);
			
			System.out.println("Enter data for update the reservation:");
			System.out.print("Check-in date (dd/mm/yyyy): ");
			checkin = sdf.parse(sc.next());
			System.out.print("Check-out date (dd/mm/yyyy): ");
			checkout = sdf.parse(sc.next());
			
			if(checkin.before(now) || checkout.before(now)) {
				System.out.println("Error in reservation: Reservation dates for update must be future dates");
			} else if (checkin.after(checkout)) {
				System.out.println("Error in reservation: Check-out date must be after Check-in date");
			} else {
				r.updateDates(checkin, checkout);
				System.out.println(r);
			}
		}

		sc.close();
	}

}
