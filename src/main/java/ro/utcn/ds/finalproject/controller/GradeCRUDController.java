package ro.utcn.ds.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ro.utcn.ds.finalproject.converter.UserGradeDtoToUserGradeConverter;
import ro.utcn.ds.finalproject.converter.UserGradeToUserGradeDtoConverter;
import ro.utcn.ds.finalproject.dto.UserGradeDto;
import ro.utcn.ds.finalproject.model.UserGrade;
import ro.utcn.ds.finalproject.service.student.StudentService;
import ro.utcn.ds.finalproject.service.subject.SubjectService;
import ro.utcn.ds.finalproject.service.subjectdetail.SubjectDetailService;
import ro.utcn.ds.finalproject.service.usergrade.UserGradeService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/grades")
public class GradeCRUDController {
    @Autowired
    private UserGradeService userGradeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectDetailService subjectDetailService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("grades", userGradeService.getAll());
        return "grades";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid UserGradeDto userGradeDto) {
        userGradeService.create(userGradeDto);
        return "redirect:create?success";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("grade", new UserGradeDto());
        model.addAttribute("students", studentService.getAll());
        model.addAttribute("subjects", subjectService.getAll());
        model.addAttribute("details", subjectDetailService.getAll());
        return "grade-create-form";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(name = "id") String id, Model model) {
        userGradeService.delete(Long.parseLong(id));
        model.addAttribute("deleteMessage", "Grade was successfully deleted");
        return "redirect:/grades";
    }


    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String showUpdateForm(Model model) {
        model.addAttribute("consultation", new UserGrade());
        return "grade-update-form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editGrade(@RequestParam(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("grade-update-form");
        UserGrade userGrade = userGradeService.findById(Long.parseLong(id));
        UserGradeDto userGradeDto = new UserGradeToUserGradeDtoConverter().apply(userGrade);
        modelAndView.addObject("userGradeDto", userGradeDto);
        modelAndView.addObject("students", studentService.getAll());
        modelAndView.addObject("subjects", subjectService.getAll());
        modelAndView.addObject("details", subjectDetailService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute UserGradeDto userGradeDto) {
        ModelAndView modelAndView = new ModelAndView("redirect:/grades");
        UserGrade userGrade = new UserGradeDtoToUserGradeConverter().apply(userGradeDto);
        userGradeService.update(userGrade);
        modelAndView.addObject("message", "Success");
        return modelAndView;
    }
}
