package com.mgr.bg.Controller;

import com.mgr.bg.Model.BatchDataEntity;
import com.mgr.bg.Model.FileData;
import com.mgr.bg.Model.FileDataCheckboxes;
import com.mgr.bg.Model.SingleDataObject;
import com.mgr.bg.Repository.BatchDataRepository;
import com.mgr.bg.Service.FileUploadService;
import com.mgr.bg.Service.MultiplyLineChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.List;

@Controller
@Transactional
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    private int incrementation = 1;
    private static final String csvFiledownloadDirectory = "C:\\Praca Magisterska\\Application Files";
    //private static final String csvFiledownloadDirectory = "C:\\Users\\Bartek\\Desktop\\mgr\\src\\main\\resources\\templates";

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private BatchDataRepository batchDataRepository;

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public ModelAndView homePage() {
        return new ModelAndView("fileUploadView", "fileDataCheckboxes", new FileDataCheckboxes());
    }

    @PostMapping(value = "/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file,  ModelMap modelMap, @ModelAttribute("fileDataCheckboxes") FileDataCheckboxes fileDataCheckboxes) {
        String fileDir = csvFiledownloadDirectory + "\\" + file.getOriginalFilename();
        String outputView = fileUploadService.uploadFile(file,
                ".csv",
                "fileUploadSuccess",
                "fileUploadFailView",
                "file",
                fileDir,
                modelMap);

        List<SingleDataObject> singleDataObjectList = fileUploadService.readCsvFile(fileDir);

        for(SingleDataObject singleDataObject : singleDataObjectList){
            System.out.println(singleDataObject.toString());
        }

        fileUploadService.saveBatchDataFromFile(singleDataObjectList, file);

        List<FileData> fileDataList = fileUploadService.fileDataForPlotsGenerations(fileDataCheckboxes);

        List<BatchDataEntity> batchDataEntities = batchDataRepository.findByFileName(file.getOriginalFilename());

        List<BatchDataEntity> batchDataEntityListForAveragePower = fileUploadService.convertEnergyListToPowerList(batchDataEntities);
        for(BatchDataEntity batchDataEntity : batchDataEntityListForAveragePower){
            System.out.println(batchDataEntity.toString());
        }

        modelMap.addAttribute("fileName", file.getOriginalFilename().substring(0, file.getOriginalFilename().length()-4));

        double maximumActivePowerConsumedValue = batchDataEntityListForAveragePower.stream().mapToDouble(BatchDataEntity::getCP).max().getAsDouble();
        double minimalActivePowerConsumedValue = batchDataEntityListForAveragePower.stream().mapToDouble(BatchDataEntity::getCP).min().getAsDouble();
        modelMap.addAttribute("maximumActivePowerConsumedValue", maximumActivePowerConsumedValue);
        modelMap.addAttribute("minimalActivePowerConsumedValue", minimalActivePowerConsumedValue);

        double maximalActivePowerGivenValue = batchDataEntityListForAveragePower.stream().mapToDouble(BatchDataEntity::getCO).min().getAsDouble();
        double minimalActivePowerGivenValue = batchDataEntityListForAveragePower.stream().mapToDouble(BatchDataEntity::getCO).min().getAsDouble();
        modelMap.addAttribute("maximalActivePowerGivenValue", maximalActivePowerGivenValue);
        modelMap.addAttribute("minimalActivePowerGivenValue", minimalActivePowerGivenValue);

        double maximumReactiveInductivePowerConsumedValue = batchDataEntityListForAveragePower.stream().mapToDouble(BatchDataEntity::getBPP).max().getAsDouble();
        double minimalReactiveInductivePowerConsumedValue = batchDataEntityListForAveragePower.stream().mapToDouble(BatchDataEntity::getBPP).min().getAsDouble();
        modelMap.addAttribute("maximumReactiveInductivePowerConsumedValue", maximumReactiveInductivePowerConsumedValue);
        modelMap.addAttribute("minimalReactiveInductivePowerConsumedValue", minimalReactiveInductivePowerConsumedValue);

        double maximumReactiveInductivePowerGivenValue = batchDataEntityListForAveragePower.stream().mapToDouble(BatchDataEntity::getBPO).max().getAsDouble();
        double minimalReactiveInductivePowerGivenValue = batchDataEntityListForAveragePower.stream().mapToDouble(BatchDataEntity::getBPO).min().getAsDouble();
        modelMap.addAttribute("maximumReactiveInductivePowerGivenValue", maximumReactiveInductivePowerGivenValue);
        modelMap.addAttribute("minimalReactiveInductivePowerGivenValue", minimalReactiveInductivePowerGivenValue);

        double maximumReactiveConductivePowerGivenValue = batchDataEntityListForAveragePower.stream().mapToDouble(BatchDataEntity::getBOO).max().getAsDouble();
        double minimalReactiveConductivePowerGivenValue = batchDataEntityListForAveragePower.stream().mapToDouble(BatchDataEntity::getBOO).min().getAsDouble();
        modelMap.addAttribute("maximumReactiveConductivePowerGivenValue", maximumReactiveConductivePowerGivenValue);
        modelMap.addAttribute("minimalReactiveConductivePowerGivenValue", minimalReactiveConductivePowerGivenValue);

        double maximumReactiveConductivePowerConsumedValue = batchDataEntityListForAveragePower.stream().mapToDouble(BatchDataEntity::getBOP).max().getAsDouble();
        double minimalReactiveConductivePowerConsumedValue = batchDataEntityListForAveragePower.stream().mapToDouble(BatchDataEntity::getBOP).min().getAsDouble();
        modelMap.addAttribute("maximumReactiveConductivePowerConsumedValue", maximumReactiveConductivePowerConsumedValue);
        modelMap.addAttribute("minimalReactiveConductivePowerConsumedValue", minimalReactiveConductivePowerConsumedValue);

        modelMap.addAttribute("listOfPowerEntities", batchDataEntityListForAveragePower);

        for(FileData fileData : fileDataList) {

            try {
                MultiplyLineChart multiplyLineChart = new MultiplyLineChart(fileData, batchDataEntities);

                String base64image = fileUploadService.convertPngToBase64("C:\\Users\\Bartek\\Desktop\\mgr\\src\\main\\resources\\static\\images" + "\\" + multiplyLineChart.getFileName() + ".png");
                String chartAttribute = "data:image/png;base64, " + base64image;
                log.info(chartAttribute);
                modelMap.addAttribute("chartName" + incrementation, chartAttribute);

                Double minimalAttribute = multiplyLineChart.getMinimalValue();
                Double minimalAttributeRounded = BigDecimal.valueOf(minimalAttribute).setScale(2, RoundingMode.HALF_UP).doubleValue();
                modelMap.addAttribute("minimalValue" + incrementation, minimalAttributeRounded);

                Double maximumAttribute = multiplyLineChart.getMaximumValue();
                Double maximumAttributeRounded = BigDecimal.valueOf(maximumAttribute).setScale(2, RoundingMode.HALF_UP).doubleValue();
                modelMap.addAttribute("maximumValue" + incrementation, maximumAttributeRounded);

                Double averageAttribute = multiplyLineChart.getAverageValue();
                Double averageAttributeRounded = BigDecimal.valueOf(averageAttribute).setScale(2, RoundingMode.HALF_UP).doubleValue();
                modelMap.addAttribute("averageValue" + incrementation, averageAttributeRounded);

                String dateStartAttribute = multiplyLineChart.getDatePeriodStart();
                String dateEndAttribute = multiplyLineChart.getDatePeriodEnd();
                modelMap.addAttribute("datePeriodValue" + incrementation, dateStartAttribute + " - " + dateEndAttribute);

                incrementation++;
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        incrementation = 1;

        return outputView;
    }
}
