package VisitorRegistry;

import java.time.*;
/**
 * Stores data on a single visit.
 */
public class Visit {
    private long visitorID;
    private LocalDate dateOfVisit;
    private LocalTime timeOfArrival;
    private LocalTime timeOfDeparture;
    private boolean ongoing;

    public Visit(long visId, LocalDate visitDate, LocalTime time) {
        visitorID = visId;
        dateOfVisit = visitDate;
        timeOfArrival = time;
        ongoing = true;
    }

    public void setDepartureTime(LocalTime time) {
        timeOfDeparture = time;
        ongoing = false;
    }

    public long getVisitorID() {
        return visitorID;
    }

    public boolean isOngoing() {
        return ongoing;
    }
}
