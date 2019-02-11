package com.mgr.bg.Controller;

        import com.mgr.bg.Model.CsvEntity;
        import com.mgr.bg.Repository.CsvRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.ModelMap;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.servlet.ModelAndView;

        import javax.validation.Valid;

/**
 * Created by Bartosz on 11/26/2018.
 */

@Controller
public class CsvEntityController {

    @Autowired
    private CsvRepository csvRepository;

    @RequestMapping(value = "/entity", method = RequestMethod.GET)
    public ModelAndView showForm(){
        return new ModelAndView("basicForm", "csvEntity", new CsvEntity());
    }

    @RequestMapping(value = "/addCsvEntity", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("csvEntity")CsvEntity csvEntity,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("date", csvEntity.getDate());
        model.addAttribute("Pmax", csvEntity.getPmax());
        model.addAttribute("CP", csvEntity.getCP());
        model.addAttribute("CO", csvEntity.getCP());
        model.addAttribute("BPP", csvEntity.getBPP());
        model.addAttribute("BPO", csvEntity.getBPO());
        model.addAttribute("BOO", csvEntity.getBOO());
        model.addAttribute("BOP", csvEntity.getBOP());

        csvRepository.save(csvEntity);

        return "entityAdded";
    }
}
