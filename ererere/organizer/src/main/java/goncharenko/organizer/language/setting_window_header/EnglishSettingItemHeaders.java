package goncharenko.organizer.language.setting_window_header;

import java.io.Serializable;

public class EnglishSettingItemHeaders implements ILanguageSettingHeader, Serializable {
    @Override
    public String getLanguageSettingHeader() {
        return "Language:";
    }

    public String getFontSizeSettingHeader() {
        return "Font size:";
    }

    public String getAlertSettingHeader() {
        return "Send alert before events:";
    }

    public String getAlertTimeSettingHeader() {
        return "Notify me before:";
    }

    public String getAlertTimeUnitSettingHeader() {
        return "Time unit:";
    }

    public String[] getAlertTimeUnitSettingHeaderArray() {
        return new String[]{"minute(s)", "hour(s)", "day(s)"};
    }

    public String getSettingTitleHeader() {
        return "Settings";
    }

    public String getStartOnBootSettingHeader() {
        return "Start calendar on system starts:";
    }

    public String getCancelButtonHeader() {
        return "Cancel";
    }

    public String getLoadDefaultButtonHeader() {
        return "Default";
    }

    public String getApplyButtonHeader() {
        return "Apply";
    }

    public String getChangeLanguageInfoLabelHeader(){
        return "Change will be visible after app restart";
    }
}
