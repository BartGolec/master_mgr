package com.mgr.bg.Repository;

import com.mgr.bg.Model.BatchDataEntity;
import com.mgr.bg.Model.FileData;
import com.mgr.bg.Model.FileDataCheckboxes;
import com.mgr.bg.Model.SingleDataObject;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUpload {

    String uploadFile(MultipartFile file, String fileType, String successView, String failView, String attributeName, String downloadDirectory, ModelMap modelMap);

    List<SingleDataObject> readCsvFile(String filePath);

    void saveBatchDataFromFile(List<SingleDataObject> singleDataObjectList, MultipartFile file);

    List<FileData> fileDataForPlotsGenerations(FileDataCheckboxes fileDataCheckboxes);

    String convertPngToBase64(String filePath);

    List<BatchDataEntity> convertEnergyListToPowerList(List<BatchDataEntity> batchDataEntityList);
}
