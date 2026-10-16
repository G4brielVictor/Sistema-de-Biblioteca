package service;

import entities.Member;
import exceptions.MemberAlreadyExistsException;
import exceptions.MemberNotFoundException;
import repository.MemberRepository;

import java.util.List;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember(String name, String id, String email) {
        //IDs
        if(id.isBlank() || id.length()<5 || !id.matches("[0-9]+")) {
            throw new IllegalArgumentException("Invalid identification number");
        }

        if(memberRepository.findMemberById(id) != null) {
            throw new MemberAlreadyExistsException("Member already exists");
        }

        //Names
        if(name.isBlank() || name.length()<3 || !name.matches("[A-Za-z ]+")) {
            throw new IllegalArgumentException("Invalid name");
        }

        //Emails
        if(email.isBlank() || email.indexOf('@') <= 0 || email.lastIndexOf('.') <= email.indexOf('@') || email.length()<5) {
            throw new IllegalArgumentException("Invalid email");
        }

        if(memberRepository.findMemberByEmail(email) != null) {
            throw new MemberAlreadyExistsException("Member with this email already exists");
        }

        Member member = new Member(id, name, email);
        memberRepository.saveMember(member);

        return member;
    }

    public void removeMember(String id) {
        Member member = findById(id);
        if(!member.getBookList().isEmpty()) {
            throw new IllegalArgumentException("Member still have borrowed books");
        }

        memberRepository.removeMember(member);
    }

    public Member findByEmail(String email) {
        Member member = memberRepository.findMemberByEmail(email);
        if(member == null) {
            throw new MemberNotFoundException("Member not found");
        }

        return member;
    }

    public Member findById(String id) {
        Member member = memberRepository.findMemberById(id);
        if(member == null) {
            throw new MemberNotFoundException("Member not found");
        }

        return member;
    }

    public Member findByName(String name) {
        Member member = memberRepository.findMemberByName(name);
        if(member == null) {
            throw new MemberNotFoundException("Member not found");
        }

        return member;
    }

    public List<Member> findAllMembers() {
        List <Member> members = memberRepository.listAllMembers();
        if(members.isEmpty()) {
            throw new MemberNotFoundException("List of members is empty");
        }

        return members;
    }
}
