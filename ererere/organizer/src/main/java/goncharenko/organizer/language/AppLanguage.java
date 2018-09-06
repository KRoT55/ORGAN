package goncharenko.organizer.language;

public enum AppLanguage {
    ENGLISH, RUSSIAN;

    public static String[] getAvailableLanguage(){
        return new String[]{"English", "Russian"};
    }
};