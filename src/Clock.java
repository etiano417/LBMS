import java.time.LocalTime;
import java.util.Collection;

/**
 * Created by Eric Tiano on 3/13/2017.
 */
public class Clock {
    private int hoursForward;
    private Collection<ClockObserver> observers;
    private LocalTime closeTime;
    private LocalTime openTime;

    /**
     * adds hours to the clock's number of hoursForward
     * @param hours the number of hours that should be added
     */
    public void moveForward(int hours){

    }
}
