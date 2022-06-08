package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	
	private Integer roomNumber;
	private Date checkin;
	private Date checkout;
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
	
	public Reservation(Integer roomNumber, Date checkin, Date checkout) throws DomainException{
		
		if (checkin.after(checkout)) {
			 throw new DomainException("Check-out date must be after Check-in date");
		}
		
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public Long duration() {
		Long dif = checkout.getTime() - checkin.getTime();
		return TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkin, Date checkout) throws DomainException{
		
		Date now = new Date();
		
		if(checkin.before(now) || checkout.before(now)) {
			 throw new DomainException("Reservation dates for update must be future dates");
		} 
		if (checkin.after(checkout)) {
			 throw new DomainException("Check-out date must be after Check-in date");
		}
		
		this.checkin = checkin;
		this.checkout = checkout;
	}

	@Override
	public String toString() {
		return "Reservation: Room " + roomNumber + ", Check-in: " + sdf.format(checkin) + ", Check-out: " + sdf.format(checkout) + ", " + duration() + " nights";
	}
}
