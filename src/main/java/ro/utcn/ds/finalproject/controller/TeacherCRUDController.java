package ro.utcn.ds.finalproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ro.utcn.ds.finalproject.converter.TeacherDtoToTeacherConverter;
import ro.utcn.ds.finalproject.converter.TeacherToTeacherDtoConverter;
import ro.utcn.ds.finalproject.dto.TeacherDto;
import ro.utcn.ds.finalproject.model.Teacher;
import ro.utcn.ds.finalproject.service.teacher.TeacherService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/teachers")
public class TeacherCRUDController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("teachers", teacherService.getAll());
        return "teachers";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid TeacherDto teacherDto) {
        teacherService.create(teacherDto);
        return "redirect:create?success";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        model.addAttribute("teacher", new TeacherDto());
        return "teacher-create-form";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(name = "id") String id, Model model) {
        teacherService.delete(Long.parseLong(id));
        model.addAttribute("deleteMessage", "Teacher was successfully deleted");
        return "redirect:/teachers";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String showUpdateForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher-update-form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editTeacher(@RequestParam(value = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("teacher-update-form");
        Teacher teacher = teacherService.findById(Long.parseLong(id));
        TeacherDto teacherDto = new TeacherToTeacherDtoConverter().apply(teacher);
        modelAndView.addObject("teacherDto", teacherDto);
        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute TeacherDto teacherDto) {
        ModelAndView modelAndView = new ModelAndView("redirect:/teachers");
        Teacher teacher = new TeacherDtoToTeacherConverter().apply(teacherDto);
        teacherService.update(teacher);
        modelAndView.addObject("message", "Success");
        return modelAndView;
    }


}
