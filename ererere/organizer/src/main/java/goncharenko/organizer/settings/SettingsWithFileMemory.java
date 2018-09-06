package goncharenko.organizer.settings;

import goncharenko.organizer.misc.FileMemory;

public class SettingsWithFileMemory {
    private Settings settings;
    private FileMemory settingMemory;

    public SettingsWithFileMemory(){
        settingMemory = new FileMemory("settings.dat");
    }


    public Settings getSettingsObject(){
        //loadFromFile();
        return settings;
    }

    public void loadFromFile() {
        settings = (Settings) settingMemory.loadFromFile();
        if (settings == null) {
            initDefaultSettings();
        }
    }

    private void initDefaultSettings() {
        settings=new Settings();
        settings.loadDefaultSettings();
        saveToFile();
    }

    public void saveToFile() {
        settingMemory.saveToFile(settings);
    }
}
