package lightsearch.updater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManController {

    @GetMapping("/man")
    public ModelAndView app() {
        return new ModelAndView("redirect:/man/man.html");
    }
}
