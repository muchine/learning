package gathering.gateway;

import gathering.entity.Gathering;
import gathering.entity.Member;
import gathering.entity.User;
import gathering.usecase.DBGateway;

public class MySQLDataAccess implements DBGateway {

    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public void createGathering(Gathering gathering) {
        insertGatheringRecord(gathering);

        for (Member member: gathering.getMembers()) {
            insertGatheringMemberRecord(member);
        }
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

    private void insertGatheringRecord(Gathering gathering) {
        // Create SQL from the domain entity
        String sql = "insert into gatherings ...";

        // Execute SQL on MySQL database
    }

    private void insertGatheringMemberRecord(Member member) {
        String sql = "insert into gathering_members ...";
    }


}
