package goncharenko.organizer.database_class.event;

public class EventName {
    private String name, description;

    public EventName(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public EventName(String name) {
        this(name, "");
    }

    public String getEventName() {
        return name;
    }

    public String getEventDescription() {
        return description;
    }

    public int hashCode() {
        return name.hashCode() + description.hashCode();
    }

    public String toString() {
        return name + " " + description;
    }
}
