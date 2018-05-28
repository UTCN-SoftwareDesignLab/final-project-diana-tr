package ro.utcn.ds.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.utcn.ds.finalproject.dto.StudentDto;
import ro.utcn.ds.finalproject.dto.TeacherDto;
import ro.utcn.ds.finalproject.dto.UserDto;
import ro.utcn.ds.finalproject.model.User;
import ro.utcn.ds.finalproject.service.student.StudentService;
import ro.utcn.ds.finalproject.service.teacher.TeacherService;
import ro.utcn.ds.finalproject.service.user.UserService;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @Order(value = 1)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        UserDto userDto = new UserDto();
        modelAndView.addObject("userDto", userDto);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid UserDto userDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        StudentDto studentDto = new StudentDto();
        TeacherDto teacherDto = new TeacherDto();
        User userExists = userService.findByUsername(userDto.getUsername());
        String retrievedRole = userDto.getRole().toUpperCase();
        String role = new StringBuilder().append("ROLE_").append(retrievedRole).toString();
        userDto.setRole(role);
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.create(userDto);
            if (userDto.getRole().equals("ROLE_STUDENT")) {
                studentDto.setId(userDto.getId());
                studentDto.setUsername(userDto.getUsername());
                System.out.println("STUDENT");
                studentService.create(studentDto);
            }
            if (userDto.getRole().equals("ROLE_TEACHER")) {
                teacherDto.setId(userDto.getId());
                teacherDto.setUsername(userDto.getUsername());
                System.out.println("TEACHER");
                teacherService.create(teacherDto);
            }
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }
}
