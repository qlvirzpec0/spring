package com.epam.newsportal.controller;

import com.epam.newsportal.dto.Author;
import com.epam.newsportal.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    @Qualifier("authorService")
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(path = "/auth", method = RequestMethod.GET)
    public String authGet(Model model) {
        model.addAttribute("author", new Author());
        return "auth";
    }

    @RequestMapping(path = "/reg", method = RequestMethod.POST)
    public String regPost(@ModelAttribute("author") @Valid Author author,
                                 BindingResult result) {
        if(result.hasErrors()){
            return "auth";
        }
        if(!authorService.registration(author)){
            result.rejectValue("username","","user is exist");
        }
        return "auth";
    }

}