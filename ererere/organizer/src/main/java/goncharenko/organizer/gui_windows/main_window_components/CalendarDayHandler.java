package goncharenko.organizer.gui_windows.main_window_components;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarDayHandler {

    private LocalDate chosenDate;
    private ArrayList<CalendarListener> listeners;

    private static CalendarDayHandler instance=null;

    public static CalendarDayHandler getInstance(){
        if(instance==null) instance=new CalendarDayHandler();
        return instance;
    }
    private CalendarDayHandler(){
        this.listeners=new ArrayList<>();
    }

    public void handleDay(LocalDate date){
        this.chosenDate=date;
        notifyListeners();
    }

    public void registerNewListener(CalendarListener listener){
        listeners.add(listener);
    }

    private void notifyListeners(){
        for(CalendarListener listener:listeners){
            listener.updateCalendarListener(chosenDate);
        }
    }
}
