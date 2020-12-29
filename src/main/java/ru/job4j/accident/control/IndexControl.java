package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;

@Controller
public class IndexControl {
    private AccidentService service;

    @Autowired
    public IndexControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("accidents", new AccidentService().accidents());
        return "index";
    }
}
