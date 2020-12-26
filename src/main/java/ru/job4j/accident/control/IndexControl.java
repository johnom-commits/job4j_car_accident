package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("accidents", new AccidentService().accidents());
        return "index";
    }
}
