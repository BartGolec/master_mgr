package com.mgr.bg.Controller;

import com.mgr.bg.Model.BatchDataEntity;
import com.mgr.bg.Model.FileData;
import com.mgr.bg.Model.FileDataCheckboxes;
import com.mgr.bg.Model.SingleDataObject;
import com.mgr.bg.Repository.BatchDataRepository;
import com.mgr.bg.Service.FileUploadService;
import com.mgr.bg.Service.MultiplyLineChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
@Transactional
public class FileController {
    private static final String downloadDirectory = "C:\\Praca Magisterska\\Application Files";
    String base64image = "";
    //private static final String downloadDirectory = "C:\\Users\\Bartek\\Desktop\\mgr\\src\\main\\resources\\templates";

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private BatchDataRepository batchDataRepository;

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public ModelAndView homePage() {
        return new ModelAndView("fileUploadView", "fileDataCheckboxes", new FileDataCheckboxes());
    }

    @PostMapping(value = "/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file,  ModelMap modelMap, @ModelAttribute("fileDataCheckboxes") FileDataCheckboxes fileDataCheckboxes, Model model) {
        String fileDir = downloadDirectory + "\\" + file.getOriginalFilename();
        String outputView = fileUploadService.uploadFile(file,
                ".csv",
                "fileUploadSuccess",
                "fileUploadFailView",
                "file",
                fileDir,
                modelMap);

        List<SingleDataObject> singleDataObjectList = fileUploadService.readCsvFile(fileDir);
        fileUploadService.saveBatchDataFromFile(singleDataObjectList, file);

        List<FileData> fileDataList = fileUploadService.fileDataForPlotsGenerations(fileDataCheckboxes);

        List<BatchDataEntity> batchDataEntities = batchDataRepository.findByFileName(file.getOriginalFilename());

        for(FileData fileData : fileDataList) {

            try {
                MultiplyLineChart multiplyLineChart = new MultiplyLineChart(fileData, batchDataEntities);
                base64image = fileUploadService.convertPngToBase64("C:\\Users\\Bartek\\Desktop\\mgr\\src\\main\\resources\\static\\images" + "\\" + multiplyLineChart.getFileName() + ".png");
                System.out.println(base64image);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        String chartAttribute = "data:image/png;base64, " + base64image;
        modelMap.addAttribute("chartName", chartAttribute);

        return outputView;
    }
}
