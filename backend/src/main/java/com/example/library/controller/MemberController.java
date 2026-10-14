package com.example.library.controller;

import com.example.library.model.Member;
import com.example.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @PostMapping
    public Member add(@RequestBody Member member) {
        return service.add(member);
    }

    @GetMapping
    public List<Member> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
