package com.example.library.service;

import com.example.library.model.Member;
import com.example.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repo;

    public Member add(Member member) {
        return repo.save(member);
    }

    public List<Member> getAll() {
        return repo.findAll();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
