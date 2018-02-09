package com.saniya.library;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class HomeController {

    @Autowired
    Stacks stacks;

    @Autowired
    HistoryRepository historyRepository;


    public String Message = "";



    @RequestMapping("/")
    public String mainPage(){
        return "Main";
    }

    @RequestMapping("/all")
    public String listAll(Model model){
        model.addAttribute("Stacks", stacks.findAll());
        model.addAttribute("Message", Message);
        return "List";
    }


    @GetMapping("/bookform")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        Message = "Successfully Added New Book!";
        model.addAttribute("Message", Message);
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

    @RequestMapping("/history")
    public String allHistory(Model model){
        model.addAttribute("history", historyRepository.findAll());
        return "History";
    }

    @RequestMapping("/return/{id}")
    public String returnBook(@PathVariable("id") long id, Model model){
        Book book = stacks.findOne(id);
        book.setAvailable("Available");
        Message = "Successfully Returned Book!";
        stacks.save(book);
        model.addAttribute("Stacks", stacks.findAll());
        return "redirect:/all";
    }

    @RequestMapping("/borrow/{id}")
    public String borrowBook(@PathVariable("id") long id, Model model){
        Book book = stacks.findOne(id);

        book.setAvailable("Not");
        book.setNumTimes(book.getNumTimes() +1);
        String temp = (LocalDateTime.now()).toString();
        String temp2 = temp.substring(5, 7) + "/" + temp.substring(8, 10) + "/" + temp.substring(0, 4) + " " + temp.substring(11, 19);
        book.setLastBorrow(temp2);

        Message = "Successfully Borrowed Book!";
        stacks.save(book);
        model.addAttribute("Stacks", stacks.findAll());

        historyRepository.save(new HistoryRecord(book.getTitle() + " " + temp2));
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
