package goncharenko.organizer.language;

import goncharenko.organizer.language.main_window_header.EnglishMainItemHeader;
import goncharenko.organizer.language.main_window_header.IMainLanguageHeader;
import goncharenko.organizer.language.main_window_header.RussianMainItemHeader;
import goncharenko.organizer.language.setting_window_header.EnglishSettingItemHeaders;
import goncharenko.organizer.language.setting_window_header.ILanguageSettingHeader;
import goncharenko.organizer.language.setting_window_header.RussianSettingItemHeaders;

import java.io.Serializable;

public final class Language implements Serializable{
    private ILanguageSettingHeader settingsItemLanguage;
    private IMainLanguageHeader mainItemLanguage;

    public Language() {
        setDefault();
    }

    public void setLanguage(AppLanguage language) {
        switch (language) {
            case ENGLISH:
                mainItemLanguage = new EnglishMainItemHeader();
                settingsItemLanguage = new EnglishSettingItemHeaders();
                break;
            case RUSSIAN:
                mainItemLanguage = new RussianMainItemHeader();
                settingsItemLanguage = new RussianSettingItemHeaders();
                break;
        }
    }

    public void setDefault(){
        setLanguage(AppLanguage.ENGLISH);
    }

    public ILanguageSettingHeader getSettingItemHeader(){
        return settingsItemLanguage;
    }
    public IMainLanguageHeader getMainItemHeader(){
        return mainItemLanguage;
    }
}
