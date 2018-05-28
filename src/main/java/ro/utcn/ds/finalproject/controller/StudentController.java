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

    @RequestMapping(method = RequestMethod.GET)
    public String student() {
        return "student";
    }


    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ModelAndView editStudent() {
        ModelAndView modelAndView = new ModelAndView("student-edit-info");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        System.out.println(username);
        Student student = studentService.findByUsername(username);
        ro.utcn.ds.finalproject.model.User user1 = userService.findByUsername(username);
        UserDto userDto = new UserToUserDtoConverter().apply(user1);
        StudentDto studentDto = new StudentToStudentDtoConverter().apply(student);
        modelAndView.addObject("studentDto", studentDto);
        modelAndView.addObject("userDto", userDto);
        return modelAndView;
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute StudentDto studentDto) {
        ModelAndView modelAndView = new ModelAndView("redirect:/student");
        Student student = new StudentDtoToStudentConverter().apply(studentDto);
        studentService.update(student);
        modelAndView.addObject("message", "Success");
        return modelAndView;
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute UserDto userDto) {
        ModelAndView modelAndView = new ModelAndView("redirect:/student");
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user1.getUsername();
        String usernameDto = userDto.getUsername();
        System.out.println("UsernameDto" + usernameDto);
        ro.utcn.ds.finalproject.model.User user = new UserDtoToUserConverter().apply(userDto);
        System.out.println("User id " + user.getId());
        Student student = studentService.findByUsername(username);
        student.setUsername(usernameDto);
        studentService.update(student);
        userService.update(user);
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
}
