package goncharenko.organizer.factories;

import goncharenko.organizer.database_class.event.Event;

/**
 * Created by mario on 13.03.17.
 */
public class EventFactory {

    private static EventFactory instance;

    private EventFactory(){}

    public EventFactory getFactory(){
        if(instance==null)
            instance = new EventFactory();
        return instance;
    }

    public Event createEvent(){
        //TODO
        return null;
    }
}
