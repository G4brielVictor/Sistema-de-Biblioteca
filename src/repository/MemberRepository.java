package repository;

import entities.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    private List<Member> members = new ArrayList<>();

    public void removeMember(Member removedMember) {
        members.remove(removedMember);
    }

    public void saveMember(Member member) {
        members.add(member);
    }

    public List<Member> listAllMembers() {
        return members;
    }

    public Member findMemberByEmail(String email) {
        for (Member member : members) {
            if (member.getEmail().equals(email)) {
                return member;
            }
        }
        return null;
    }

    public Member findMemberByName(String name) {
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        return null;
    }

    public Member findMemberById(String id) {
        for (Member member : members) {
            if(member.getIdMember().equals(id)) {
                return member;
            }
        }
        return null;
    }

}
