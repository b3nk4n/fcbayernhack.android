package fcb.de.fcbayernemotions.model;

/**
 * A simple fan emotion model.
 */
public class EmotionModel {

    public enum ResourceType {
        IMG, GIF
    }

    private String timeString;
    private String extraText;
    private int resourceId;
    private ResourceType resourceType;
    private int isfav;
    private int isturned;

    public EmotionModel(String time, String extra, int resId, ResourceType resType) {
        setTimeString(time);
        setExtraText(extra);
        setResourceId(resId);
        setResourceType(resType);
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

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }
}
