package gathering.usecase;

import gathering.entity.Gathering;
import gathering.entity.Member;
import gathering.entity.User;
import gathering.usecase.input.GatheringCreationInputData;
import gathering.usecase.input.GatheringInputBoundary;
import gathering.usecase.output.GatheringCreationOutputData;
import gathering.usecase.output.GatheringOutputBoundary;

public class GatheringUseCaseInteractor implements GatheringInputBoundary {

    private final DBGateway dbGateway;

    private final GatheringOutputBoundary outputBoundary;

    public GatheringUseCaseInteractor(DBGateway dbGateway, GatheringOutputBoundary outputBoundary) {
        this.dbGateway = dbGateway;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void createGathering(GatheringCreationInputData input) {
        String gatheringName = input.getGatheringName();
        User establisher = dbGateway.findUserById(input.getEstablisherId());
        String establisherNickname = input.getEstablisherNickname();

        Gathering gathering = new Gathering(gatheringName);
        gathering.addMember(establisher, establisherNickname, Member.Grade.ADMIN);

        dbGateway.createGathering(gathering);

        Member admin = gathering.getAdmin();
        GatheringCreationOutputData output = new GatheringCreationOutputData(
                gathering.getName(),
                gathering.countMembers(),
                admin.getUser().getName(),
                admin.getNickname(),
                gathering.getCreatedAt()
        );
        outputBoundary.showGatheringCreationResult(output);
    }

}
