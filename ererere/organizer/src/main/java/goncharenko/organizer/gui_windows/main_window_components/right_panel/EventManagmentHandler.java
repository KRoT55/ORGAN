package goncharenko.organizer.gui_windows.main_window_components.right_panel;

import goncharenko.organizer.gui_windows.main_window_components.EventManagmentAction;

import java.util.ArrayList;

public class EventManagmentHandler {

    private static EventManagmentHandler instance=null;
    private final ArrayList<EventManagmentListener> listeners;

    public static EventManagmentHandler getInstance(){
        if(instance==null) instance=new EventManagmentHandler();
        return instance;
    }

    private EventManagmentHandler(){
        listeners=new ArrayList<>();
    }

    public void registerNewListener(EventManagmentListener listener){
        listeners.add(listener);
    }

    public void notifyListeners(EventManagmentAction action){
        for(EventManagmentListener listener:listeners){
            listener.updateEventManagmentListener(action);
        }
    }
}
