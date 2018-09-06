package goncharenko.organizer.database_class;

//TODO add name comparator
public class EventTypeColor implements StoreableInDatabase, Comparable {
    private String name;
    private String hexValue;

    public EventTypeColor(String name, String hexValue) {
        this.name = name;
        this.hexValue = hexValue;
    }

    public String getName() {
        return name;
    }

    public String getHexValue() {
        return hexValue;
    }

    public int compareTo(Object object) {
        EventTypeColor objectToCompare = (EventTypeColor) object;
        return name.compareTo(objectToCompare.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode() + hexValue.hashCode();
    }
}
