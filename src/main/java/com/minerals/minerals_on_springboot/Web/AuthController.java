package com.minerals.minerals_on_springboot.Web;

import com.minerals.minerals_on_springboot.Config.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    Authentication auth = Authentication.getAuth();

    @GetMapping("/login")
    public String goToAuthForm(
            Model model
    ){
        if (auth.isAuth())
            auth.setAuth(false);

        model.addAttribute("auth", auth.isAuth());

        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("pwd") String gotPwd
    ){
        if (auth.checkAuth(gotPwd))
            return "redirect:/";
        else
            return "redirect:/login";
    }
}
