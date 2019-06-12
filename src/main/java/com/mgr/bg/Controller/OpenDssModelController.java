package com.mgr.bg.Controller;

import com.mgr.bg.Model.Load;
import com.mgr.bg.Model.SimulationResultsCheckboxes;
import com.mgr.bg.Model.VLL_Node_Model;
import com.mgr.bg.Service.FileUploadService;
import com.mgr.bg.Service.RunExternalProgramService;
import com.mgr.bg.Service.TextFileService;
import com.mgr.bg.Service.VlnVoltageBarChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class OpenDssModelController {

    private static final Logger log = LoggerFactory.getLogger(OpenDssModelController.class);

    @Autowired
    RunExternalProgramService runExternalProgramService;

    @Autowired
    TextFileService textFileService;

    @Autowired
    VlnVoltageBarChart vlnVoltageBarChart;

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping(value = "/updateOpenDssModel", method = RequestMethod.GET)
    public ModelAndView updateOpenDss(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("modifyOpenDssModel");
        modelAndView.addObject("openDssLoad", new Load());
        return modelAndView;
    }

    private String modelFilePath = "";

    @PostMapping(value = "/openDssModelUpdated")
    public String updatedOpenDss(@ModelAttribute("openDssLoad") Load load, ModelMap modelMap, BindingResult result){

        modelMap.addAttribute("filePath", load.getModelFilePath());
        modelMap.addAttribute("loadOldSignature", load.getOldSignature());
        modelMap.addAttribute("loadNewSignature", load.getSignature());
        modelMap.addAttribute("loadBus", load.getBus());
        modelMap.addAttribute("loadPhases", load.getPhases());
        modelMap.addAttribute("loadConnection", load.getConn());
        modelMap.addAttribute("loadModel", load.getModel());
        modelMap.addAttribute("loadVoltage", load.getVoltage());
        modelMap.addAttribute("loadActivePower", load.getActivePower());
        modelMap.addAttribute("loadRectivePower", load.getReactivePower());
        modelFilePath = load.getModelFilePath();

        if(result.hasErrors()){
            return "modifyOpenDssModelFail";
        }

        String loadReplacement = textFileService.setOpenDssLoadReplacement(load);
        TextFileService.replaceObjectInOpenDssModel(load.getModelFilePath(), load.getOldSignature(), loadReplacement);

        return "modifiedOpenDssModel";
    }

//    @RequestMapping(value = "/plotResults")
//    public ModelAndView chooseChartsToBePlottedFromTheModel(){
//        SimulationResultsCheckboxes simulationResultsCheckboxes = new SimulationResultsCheckboxes();
//        if(!modelFilePath.isEmpty()) {
//            simulationResultsCheckboxes.setOpenDssModelFilePath(modelFilePath);
//        }
//        return new ModelAndView("plotSimulationResults", "simulationResultsCheckboxes", simulationResultsCheckboxes);
//    }

    @PostMapping(value = "/plottedResults")
    public String plotResultForOpenDssModel(@ModelAttribute(name = "simulationResultsCheckboxes") SimulationResultsCheckboxes simulationResultsCheckboxes, ModelMap modelMap){

        modelMap.addAttribute("openDssModelFilePath", simulationResultsCheckboxes.getOpenDssModelFilePath());
        modelMap.addAttribute("openDssExeFilePath", simulationResultsCheckboxes.getOpenDssExeFilePath());

        log.info("OpenDSS model filepath : " + simulationResultsCheckboxes.getOpenDssModelFilePath() );
        log.info("OpenDSS exe filepath : " + simulationResultsCheckboxes.getOpenDssExeFilePath());

        List<String> openDssResultsList = textFileService.setOpenDssResultsToBePlotted(simulationResultsCheckboxes);
        textFileService.setOpenDssResult(simulationResultsCheckboxes.getOpenDssModelFilePath(), openDssResultsList);

        log.info("Model should be updated");

        runExternalProgramService.runOpenDssWithModel(simulationResultsCheckboxes.getOpenDssExeFilePath(), simulationResultsCheckboxes.getOpenDssModelFilePath());
        runExternalProgramService.killNotepadProcess();

        //TODO updates OpenDssModel so it will plot Voltage
        List <VLL_Node_Model> vll_node_modelList = TextFileService.gatherDataForVllPlot(TextFileService.getVllNodeFilePath(simulationResultsCheckboxes.getOpenDssModelFilePath()) + TextFileService.getOpenDssModelName(simulationResultsCheckboxes.getOpenDssModelFilePath()) + "_VLL_Node.Txt");
        String sourceBusValue = vll_node_modelList.get(0).getVlnValue().toString() + " " + vll_node_modelList.get(0).getVoltageUnit();
        vll_node_modelList.remove(0);
        String barChartPath = vlnVoltageBarChart.createVllNodeBarChart(vll_node_modelList);
        String base64barChart = fileUploadService.convertPngToBase64(barChartPath);

        String chartAttribute = "data:image/png;base64, " + base64barChart;
        log.info(chartAttribute);
        log.info(base64barChart);
        modelMap.addAttribute("chartName", chartAttribute);
        modelMap.addAttribute("sourceBusValue", sourceBusValue);

        return "plottedSimulationResults";
    }
}
