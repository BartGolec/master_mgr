package com.mgr.bg.Service;

import com.mgr.bg.Model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class TextFileService {

    private static final Logger log = LoggerFactory.getLogger(TextFileService.class);

    public void setOpenDssResult (String filePath, List<String> openDssResults){

        if(openDssResults.contains(OpenDssResults.VOLTAGES)){
            replaceObjectInOpenDssModel(filePath, OpenDssResults.VOLTAGES, "Show Voltages LL Nodes");
        }
        else{
            replaceObjectInOpenDssModel(filePath, OpenDssResults.VOLTAGES, "// Show Voltages LL Nodes");
        }

        if(openDssResults.contains(OpenDssResults.POWERS)){
            replaceObjectInOpenDssModel(filePath, OpenDssResults.POWERS, "Show Powers kVA Elem");
        }
        else{
            replaceObjectInOpenDssModel(filePath, OpenDssResults.POWERS, "// Show Powers kVA Elem");
        }

        if(openDssResults.contains(OpenDssResults.CURRENTS)){
            replaceObjectInOpenDssModel(filePath, OpenDssResults.CURRENTS, "Show Currents Elem");
        }
        else{
            replaceObjectInOpenDssModel(filePath, OpenDssResults.CURRENTS, "// Show Currents Elem");
        }

        if(openDssResults.contains(OpenDssResults.LOSSES)){
            replaceObjectInOpenDssModel(filePath, OpenDssResults.LOSSES, "Show Losses");
        }
        else{
            replaceObjectInOpenDssModel(filePath, OpenDssResults.LOSSES, "// Show Losses");
        }

        if(openDssResults.contains(OpenDssResults.TAPS)){
            replaceObjectInOpenDssModel(filePath, OpenDssResults.TAPS, "Show Taps");
        }
        else{
            replaceObjectInOpenDssModel(filePath, OpenDssResults.TAPS, "// Show Taps");
        }
    }

    public  static List<VLL_Node_Model> gatherDataForVllPlot(String filePath){
        log.info("Gather data for vll plot from filepath : " + filePath);
        List<VLL_Node_Model> vll_node_modelList = new ArrayList<>();
        String voltageUnit = "";
        List<String> fileContent = null;
        try{
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        if(fileContent != null){
            if(fileContent.get(3).contains("VLN (kV)")){
                voltageUnit = "kV";
            }
            String busUpdate = "";
            for(int x = 5; x < fileContent.size(); x += 3){

                VLL_Node_Model vll_node_model = new VLL_Node_Model();
                vll_node_model.setVoltageUnit(voltageUnit);
                if(fileContent.get(x).substring(0, 9).contains("-")){
                    vll_node_model.setBus(busUpdate);
                }
                else {
                    busUpdate = fileContent.get(x).substring(0, 9).replace(".", "");
                    vll_node_model.setBus(busUpdate);
                }
                vll_node_model.setNode(fileContent.get(x).substring(10, 13));
                vll_node_model.setVlnValue(Double.parseDouble(fileContent.get(x).substring(14, 24)));
                vll_node_model.setAngleValue(Double.parseDouble(fileContent.get(x).substring(29, 34)));
                vll_node_model.setPuValue(Double.parseDouble(fileContent.get(x).substring(36, 44)));
                vll_node_model.setBaseVoltageValue(Double.parseDouble(fileContent.get(x).substring(46, 54)));
                log.info("Moddel : " + vll_node_model.toString());
                vll_node_modelList.add(vll_node_model);
            }
        }
        return vll_node_modelList;
    }

    public static void replaceObjectInOpenDssModel(String filePath, String toBeReplaced, String replacement){
        List<String> fileContent = null;
        try {
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileContent != null) {
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).contains(toBeReplaced)) {
                    fileContent.set(i, replacement);
                    break;
                }
            }
        }
        try {
            if (fileContent != null) {
                Files.write(Paths.get(filePath), fileContent, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> lista = new ArrayList<>();
        lista.add("Bartek");
        lista.add("Ernest");
        lista.add("Norbert");
        lista.add(1, "Agnieszka");
        for(String imie : lista){
            System.out.println(imie);
        }
    }

    public static void addNewLoad(String filePath, NewLoad newLoad){
        List<String> fileContent = null;
        try {
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileContent != null) {
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).contains("New Load")) {
                    fileContent.add(i, "New " + newLoad.getSignature() + " "+ newLoad.getBus() + " " + newLoad.getPhases() + " " + "Conn=Wye" + " "  + "Model=1" + " "  + newLoad.getVoltage() + "  " + newLoad.getActivePower() + " " +  newLoad.getReactivePower());
                    break;
                }
            }
        }
        try {
            if (fileContent != null) {
                Files.write(Paths.get(filePath), fileContent, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getOpenDssModelName(String openDssModelFilePath){
        log.info("Get OpenDss model name using OpenDss model file path : " + openDssModelFilePath);

        String openDssModelName = "";
        List<String> fileContent = null;
        try {
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(openDssModelFilePath), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileContent != null) {
            for (String line : fileContent) {
                if (line.contains("New object=circuit.")) {
                    openDssModelName = line.replace("New object=circuit.", "").trim();
                    break;
                }
            }
        }
        return openDssModelName;
    }

    public String setOpenDssLoadReplacement (Load load){
        return load.getOpenDssLoadConvension();
    }

    public List<String> setOpenDssResultsToBePlotted(SimulationResultsCheckboxes simulationResultsCheckboxes){
        List<String> openDssResults = new ArrayList<>();

        if(simulationResultsCheckboxes.isCurrents()){
            openDssResults.add(OpenDssResults.CURRENTS);
        }
        if(simulationResultsCheckboxes.isVoltages()){
            openDssResults.add(OpenDssResults.VOLTAGES);
        }
        if(simulationResultsCheckboxes.isPowers()){
            openDssResults.add(OpenDssResults.POWERS);
        }
        if(simulationResultsCheckboxes.isLosses()){
            openDssResults.add(OpenDssResults.LOSSES);
        }
        if(simulationResultsCheckboxes.isTaps()){
            openDssResults.add(OpenDssResults.TAPS);
        }

        return openDssResults;
    }

    public static String getVllNodeFilePath(String openDssModelFilePath){
        log.info("Get vll node file path based on openDss model file path : " +  openDssModelFilePath);
        String vllNodeFilePath = "";
        if(openDssModelFilePath != null){
            char [] filePathCharArray = openDssModelFilePath.toCharArray();
            for(int i = filePathCharArray.length - 1; i > 0; i --){
                if(filePathCharArray[i] != '\\'){
                    filePathCharArray[i] = ' ';
                }
                else if(filePathCharArray[i] == '\\'){
                    break;
                }
            }
            vllNodeFilePath = String.valueOf(filePathCharArray).trim();
        }
        return vllNodeFilePath;
    }

    public static List<NewLoad> getAllLoadsFromOpenDssModelFile(String openDssModelfilepath){

        log.info("Get all loads from OpenDss Model with filepath : " + openDssModelfilepath);

        List<NewLoad> newLoadList = new ArrayList<>();
            List<String> fileContent = null;
            try {
                fileContent = new ArrayList<>(Files.readAllLines(Paths.get(openDssModelfilepath), StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (fileContent != null) {
                for (int i = 0; i < fileContent.size(); i++) {
                    if (fileContent.get(i).contains("New Load") && fileContent.get(i).charAt(0) != '!') {
                        NewLoad load = new NewLoad();
                        fileContent.set(i, fileContent.get(i).replace("    ", " ").replace("   ", " ").replace("  ", " "));
                        String line = fileContent.get(i).replace("    ", " ").replace("   ", " ").replace("  ", " ");
                        String [] lineToStringArray = line.split(" ");
                        System.out.println(Arrays.toString(lineToStringArray));

                        load.setSignature(lineToStringArray [1]);
                        load.setBus(lineToStringArray [2]);
                        load.setPhases(lineToStringArray [3]);
                        load.setConn(lineToStringArray[4]);
                        load.setModel(lineToStringArray[5]);
                        load.setVoltage(lineToStringArray[6]);
                        load.setActivePower(lineToStringArray[7]);
                        load.setReactivePower(lineToStringArray[8]);

                        newLoadList.add(load);
                    }
                }
            }

            try {
                if (fileContent != null) {
                    Files.write(Paths.get(openDssModelfilepath), fileContent, StandardCharsets.UTF_8);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return newLoadList;
    }
}
