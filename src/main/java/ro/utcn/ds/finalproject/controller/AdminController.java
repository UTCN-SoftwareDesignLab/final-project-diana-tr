package ro.utcn.ds.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping()
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String users() {
        return "redirect:/students";
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String teachers() {
        return "redirect:/teachers";
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public String subjects() {
        return "redirect:/subjects";
    }

    @RequestMapping(value = "/grades", method = RequestMethod.GET)
    public String grades() {
        return "redirect:/grades";
    }

}
