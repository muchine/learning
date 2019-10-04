package gathering.controller;

import gathering.usecase.input.GatheringCreationInputData;
import gathering.usecase.input.GatheringInputBoundary;

public class GatheringController {

    private final GatheringInputBoundary inputBoundary;

    private UserSession userSession;

    public GatheringController(GatheringInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void doGatheringCreationUseCase(String gatheringName, String establisherNickname) {
        int establisherId = userSession.getLogedInUserId();

        GatheringCreationInputData input = new GatheringCreationInputData(
                gatheringName,
                establisherId,
                establisherNickname
        );

        inputBoundary.createGathering(input);
    }

}
