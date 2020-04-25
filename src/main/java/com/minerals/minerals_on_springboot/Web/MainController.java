package com.minerals.minerals_on_springboot.Web;

import com.minerals.minerals_on_springboot.Config.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("auth", Authentication.getAuth().isAuth());
        return "index";
    }
    @GetMapping("/feedback")
    public String feedback(Model model){
        model.addAttribute("auth", Authentication.getAuth().isAuth());
        return "feedback";
    }
    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("auth", Authentication.getAuth().isAuth());
        return "about";
    }
}
