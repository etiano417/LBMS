package VisitorRegistry;

import java.time.*;
/**
 * Stores data on a single visit.
 */
public class Visit {
    private String visitorID;
    private LocalDate dateOfVisit;
    private LocalTime timeOfArrival;
    private LocalTime timeOfDeparture;
    private boolean ongoing;

    public Visit(String visId, LocalDate visitDate, LocalTime time) {
        visitorID = visId;
        dateOfVisit = visitDate;
        timeOfArrival = time;
        ongoing = true;
    }

    public void setDepartureTime(LocalTime time) {
        timeOfDeparture = time;
        ongoing = false;
    }

    public String getVisitorID() {
        return visitorID;
    }

    public LocalTime getTimeOfArrival(){
        return timeOfArrival;
    }

    public boolean isOngoing() {
        return ongoing;
    }
}
