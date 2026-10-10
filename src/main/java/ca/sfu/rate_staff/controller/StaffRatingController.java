package ca.sfu.rate_staff.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import ca.sfu.rate_staff.repository.StaffRatingRepository;
import org.springframework.stereotype.Controller;

@Controller
public class StaffRatingController {

    private final StaffRatingRepository repository;

    public StaffRatingController(StaffRatingRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("ratings", repository.findAll());
        return "index";
    }
}
