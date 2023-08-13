package com.shop.main.member.controller;


import com.shop.main.member.domain.Member;
import com.shop.main.member.mapper.MemberMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class MemberController {

    private final MemberMapper mapper;

    public MemberController(MemberMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping("/")
    public String goHomepage(){

        return "home";
    }

    @GetMapping("/login")
    public String goLoginPage(){

        return "member/login";
    }

    @PostMapping("/login")
    public String memberLogin(Member member, HttpSession session){
        Member result = mapper.memberLogin(member);
        if(result == null){
            return "member/login";
        }
        session.setAttribute("mvo", result);

        return "redirect:/";
    }

    @GetMapping("/membership")
    public String goMembershipPage(){

        return "member/membership";
    }

    @PostMapping("/membership")
    public String memberRegister(Member member, MultipartFile file) throws IOException {

//        파일 크기 조정하는 로직 추가해야 됨
        if(!file.isEmpty()) {
            //images폴더에 파일 저장하는 로직
            String fullPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images";
            File saveFile = new File(fullPath, file.getOriginalFilename());
            file.transferTo(saveFile);
        }
        member.setMemProfile("/images/" + file.getOriginalFilename());

        int result = mapper.memberRegister(member);

        if(result == 0){
            return "member/membership";
        }

        return "redirect:/";
    }

    @GetMapping("/myPage")
    public String goMyPage(){

        return "member/myPage";
    }

    @PostMapping("myPage")
    public String memberUpdate(){

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String memberLogout(HttpSession session){
        session.invalidate();

        return "redirect:/";
    }
}
