package goncharenko.organizer;

import java.io.Serializable;

public class Alert implements Serializable{
    private boolean alertSetting;
    private int alertTimeSetting;

    public Alert() {
    }

    public void setAlertSetting(boolean option) {

        this.alertSetting = option;
    }

    public boolean getAlertSetting() {

        return alertSetting;
    }

    public void setAlertTimeSetting(int time) {

        this.alertTimeSetting = time;
    }

    public int getAlertTimeSetting() {

        return alertTimeSetting;
    }
}
