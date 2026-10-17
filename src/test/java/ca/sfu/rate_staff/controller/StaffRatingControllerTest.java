package ca.sfu.rate_staff.controller;

import ca.sfu.rate_staff.repository.StaffRatingRepository;
import ca.sfu.rate_staff.model.StaffRating;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(StaffRatingController.class)
class StaffRatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StaffRatingRepository repository;

    @Test
    void homePageShouldReturnRatings() throws Exception {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("ratings"));
    }


    @Test
    void validFormShouldSaveRatingAndRedirect() throws Exception {
        mockMvc.perform(post("/ratings")
                        .param("name", "Alex Smith")
                        .param("email", "alex@example.com")
                        .param("roleType", "TA")
                        .param("clarityScore", "8")
                        .param("nicenessScore", "9")
                        .param("knowledgeableScore", "7")
                        .param("comment", "Helpful TA"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(repository).save(any(StaffRating.class));
    }


    @Test
    void invalidFormShouldReturnFormWithErrors() throws Exception {
        mockMvc.perform(post("/ratings")
                        .param("name", "")
                        .param("email", "alex@example.com")
                        .param("roleType", "TA")
                        .param("clarityScore", "8")
                        .param("nicenessScore", "9")
                        .param("knowledgeableScore", "7")
                        .param("comment", "Helpful TA"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating-form"))
                .andExpect(model().attributeHasFieldErrors("rating", "name"));

        verify(repository, org.mockito.Mockito.never())
                .save(any(StaffRating.class));
    }
    


}
