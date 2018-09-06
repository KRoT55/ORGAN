package goncharenko.organizer.database_class;


public class EventType implements StoreableInDatabase,Comparable {
    private String eventTypeName;
    private int eventTypeColorId;

    public EventType(String eventTypeName, int eventTypeColorId) {
        this.eventTypeName = eventTypeName;
        this.eventTypeColorId = eventTypeColorId;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    //alphabetical order by database name
    public int compareTo(EventType eventTypeToCompare) {
        return this.eventTypeName.compareTo(eventTypeToCompare.eventTypeName);
    }

    @Override
    public int hashCode() {
        return eventTypeName.hashCode() + eventTypeColorId;
    }

    public int getEventTypeColorId() {
        return eventTypeColorId;
    }


    public int compareTo(Object o1) {
        EventType objectToCompare = (EventType) o1;
        return this.getEventTypeName().compareTo(objectToCompare.getEventTypeName());
    }
//    //possible useless
//    public boolean equals(Object otherEventType){
//        return Objects.equals(this,otherEventType) &&
//                this.eventTypeName.equals((EventType)otherEventType.getEventTypeName()) &&
//                this.eventTypeColor.equals((EventType)otherEventType.getEventColor());
//    }
}
