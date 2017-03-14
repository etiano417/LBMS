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
    private LocalTime closeTime;
    private LocalTime openTime;

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

}
