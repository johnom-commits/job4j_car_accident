package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(final Model model) {
        List<String> list = new ArrayList<>();
        list.add("Artos");
        list.add("Portos");
        list.add("Aramis");
        model.addAttribute("list", list);
        return "index";
    }
}
