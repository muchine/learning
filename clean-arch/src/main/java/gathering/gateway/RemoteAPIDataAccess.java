package gathering.gateway;

import gathering.entity.Gathering;
import gathering.entity.Member;
import gathering.entity.User;
import gathering.usecase.DBGateway;

public class RemoteAPIDataAccess implements DBGateway {

    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public void createGathering(Gathering gathering) {
        // Convert gathering into JSON format
        // Invoke remote API
    }

    @Override
    public void updateGathering(Gathering gathering) {

    }

    @Override
    public void addGatheringMember(Gathering gathering, Member member) {

    }

    @Override
    public Gathering findGatheringById(int id) {
        return null;
    }

}
