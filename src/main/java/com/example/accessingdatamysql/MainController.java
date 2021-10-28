package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
//import org.springframework.ui.Model;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller // This means that this class is a Controller
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private UserRepository userRepository;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewUser (@RequestParam String name
      , @RequestParam String email) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return userRepository.findAll();
  }

  @RequestMapping("/")
  public String viewHomePage(Model model) {
      model.addAttribute("listuser", userRepository.findAll());
      
      return "index";
  }

  @RequestMapping("/new")
  public String showNewProductPage(Model model) {
      User u = new User();
      model.addAttribute("user", u);
      
      return "new_user";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String saveProduct(@ModelAttribute("user") User u) {
    userRepository.save(u);
    
    return "redirect:/";
  }

  @RequestMapping("/edit/{id}")
  public ModelAndView showEditProductPage(@PathVariable(name = "id") int id, Model model) {
      ModelAndView mav = new ModelAndView("edit_user");
      User u = userRepository.findById(id).get();
      mav.addObject("user", u);
      
      return mav;
  }
  @GetMapping("/delete/{id}")
  public String deleteUser(@PathVariable("id") int id, Model model) {
      User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
      userRepository.delete(user);
      return "redirect:/";
  }
}