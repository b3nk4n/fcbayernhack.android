package fcb.de.fcbayernemotions.model;

/**
 * A simple fan emotion model.
 */
public class EmotionModel {

    private String timeString;
    private String extraText;
    private int imageResourceId;
    private int isfav;
    private int isturned;

    public EmotionModel(String time, String extra, int imageResourceId) {
        setTimeString(time);
        setExtraText(extra);
        setImageResourceId(imageResourceId);
    }

    public int getIsturned() {
        return isturned;
    }

    public void setIsturned(int isturned) {
        this.isturned = isturned;
    }

    public int getIsfav() {
        return isfav;
    }

    public void setIsfav(int isfav) {
        this.isfav = isfav;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public String getExtraText() {
        return extraText;
    }

    public void setExtraText(String extraText) {
        this.extraText = extraText;
    }

    public boolean hasExtraText() {
        return this.extraText != null && !this.extraText.equals("");
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
