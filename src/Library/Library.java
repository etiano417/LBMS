package Library;

import VisitorRegistry.Visitor;
import VisitorRegistry.VisitorRegistry;
import Clock.Clock;
import Clock.ClockObserver;
import java.io.Serializable;

/**
 * In charge of performing opening and closing operations on the system, and for keeping track of if the library is open
 */
public class Library implements ClockObserver, Serializable
{
    private boolean open;
    private Clock clock;
    private VisitorRegistry registry;
    
    public Library(Clock clock, VisitorRegistry registry){
    	this.open = clock.isOpen();
    	this.clock = clock;
    	this.registry = registry;
    }
    
    /**
     * closes and opens the library based on changes in time
     */
    public void update(){
    	if(open)
    	{
    		if( clock.getTime().equals(clock.getCloseTime()) || ( clock.getTime().isAfter(clock.getCloseTime()) && clock.getTime().isBefore(clock.getOpenTime()) ) )
    		{
    			endVisits();
    			updateFines();
    			open = false;
    		}
    	}
    	else
    	{
    		if( clock.getTime().equals(clock.getOpenTime()) || ( clock.getTime().isAfter(clock.getOpenTime()) && clock.getTime().isBefore(clock.getCloseTime()) ) )
    			open = true;
    	}
    	return;
    }

    public boolean isOpen(){
    	return open;
	}

    /**
     * ends all visits currently in progress.
     */
    private void endVisits(){
    	for(Visitor visitor : registry.getVisitors())
    	{
    		registry.endVisit(visitor.getVisitorID(), clock.getTime());
    	}
    }

    /**
     * updates the state of all fines.
     */
    private void updateFines(){
    	// Do Nothing
    	/**
    	 * Given a Collection of Borrowed books, similar to the Collection of Visitors in VisitorRegistry,
    	 * a "for each" loop could be created that would use the advanceDay method of Borrow for each
    	 * instance of Borrow in the Collection. See example implementation below.
    	 * 
    	 * for(Borrow borrow : borrowCollection.getBorrows())
    	 * {
    	 * 		borrow.advanceDay();
    	 * }
    	 * 
    	 */
    }
}
