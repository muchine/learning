package gathering.entity;

import gathering.entity.exception.LongGatheringNameException;
import gathering.entity.exception.MemberCountExceedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gathering {

    private static final int MAX_MEMBER_COUNT = 30;

    private static final int MAX_NAME_LENGTH = 10;

    private final String name;

    private final List<Member> members = new ArrayList<>();

    private final Date createdAt;

    public Gathering(String name) {
        this(name, new Date());
    }

    public Gathering(String name, Date createdAt) {
        if (name.length() > MAX_NAME_LENGTH) throw new LongGatheringNameException();

        this.name = name;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Member getAdmin() {
        for (Member member : members) {
            if (member.isAdmin()) return member;
        }

        throw new IllegalStateException("Gathering should have at least one administrator.");
    }

    public int countMembers() {
        return members.size();
    }

    public List<Member> getMembers() {
        return members;
    }

    public void addMember(User user, String nickname, Member.Grade grade) {
        if (countMembers() >= MAX_MEMBER_COUNT) throw new MemberCountExceedException();

        if (grade == Member.Grade.ADMIN) {
            Member admin = getAdmin();
            if (admin != null) throw new IllegalArgumentException("admin already exists");
        }

        Member member = new Member(Member.newId(), user, nickname, grade);
        members.add(member);
    }

    public void removeMemberById(int id) {
        Member member = findMemberById(id);
        if (member != null) members.remove(member);
    }

    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) return member;
        }

        return null;
    }

}
