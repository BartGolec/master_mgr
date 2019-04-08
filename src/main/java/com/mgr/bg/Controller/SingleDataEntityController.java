package com.mgr.bg.Controller;

import com.mgr.bg.Model.SingleDataEntity;
import com.mgr.bg.Repository.SingleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Created by Bartosz on 11/26/2018.
 */

@Controller
@Transactional
public class SingleDataEntityController {

    @Autowired
    private SingleDataRepository singleDataRepository;

    @RequestMapping(value = "/entity", method = RequestMethod.GET)
    public ModelAndView showForm(){
        return new ModelAndView("addSingleDataEntity", "singleDataEntity", new SingleDataEntity());
    }

    @PostMapping(value = "/addSingleDataEntity")
    public String submit(@Valid @ModelAttribute("singleDataEntity") SingleDataEntity singleDataEntity,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "wrongDataInputError";
        }
        model.addAttribute("date", singleDataEntity.getDate());
        model.addAttribute("Pmax", singleDataEntity.getPmax());
        model.addAttribute("CP", singleDataEntity.getCP());
        model.addAttribute("CO", singleDataEntity.getCP());
        model.addAttribute("BPP", singleDataEntity.getBPP());
        model.addAttribute("BPO", singleDataEntity.getBPO());
        model.addAttribute("BOO", singleDataEntity.getBOO());
        model.addAttribute("BOP", singleDataEntity.getBOP());

        singleDataRepository.save(singleDataEntity);

        return "singleDataEntityAdded";
    }
}
