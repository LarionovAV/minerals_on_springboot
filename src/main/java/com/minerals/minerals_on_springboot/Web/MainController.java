package com.minerals.minerals_on_springboot.Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }
    @GetMapping("/feedback")
    public String feedback(Model model){
        return "feedback";
    }
    @GetMapping("/about")
    public String about(Model model){
        return "about";
    }
}
