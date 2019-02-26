package ApacheCommonCsv;

/**
 * author: dulei
 * date: 18-4-28
 * desc: Audience Bean
 */
public class Audience {
    public String dmpId;
    public String dmpName;
    public String audienceId;
    public String audienceName;
    public String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Audience(String dmpId, String dmpName, String audienceId, String audienceName, String tag) {
        this.dmpId = dmpId;
        this.dmpName = dmpName;
        this.audienceId = audienceId;
        this.audienceName = audienceName;
        this.tag = tag;
    }

    public String getDmpId() {
        return dmpId;
    }

    public void setDmpId(String dmpId) {
        this.dmpId = dmpId;
    }

    public String getDmpName() {
        return dmpName;
    }

    public void setDmpName(String dmpName) {
        this.dmpName = dmpName;
    }

    public String getAudienceId() {
        return audienceId;
    }

    public void setAudienceId(String audienceId) {
        this.audienceId = audienceId;
    }

    public String getAudienceName() {
        return audienceName;
    }

    public void setAudienceName(String audienceName) {
        this.audienceName = audienceName;
    }

    @Override
    public String toString() {
        return "Audience{" +
                "dmpId='" + dmpId + '\'' +
                ", dmpName='" + dmpName + '\'' +
                ", audienceId='" + audienceId + '\'' +
                ", audienceName='" + audienceName + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
