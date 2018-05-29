package ro.utcn.ds.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import java.util.Collections;

@Controller
@RequestMapping(value = "/subjects")
public class SubjectCRUDController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectDtoToSubjectConverter converter;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("subjects", subjectService.getAll());
        return "subjects";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid SubjectDto subjectDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "subject-create-form";
        }
        if (subjectService.existsByTeacherId(subjectDto.getTeacher_id())) {
            String message = "Specified teacher is already assigned to a subject";
            model.addAttribute("message", message);
            model.addAttribute("teachers", teacherService.getAll());
            return "subject-create-form";
        }

        subjectService.create(subjectDto);
        return "redirect:create?success";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("subjectDto", new SubjectDto());
        model.addAttribute("teachers", teacherService.getAll());
        return "subject-create-form";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(name = "id") String id, Model model) {
        String message = null;
        boolean error = true;
        if (id.isEmpty()) {
            message = "Please enter the id!";
        } else if (Long.parseLong(id) < 0) {
            message = "Please enter a positive value!";
        } else if (!subjectService.exists(Long.parseLong(id))) {
            message = "The subject with the specified id doesn't exist!";
        } else if (subjectService.existsByStudentId(Long.parseLong(id))) {
            message = "The subject can't be deleted";
        } else {
            error = false;
        }

        if (error) {
            model.addAttribute("subjects", subjectService.getAll());
            model.addAttribute("message", message);
            return "subjects";
        } else {
            subjectService.delete(Long.parseLong(id));
            model.addAttribute("message", "Subject was successfully deleted");
            return "redirect:/subjects";
        }

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
        System.out.println(subjectDto.toString());
        modelAndView.addObject("subjectDto", subjectDto);
        modelAndView.addObject("teachers", teacherService.getAll());
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute SubjectDto subjectDto, Model model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/subjects");
        System.out.println("After click:" + subjectDto.toString());
        if (subjectDto.getStudents() == null) {
            System.out.println("Null list");
            subjectDto.setStudents(Collections.emptyList());
            System.out.println(subjectDto.toString());
        }
        if (subjectService.existsByTeacherId(subjectDto.getTeacher_id())) {
            String message = "Specified teacher is already assigned to a subject";
            model.addAttribute("message", message);
            model.addAttribute("teachers", teacherService.getAll());
            ModelAndView modelAndView1 = new ModelAndView("subject-update-form");
            return modelAndView1;
        }
        Subject subject = converter.apply(subjectDto);
        System.out.println(subject.toString());
        subjectService.update(subject);
        modelAndView.addObject("message", "Success");
        return modelAndView;
    }


}
