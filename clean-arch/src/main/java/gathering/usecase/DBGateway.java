package gathering.usecase;

import gathering.entity.Gathering;
import gathering.entity.Member;
import gathering.entity.User;

public interface DBGateway {

    User findUserById(int id);

    void createGathering(Gathering gathering);

    void updateGathering(Gathering gathering);

    void addGatheringMember(Gathering gathering, Member member);

    Gathering findGatheringById(int id);

}
