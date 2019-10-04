package gathering.presenter;

import gathering.usecase.output.GatheringCreationOutputData;
import gathering.usecase.output.GatheringOutputBoundary;

public class GatheringPresenter implements GatheringOutputBoundary {

    private final GatheringPresenterView presenterView;

    public GatheringPresenter(GatheringPresenterView presenterView) {
        this.presenterView = presenterView;
    }

    @Override
    public void showGatheringCreationResult(GatheringCreationOutputData output) {
        GatheringCreationViewModel viewModel = new GatheringCreationViewModel(
                output.getGatheringName(),
                output.getAdminName(),
                output.getCreatedAt()
        );

        presenterView.showGatheringCreationResult(viewModel);
    }

}
