package Clock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

/**
 * Tracks the system time
 */
public class Clock {
    private long hoursForward;
    private Collection<ClockObserver> observers;
    private LocalTime openTime;
    private LocalTime closeTime;
    
    /**
     * Constructor for Clock
     */
    public Clock(long hoursForward, Collection<ClockObserver> observers, String openTime, String closeTime){
    	this.hoursForward = hoursForward;
        this.observers = observers;
        this.openTime = LocalTime.parse(openTime);		// "08:00:00" -> LocalTime object
        this.closeTime = LocalTime.parse(closeTime);	// "19:00:00" -> LocalTime object
    }
    
    /**
     * Accessor for closeTime
     */
    public LocalTime getCloseTime() {
		return closeTime;
	}

    /**
     * Mutator for closeTime
     */
	public void setCloseTime(LocalTime closeTime) {
		this.closeTime = closeTime;
	}

	 /**
     * Accessor for openTime
     */
	public LocalTime getOpenTime() {
		return openTime;
	}

	/**
     * Mutator for openTime
     */
	public void setOpenTime(LocalTime openTime) {
		this.openTime = openTime;
	}

	
    /**
     * adds hours to the clock's number of hoursForward
     * @param hours the number of hours that should be added
     */
    public void moveForward(int hours){
        hoursForward += hours;
    }

    /**
     * retrieves the current system date
     * @return today's date in the library's clock
     */
    public LocalDate getDate(){
        return LocalDateTime.now().plusHours(hoursForward).toLocalDate();
    }

    /**
     * retrieves the current system time
     * @return today's time in the library's clock
     */
    public LocalTime getTime(){
        return LocalDateTime.now().plusHours(hoursForward).toLocalTime();
    }

    /**
     * retrieves the current system date and time
     * @return today's date and time in the library's clock
     */
    public LocalDateTime getDateTime(){
        return LocalDateTime.now().plusHours(hoursForward);
    }

    public boolean isOpen(){
        return (getTime().compareTo(getOpenTime()) > 0 && getTime().compareTo(getCloseTime()) < 0);
    }

}
