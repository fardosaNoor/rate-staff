package ca.sfu.rate_staff.controller;

import ca.sfu.rate_staff.model.RoleType;
import ca.sfu.rate_staff.model.StaffRating;
import ca.sfu.rate_staff.repository.StaffRatingRepository;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StaffRatingController {

    private final StaffRatingRepository repository;

    public StaffRatingController(StaffRatingRepository repository) {
        this.repository = repository;
    }

    // Display the home page and all saved staff ratings.
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("ratings", repository.findAll());
        return "index";
    }

    // Display one staff member's details.
    @GetMapping("/ratings/{id}")
    public String viewRating(@PathVariable Long id, Model model) {
        StaffRating rating = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));

        model.addAttribute("rating", rating);
        return "rating-details";
    }

    // Display an empty form for creating a rating.
    @GetMapping("/ratings/new")
    public String showCreateForm(Model model) {
        model.addAttribute("rating", new StaffRating());
        model.addAttribute("roles", RoleType.values());
        return "rating-form";
    }

    // Display an existing rating in the form for editing.
    @GetMapping("/ratings/{id}/edit")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        StaffRating rating = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));

        model.addAttribute("rating", rating);
        model.addAttribute("roles", RoleType.values());
        return "rating-form";
    }

    // Create a new staff rating.
    @PostMapping("/ratings")
    public String createRating(
            @Valid @ModelAttribute("rating") StaffRating rating,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (!result.hasErrors() && repository.existsByEmail(rating.getEmail())) {
        result.rejectValue(
                "email",
                "duplicate",
                "This email is already used for another staff rating."
        );
    }

        if (result.hasErrors()) {
            model.addAttribute("roles", RoleType.values());
            return "rating-form";
        }

        repository.save(rating);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Staff rating added successfully!"
        );

        return "redirect:/";
    }

    // Update an existing staff rating.
    @PostMapping("/ratings/{id}")
    public String updateRating(
            @PathVariable Long id,
            @Valid @ModelAttribute("rating") StaffRating formRating,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        StaffRating existingRating = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));

            if (!result.hasErrors()
            && repository.existsByEmailAndIdNot(formRating.getEmail(), id)) {
        result.rejectValue(
                "email",
                "duplicate",
                "This email is already used for another staff rating."
        );
    }

    if (result.hasErrors()) {
        formRating.setId(id);
        model.addAttribute("roles", RoleType.values());
        return "rating-form";
    }

        existingRating.setName(formRating.getName());
        existingRating.setEmail(formRating.getEmail());
        existingRating.setRoleType(formRating.getRoleType());
        existingRating.setClarityScore(formRating.getClarityScore());
        existingRating.setNicenessScore(formRating.getNicenessScore());
        existingRating.setKnowledgeableScore(
                formRating.getKnowledgeableScore());
        existingRating.setComment(formRating.getComment());

        repository.save(existingRating);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Staff rating updated successfully!"
        );

        return "redirect:/";
    }

    // Delete an existing staff rating.
    @PostMapping("/ratings/{id}/delete")
    public String deleteRating(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        StaffRating rating = repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));

        repository.delete(rating);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Staff rating deleted successfully!"
        );

        return "redirect:/";
    }

}
