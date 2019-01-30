package com.mgr.bg.Controller;

        import com.mgr.bg.Model.CsvEntity;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Bartosz on 11/26/2018.
 */

@Controller
public class CsvEntityController {

    @RequestMapping(value = "/entity", method = RequestMethod.GET)
    public ModelAndView showForm(){
        return new ModelAndView("basicForm", "csvEntity", new CsvEntity());
    }

//    @RequestMapping(value = "/addCsvEntity", method = RequestMethod.POST)
//    public String submit(@Valid @ModelAttribute("employee")Employee employee,
//                         BindingResult result, ModelMap model) {
//        if (result.hasErrors()) {
//            return "error";
//        }
//        model.addAttribute("name", employee.getName());
//        model.addAttribute("contactNumber", employee.getContactNumber());
//        model.addAttribute("id", employee.getId());
//        return "employeeView";
//    }
}
