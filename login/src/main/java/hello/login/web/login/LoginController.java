package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form,
                        BindingResult bindingResult,
                        HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Member loginmember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginmember == null){
            bindingResult.reject("loginfail", "아이디 또는 비밀번호가 맞지 않습니다");
            return "login/loginForm";
        }

        //로그인 성공 처리
        //쿠키에 시간정보를 주지 않으면 세션쿠키(브라우저 종료시 종료)
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginmember.getId()));
        response.addCookie(idCookie);


        return "redirect:/";
    }
}
