package gathering.usecase.input;

public class GatheringCreationInputData {

    private final String gatheringName;

    private final int establisherId;

    private final String establisherNickname;

    public GatheringCreationInputData(String gatheringName, int establisherId, String establisherNickname) {
        this.gatheringName = gatheringName;
        this.establisherId = establisherId;
        this.establisherNickname = establisherNickname;
    }

    public String getGatheringName() {
        return gatheringName;
    }

    public int getEstablisherId() {
        return establisherId;
    }

    public String getEstablisherNickname() {
        return establisherNickname;
    }
}
