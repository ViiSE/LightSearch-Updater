package lightsearch.updater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

    @GetMapping("/app")
    public ModelAndView app() {
        return new ModelAndView("redirect:/app/app.html");
    }
}
