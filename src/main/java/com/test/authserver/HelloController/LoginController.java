package com.test.authserver.HelloController;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(required = false) Object error,
                        @RequestParam(required = false) Object logout,
                        Model model) {
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return "login-page";
    }

    @PostMapping("login/success")
    public ModelAndView successLogin() {
        String redirectUrl = "http://localhost:4200/gip-gip";
        RedirectView vf = new RedirectView(redirectUrl);
        vf.setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
        ModelAndView md = new ModelAndView(vf);
        md.addObject("first-param", "hello"); //Add your params here
        md.addObject("second-param", "myfriend"); //Add your params here
        return md;
    }
}
