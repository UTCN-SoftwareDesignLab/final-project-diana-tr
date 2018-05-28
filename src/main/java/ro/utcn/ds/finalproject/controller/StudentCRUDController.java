package ro.utcn.ds.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ro.utcn.ds.finalproject.converter.StudentDtoToStudentConverter;
import ro.utcn.ds.finalproject.converter.StudentToStudentDtoConverter;
import ro.utcn.ds.finalproject.dto.StudentDto;
import ro.utcn.ds.finalproject.model.Student;
import ro.utcn.ds.finalproject.model.Subject;
import ro.utcn.ds.finalproject.service.student.StudentService;
import ro.utcn.ds.finalproject.service.subject.SubjectService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/students")
public class StudentCRUDController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("students", studentService.getAll());
        return "students";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid StudentDto studentDto) {
        studentService.create(studentDto);
        return "redirect:create?success";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("student", new StudentDto());
        return "student-create-form";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(name = "id") String id, Model model) {
        studentService.delete(Long.parseLong(id));
        model.addAttribute("deleteMessage", "Student was successfully deleted");
        return "redirect:/students";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String showUpdateForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-update-form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editStudent(@RequestParam(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("student-update-form");
        Student student = studentService.findById(Long.parseLong(id));
        StudentDto studentDto = new StudentToStudentDtoConverter().apply(student);
        modelAndView.addObject("studentDto", studentDto);
        modelAndView.addObject("subjects", studentDto.getSubjects());
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam(value = "course") String course, @ModelAttribute StudentDto studentDto) {
        ModelAndView modelAndView = new ModelAndView("redirect:/students");
        Student student = new StudentDtoToStudentConverter().apply(studentDto);
        System.out.println(course);
        if (!course.isEmpty()) {
            Subject subject = subjectService.findByName(course);
            studentService.addSubjectToStudent(subject.getId(), student);
        }
        studentService.update(student);
        modelAndView.addObject("message", "Success");
        return modelAndView;
    }

}
