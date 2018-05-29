package ro.utcn.ds.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ro.utcn.ds.finalproject.converter.StudentDtoToStudentConverter;
import ro.utcn.ds.finalproject.converter.StudentToStudentDtoConverter;
import ro.utcn.ds.finalproject.converter.UserDtoToUserConverter;
import ro.utcn.ds.finalproject.converter.UserToUserDtoConverter;
import ro.utcn.ds.finalproject.dto.StudentDto;
import ro.utcn.ds.finalproject.dto.UserDto;
import ro.utcn.ds.finalproject.model.Student;
import ro.utcn.ds.finalproject.service.student.StudentService;
import ro.utcn.ds.finalproject.service.user.UserService;
import ro.utcn.ds.finalproject.service.usergrade.UserGradeService;


@Controller
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserGradeService userGradeService;

    @Autowired
    private StudentDtoToStudentConverter studentDtoToStudentConverter;

    @Autowired
    private StudentToStudentDtoConverter studentToStudentDtoConverter;

    @Autowired
    private UserDtoToUserConverter userDtoToUserConverter;

    @Autowired
    private UserToUserDtoConverter userToUserDtoConverter;

    @RequestMapping(method = RequestMethod.GET)
    public String student() {
        return "student";
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.GET)
    public String showUpdateFormStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student-edit-info";
    }

    @RequestMapping(value = "/editStudent", method = RequestMethod.GET)
    public ModelAndView editStudent() {
        ModelAndView modelAndView = new ModelAndView("student-edit-info");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        System.out.println(username);
        Student student = studentService.findByUsername(username);
        StudentDto studentDto = studentToStudentDtoConverter.apply(student);
        modelAndView.addObject("studentDto", studentDto);
        return modelAndView;
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public ModelAndView updateStudent(@ModelAttribute StudentDto studentDto) {
        ModelAndView modelAndView = new ModelAndView("redirect:/student");
        Student student = studentDtoToStudentConverter.apply(studentDto);
        studentService.update(student);
        modelAndView.addObject("message", "Success");
        return modelAndView;
    }

    @RequestMapping(value = "/grades", method = RequestMethod.GET)
    public String getAllGrades(Model model) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user1.getUsername();
        Student student = studentService.findByUsername(username);
        model.addAttribute("grades", userGradeService.getAllByStudentId(student.getId()));
        return "student-grades";
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.GET)
    public String showUpdateFormUser(Model model) {
        model.addAttribute("user", new ro.utcn.ds.finalproject.model.User());
        return "student-edit-form";
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.GET)
    public ModelAndView editUser() {
        ModelAndView modelAndView = new ModelAndView("student-edit-form");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        System.out.println(username);
        ro.utcn.ds.finalproject.model.User user1 = userService.findByUsername(username);
        System.out.println(user1.toString());
        UserDto userDto = new UserToUserDtoConverter().apply(user1);
        System.out.println(userDto.toString());
        modelAndView.addObject("userDto", userDto);
        return modelAndView;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ModelAndView updateUser(@ModelAttribute UserDto userDto) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        ro.utcn.ds.finalproject.model.User user = new UserDtoToUserConverter().apply(userDto);
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user1.getUsername();
        Student student = studentService.findByUsername(username);
        studentService.update(student);
        userService.update(user);
        modelAndView.addObject("message", "Success");
        return modelAndView;
    }
}
