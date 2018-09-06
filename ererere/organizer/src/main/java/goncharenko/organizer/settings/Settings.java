package goncharenko.organizer.settings;

import goncharenko.organizer.Alert;
import goncharenko.organizer.language.AppLanguage;
import goncharenko.organizer.language.Language;
import goncharenko.organizer.language.main_window_header.IMainLanguageHeader;
import goncharenko.organizer.language.setting_window_header.ILanguageSettingHeader;

import java.io.*;

public class Settings implements Serializable {

    private String languageSetting;
    private Alert alert;
    private int fontSizeSetting;
    private boolean startOnSystemBootSetting;

    private String timeUnitSetting;

    private Language language;
    private AppLanguage currentChoosenLanguage;

    public Settings() {
        alert = new Alert();//?
        language = new Language();
    }

    public void setLanguage(AppLanguage language) {
        this.language.setLanguage(language);
        currentChoosenLanguage=language;
    }

    public AppLanguage getLanguageSetting() {
        return currentChoosenLanguage;
    }

    public ILanguageSettingHeader getSettingsLanguageHeader() {
        return language.getSettingItemHeader();
    }

    public IMainLanguageHeader getMainLanguageHeader() {
        return language.getMainItemHeader();
    }

    public void setAlertSetting(boolean option) {
        alert.setAlertSetting(option);
    }

    public boolean getAlertSetting() {
        return alert.getAlertSetting();
    }

    public void setAlertTimeSetting(int time) {
        alert.setAlertTimeSetting(time);
    }

    public int getAlertTimeSetting() {
        return alert.getAlertTimeSetting();
    }

    public void setFontSizeSetting(int size) {
        this.fontSizeSetting = size;
    }

    public int getFontSizeSetting() {
        return fontSizeSetting;
    }

    public void setStartOnSystemBootSetting(boolean option) {
        this.startOnSystemBootSetting = option;
    }

    public boolean getStartOnBootSetting() {
        return startOnSystemBootSetting;
    }

    public String[] getAvailableLanguage() {
        return AppLanguage.getAvailableLanguage();
    }

    public String getAlertTimeUnitSetting() {
        return timeUnitSetting;
    }

    public void setAlertTimeUnitSetting(String unit) {
        this.timeUnitSetting = unit;
    }

    public void loadDefaultSettings() {
        language.setDefault();
        currentChoosenLanguage=AppLanguage.ENGLISH;
        setAlertSetting(false);
        setAlertTimeSetting(0);
        setAlertTimeUnitSetting(language.getSettingItemHeader().getAlertTimeUnitSettingHeaderArray()[0]);
        setFontSizeSetting(14);
        setStartOnSystemBootSetting(false);
    }
}
