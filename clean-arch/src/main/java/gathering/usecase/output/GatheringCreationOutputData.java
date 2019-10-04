package gathering.usecase.output;

import java.util.Date;

public class GatheringCreationOutputData {

    private final String gatheringName;

    private final int memberCount;

    private final String adminName;

    private final String adminNickname;

    private final Date createdAt;

    public GatheringCreationOutputData(
            String gatheringName,
            int memberCount,
            String adminName,
            String adminNickname,
            Date createdAt) {
        this.gatheringName = gatheringName;
        this.memberCount = memberCount;
        this.adminName = adminName;
        this.adminNickname = adminNickname;
        this.createdAt = createdAt;
    }

    public String getGatheringName() {
        return gatheringName;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminNickname() {
        return adminNickname;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
