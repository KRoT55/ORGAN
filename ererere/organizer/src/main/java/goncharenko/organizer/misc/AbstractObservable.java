package goncharenko.organizer.misc;

import java.util.ArrayList;

public abstract class AbstractObservable implements Observable {

    private ArrayList<Observer> observers;

    public AbstractObservable(){
        observers=new ArrayList<>();
    }

    public void registerObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public abstract void notifyObservers();

    protected ArrayList<Observer> getObserverList(){return observers;}
}
