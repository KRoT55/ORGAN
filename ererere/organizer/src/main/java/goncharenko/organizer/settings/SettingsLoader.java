package goncharenko.organizer.settings;

public class SettingsLoader {

    private static SettingsWithFileMemory settingsWithFileMemory;

    public static Settings loadSettings() {
        settingsWithFileMemory = new SettingsWithFileMemory();
        settingsWithFileMemory.loadFromFile();
        return settingsWithFileMemory.getSettingsObject();
    }
}
