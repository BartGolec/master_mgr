package com.mgr.bg.Controller;


import com.mgr.bg.Model.NewLoad;
import com.mgr.bg.Model.SimulationResultsCheckboxes;
import com.mgr.bg.Service.NewLoadDto;
import com.mgr.bg.Service.TextFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ModernOpenDssModelController {

    private static final Logger log = LoggerFactory.getLogger(ModernOpenDssModelController.class);
    private String openDssModelfilePath = "";
    private List<String> listOfOldLoadSignatures = new ArrayList<>();

    @RequestMapping(value = "/chooseOpenDssModelToBeUpdated")
    public ModelAndView chooseOpenDssModelToBeUpdated(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("modelToBeUpdated");
        modelAndView.addObject("openDssModelFilePath", new NewLoad());
        return modelAndView;
    }

    @PostMapping(value = "/getAllLoadsFromOpenDssModel")
    public String getAllLoadsFromOpenDssModel(@ModelAttribute("load") NewLoad load, BindingResult bindingResult, ModelMap modelMap){
        if(bindingResult.hasErrors()){
            return "wrongDataInputError";
        }
        openDssModelfilePath = load.getModelFilePath();
        modelMap.addAttribute("openDssModelFilePath", openDssModelfilePath);

        List<NewLoad> loadList = TextFileService.getAllLoadsFromOpenDssModelFile(openDssModelfilePath);
        listOfOldLoadSignatures = loadList.stream().map(NewLoad::getSignature).collect(Collectors.toList());
        modelMap.addAttribute("listOfLoads", loadList);

        return "allLoadsFromOpenDssModel";
    }

    @GetMapping(value = "/getAllLoadsFromOpenDssModelAfterSave")
    public String getAllLoadsFromOpenDssModel(ModelMap modelMap){

        modelMap.addAttribute("openDssModelFilePath", openDssModelfilePath);

        List<NewLoad> loadList = TextFileService.getAllLoadsFromOpenDssModelFile(openDssModelfilePath);
        modelMap.addAttribute("openDssModelFilePath", openDssModelfilePath);
        modelMap.addAttribute("listOfLoads", loadList);

        return "allLoadsFromOpenDssModel";
    }

    @GetMapping(value = "/editLoads")
    public String showEditForm(Model model) {

        List<NewLoad> loadList = TextFileService.getAllLoadsFromOpenDssModelFile(openDssModelfilePath);

        model.addAttribute("loadList", loadList);

        return "editLoadsForm";
    }

    @PostMapping(value = "/saveLoads")
    public String saveBooks(@ModelAttribute NewLoadDto form, Model model) {
        //TODO SAVE IN DB ?

        List<String> listOfNewSignatures = form.getLoadList().stream().map(NewLoad::getOpenDssNewLoadConvension).collect(Collectors.toList());

        if(listOfOldLoadSignatures.size() == listOfNewSignatures.size()){
            for(int i = 0; i<listOfOldLoadSignatures.size(); i++){
                TextFileService.replaceObjectInOpenDssModel(openDssModelfilePath, listOfOldLoadSignatures.get(i), listOfNewSignatures.get(i));
            }
        }
        else{
            log.error("List of old Load Signature doesn't match with the new one");
        }

        List<NewLoad> newLoadList = form.getLoadList();

        model.addAttribute("listOfLoads", newLoadList);

        return "redirect:/getAllLoadsFromOpenDssModelAfterSave";
    }

    //TODO Feature not working
    @GetMapping(value = "/createLoads")
    public String showCreateForm(Model model) {

        NewLoad newLoad = new NewLoad();

        model.addAttribute("newLoad", newLoad);

        return "createLoadForm";
    }

    @PostMapping(value = "/createdLoad")
    public String showCreatedLoad(@ModelAttribute NewLoad newLoad, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "wrongDataInputError";
        }
        model.addAttribute("signature", newLoad.getSignature());
        model.addAttribute("bus", newLoad.getBus());
        model.addAttribute("phases", newLoad.getPhases());
        model.addAttribute("voltage", newLoad.getVoltage());
        model.addAttribute("activePower", newLoad.getActivePower());
        model.addAttribute("reactivePower", newLoad.getReactivePower());
        newLoad.setModelFilePath(openDssModelfilePath);

        TextFileService.addNewLoad(openDssModelfilePath, newLoad);

        return "loadCreated";
    }

    @RequestMapping(value = "/plotResults")
    public ModelAndView chooseChartsToBePlottedFromTheModel(){
        SimulationResultsCheckboxes simulationResultsCheckboxes = new SimulationResultsCheckboxes();
        if(!openDssModelfilePath.isEmpty()) {
            simulationResultsCheckboxes.setOpenDssModelFilePath(openDssModelfilePath);
        }
        return new ModelAndView("plotSimulationResults", "simulationResultsCheckboxes", simulationResultsCheckboxes);
    }
}
