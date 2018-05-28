package ro.utcn.ds.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ro.utcn.ds.finalproject.converter.SubjectDtoToSubjectConverter;
import ro.utcn.ds.finalproject.converter.SubjectToSubjectDtoConverter;
import ro.utcn.ds.finalproject.dto.SubjectDto;
import ro.utcn.ds.finalproject.model.Subject;
import ro.utcn.ds.finalproject.service.subject.SubjectService;
import ro.utcn.ds.finalproject.service.teacher.TeacherService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/subjects")
public class SubjectCRUDController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("subjects", subjectService.getAll());
        return "subjects";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid SubjectDto subjectDto) {
        subjectService.create(subjectDto);
        return "redirect:create?success";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("subject", new SubjectDto());
        model.addAttribute("teachers", teacherService.getAll());
        return "subject-create-form";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(name = "id") String id, Model model) {
        subjectService.delete(Long.parseLong(id));
        model.addAttribute("deleteMessage", "Subject was successfully deleted");
        return "redirect:/subjects";
    }


    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String showUpdateForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "subject-update-form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editSubject(@RequestParam(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("subject-update-form");
        Subject subject = subjectService.findById(Long.parseLong(id));
        SubjectDto subjectDto = new SubjectToSubjectDtoConverter().apply(subject);
        modelAndView.addObject("subjectDto", subjectDto);
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute SubjectDto subjectDto) {
        ModelAndView modelAndView = new ModelAndView("redirect:/subjects");
        Subject subject = new SubjectDtoToSubjectConverter().apply(subjectDto);
        subjectService.update(subject);
        modelAndView.addObject("message", "Success");
        return modelAndView;
    }


}
