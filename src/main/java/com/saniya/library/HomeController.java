package com.saniya.library;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    Stacks stacks;

    @Autowired
    Stacks rstacks;

    @Autowired
    Stacks bstacks;


    @RequestMapping("/")
    public String mainPage(){
        return "Main";
    }

    @RequestMapping("/all")
    public String listAll(Model model){
        model.addAttribute("Stacks", stacks.findAll());
        return "List";
    }


    @GetMapping("/bookform")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "BookForm";
    }

    @PostMapping("/bookform")
    public String processBook(Model model, @Valid @ModelAttribute("book") Book book, BindingResult result){
        if(result.hasErrors()){
            return "BookForm";
        }
        book.setAvailable("Available");
        model.addAttribute("book", book);
        stacks.save(book);
        return "redirect:/all";
    }


    @RequestMapping("/return/{id}")
    public String returnBook(@PathVariable("id") long id, Model model){
        Book book = stacks.findOne(id);
        book.setAvailable("Available");
        stacks.save(book);
        model.addAttribute("Stacks", stacks.findAll());
        return "redirect:/all";
    }

    @RequestMapping("/borrow/{id}")
    public String borrowBook(@PathVariable("id") long id, Model model){
        Book book = stacks.findOne(id);
        book.setAvailable("Not");
        book.setNumTimes(book.getNumTimes() +1);

        stacks.save(book);
        model.addAttribute("Stacks", stacks.findAll());
        return "redirect:/all";
    }



    @RequestMapping("/borrowed")
    public String allBorrowed(Model model){
        model.addAttribute("Stacks", stacks.findAll());
        return "Borrowed";
    }

    @RequestMapping("/returned")
    public String allReturned(Model model){
        model.addAttribute("Stacks", stacks.findAll());
        return "Returned";
    }

}
