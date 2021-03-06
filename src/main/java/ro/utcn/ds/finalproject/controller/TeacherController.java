package ro.utcn.ds.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.utcn.ds.finalproject.dto.UserGradeDto;
import ro.utcn.ds.finalproject.model.Subject;
import ro.utcn.ds.finalproject.model.Teacher;
import ro.utcn.ds.finalproject.service.subject.SubjectService;
import ro.utcn.ds.finalproject.service.subjectdetail.SubjectDetailService;
import ro.utcn.ds.finalproject.service.teacher.TeacherService;
import ro.utcn.ds.finalproject.service.usergrade.UserGradeService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private UserGradeService userGradeService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectDetailService subjectDetailService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user1.getUsername();
        Teacher teacher = teacherService.findByUsername(username);
        Subject subject = subjectService.findByTeacherId(teacher.getId());
        model.addAttribute("grades", userGradeService.getAllBySubjectId(subject.getId()));
        return "teacher";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateGrade(Model model) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user1.getUsername();
        Teacher teacher = teacherService.findByUsername(username);
        Subject subject = subjectService.findByTeacherId(teacher.getId());
        model.addAttribute("gradeDto", new UserGradeDto());
        model.addAttribute("subject", subject);
        model.addAttribute("students", subject.getStudents());
        model.addAttribute("details", subjectDetailService.getAll());
        return "teacher-grade";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid UserGradeDto userGradeDto) {
        System.out.println(userGradeDto.toString());
        userGradeService.create(userGradeDto);
        return "redirect:create?success";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(name = "id") String id, Model model) {
        String message = null;
        boolean error = true;
        if (id.isEmpty()) {
            message = "Please enter the id";
        } else if (Long.parseLong(id) < 0) {
            message = "Please enter a positive value";
        } else if (!userGradeService.existsById(Long.parseLong(id))) {
            message = "The grade with the specified id doesn't exist";
        } else {
            error = false;
        }

        if (error) {
            model.addAttribute("message", message);
        } else {
            userGradeService.delete(Long.parseLong(id));
            model.addAttribute("message", "Grade was successfully deleted");
        }
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user1.getUsername();
        Teacher teacher = teacherService.findByUsername(username);
        Subject subject = subjectService.findByTeacherId(teacher.getId());
        model.addAttribute("grades", userGradeService.getAllBySubjectId(subject.getId()));
        return "teacher";
    }

}


