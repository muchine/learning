package gathering.presenter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GatheringCreationViewModel {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");

    private final String gatheringName;

    private final String adminName;

    private final String createdAt;

    private final boolean closeButtonEnabled = true;

    private final String closeButtonName = "Close";

    public GatheringCreationViewModel(String gatheringName, String adminName, Date createdAt) {
        this.gatheringName = gatheringName;
        this.adminName = adminName;
        this.createdAt = dateFormat.format(createdAt);
    }

    public String getGatheringName() {
        return gatheringName;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isCloseButtonEnabled() {
        return closeButtonEnabled;
    }

    public String getCloseButtonName() {
        return closeButtonName;
    }
}
