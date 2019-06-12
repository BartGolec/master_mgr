package com.mgr.bg.Controller;

import com.mgr.bg.Model.BatchDataEntity;
import com.mgr.bg.Repository.BatchDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Bartosz on 11/26/2018.
 */

@Controller
@Transactional
public class SingleDataEntityController {

    @Autowired
    private BatchDataRepository batchDataRepository;

    @RequestMapping(value = "/entity", method = RequestMethod.GET)
    public ModelAndView showForm(){
        return new ModelAndView("addSingleDataEntity", "batchDataEntity", new BatchDataEntity());
    }

    @PostMapping(value = "/addBatchDataEntity")
    public String submit(@ModelAttribute("batchDataEntity") BatchDataEntity batchDataEntity,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "wrongDataInputError";
        }
        model.addAttribute("fileName", batchDataEntity.getFileName());
        model.addAttribute("date", batchDataEntity.getDate());
        model.addAttribute("Pmax", batchDataEntity.getPmax());
        model.addAttribute("CP", batchDataEntity.getCP());
        model.addAttribute("CO", batchDataEntity.getCP());
        model.addAttribute("BPP", batchDataEntity.getBPP());
        model.addAttribute("BPO", batchDataEntity.getBPO());
        model.addAttribute("BOO", batchDataEntity.getBOO());
        model.addAttribute("BOP", batchDataEntity.getBOP());

        batchDataRepository.save(batchDataEntity);

        return "singleDataEntityAdded";
    }

    @GetMapping(value = "/seeDataFromCertainNode")
    public ModelAndView showFileNameForm() {
        return new ModelAndView("seeDataFromCertainNodeView", "batchDataEntity", new BatchDataEntity());
    }

    @PostMapping(value = "/retrieveDataFromCertainNode")
    public String submitDataFromCertainNode(@ModelAttribute("batchDataEntity") BatchDataEntity batchDataEntity,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "wrongDataInputError";
        }

        model.addAttribute("fileName", batchDataEntity.getFileName());

        List<BatchDataEntity> batchDataEntityList = batchDataRepository.findByFileName(batchDataEntity.getFileName());
        model.addAttribute("batchDataList", batchDataEntityList);

        return "retrieveDataFromCertainNodeView";
    }

}
