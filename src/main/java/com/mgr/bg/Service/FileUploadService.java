package com.mgr.bg.Service;

import com.mgr.bg.Model.BatchDataEntity;
import com.mgr.bg.Model.FileData;
import com.mgr.bg.Model.FileDataCheckboxes;
import com.mgr.bg.Model.SingleDataObject;
import com.mgr.bg.Repository.BatchDataRepository;
import com.mgr.bg.Repository.FileUpload;
import com.mgr.bg.Repository.SingleDataRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class FileUploadService implements FileUpload {

    @Autowired
    private SingleDataRepository singleDataRepository;

    @Autowired
    private BatchDataRepository batchDataRepository;

    private static final Logger log = LoggerFactory.getLogger(FileUploadService.class);

    private static final int DATA_ODCZYTU_IDX = 0;
    private static final int PMAX_IDX = 1;
    private static final int CP_IDX = 2;
    private static final int CO_IDX = 3;
    private static final int BPP_IDX = 4;
    private static final int BPO_IDX = 5;
    private static final int BOO_IDX = 6;
    private static final int BOP_IDX = 7;

    @Override
    public String uploadFile(MultipartFile file, String fileType, String successView, String failView, String attributeName, String downloadDirectory, ModelMap modelMap) {
        if(!file.isEmpty() && Objects.requireNonNull(file.getOriginalFilename()).endsWith(fileType)){
            try {
                File transferFile = new File(downloadDirectory);
                file.transferTo(transferFile);
                log.info("Uploaded file name : " + file.getOriginalFilename());

            } catch (Exception e) {

                e.printStackTrace();

                return failView;
            }

            modelMap.addAttribute(attributeName, file);

            return successView;
        }
        else
            if(file.isEmpty()) {
                log.info("File " + file.getOriginalFilename() + " is empty");
        }
            else if(!Objects.requireNonNull(file.getOriginalFilename()).endsWith(fileType)){
                log.info("File " + file.getOriginalFilename() + " has incorrect file type : " + file.getOriginalFilename().substring((file.getOriginalFilename().length()-4) , (file.getOriginalFilename().length()-1)));
            }
            return failView;
    }

    @Override
    public List<SingleDataObject> readCsvFile(String filePath) {
            BufferedReader fileReader = null;

            try{
                List<SingleDataObject> singleDataObjectList = new ArrayList<>();
                String line = "";
                fileReader = new BufferedReader(new FileReader(filePath));

                // Read CSV header and 2 redundant lines;
                fileReader.readLine();
                fileReader.readLine();
                fileReader.readLine();
                fileReader.readLine();


                while((line = fileReader.readLine())!=null){
                    String[] tokens = line.split(";");
                    if(tokens.length>0){
                        SingleDataObject singleDataObject = new SingleDataObject(
                                tokens[DATA_ODCZYTU_IDX],
                                tokens[PMAX_IDX],
                                tokens[CP_IDX],
                                tokens[CO_IDX],
                                tokens[BPP_IDX],
                                tokens[BPO_IDX],
                                tokens[BOO_IDX],
                                tokens[BOP_IDX]
                        );
                        singleDataObjectList.add(singleDataObject);
                    }
                }
                return singleDataObjectList;

            }   catch (Exception e){
                log.info("Reading CSV Error!");
                e.printStackTrace();
            } finally {
                try {
                    Objects.requireNonNull(fileReader).close();
                } catch (IOException e){
                    log.info("Closing fileReader Error!");
                    e.printStackTrace();
                }
            }
            return null;
        }

    @Override
    public void saveBatchDataFromFile(List<SingleDataObject> singleDataObjectList, MultipartFile file) {
        if(!(batchDataRepository.findByFileName(file.getOriginalFilename()).size() == 0)){
            log.info(file.getOriginalFilename() +  " already exists in the database!. The file date wasn't saved.");
        }
        if(batchDataRepository.findByFileName(file.getOriginalFilename()).size() == 0) {
            for (SingleDataObject singleDataObject : singleDataObjectList) {
                BatchDataEntity batchDataEntity = new BatchDataEntity(
                        file.getOriginalFilename(),
                        singleDataObject.getDate(),
                        Double.valueOf(singleDataObject.getPmax().replace(",", ".").replace(" ", "")),
                        Double.valueOf(singleDataObject.getCP().replace(",", ".").replace(" ", "")),
                        Double.valueOf(singleDataObject.getCO().replace(",", ".").replace(" ", "")),
                        Double.valueOf(singleDataObject.getBPP().replace(",", ".").replace(" ", "")),
                        Double.valueOf(singleDataObject.getBPO().replace(",", ".").replace(" ", "")),
                        Double.valueOf(singleDataObject.getBOO().replace(",", ".").replace(" ", "")),
                        Double.valueOf(singleDataObject.getBOP().replace(",", ".").replace(" ", ""))
                );
                    batchDataRepository.save(batchDataEntity);
            }
        }
    }

    @Override
    public List<FileData> fileDataForPlotsGenerations(FileDataCheckboxes fileDataCheckboxes) {
        System.out.println(fileDataCheckboxes.isBop());
        System.out.println(fileDataCheckboxes.isBpo());
        System.out.println(fileDataCheckboxes.isPmax());
        System.out.println(fileDataCheckboxes.isCo());
        System.out.println(fileDataCheckboxes.isCp());
        System.out.println(fileDataCheckboxes.isBpp());
        System.out.println(fileDataCheckboxes.isBoo());

        List<FileData> fileDataList = new ArrayList<>();

        if(fileDataCheckboxes.isPmax()){
            fileDataList.add(FileData.PMAX);
        }
        if(fileDataCheckboxes.isCp()){
            fileDataList.add(FileData.CP);
        }
        if(fileDataCheckboxes.isCo()){
            fileDataList.add(FileData.CO);
        }
        if(fileDataCheckboxes.isBpp()){
            fileDataList.add(FileData.BPP);
        }
        if(fileDataCheckboxes.isBpo()){
            fileDataList.add(FileData.BPO);
        }
        if(fileDataCheckboxes.isBoo()){
            fileDataList.add(FileData.BOO);
        }
        if(fileDataCheckboxes.isBop()){
            fileDataList.add(FileData.BOP);
        }
        System.out.println(fileDataList.size());
        return fileDataList;
    }

    @Override
    public String convertPngToBase64(String filePath) {
        log.info("Convert PNG to Base64 from file path : " + filePath);
        String base64 = "";
        try {
            InputStream inputStream = new FileInputStream(new File(filePath));
            byte [] imageBytes = IOUtils.toByteArray(inputStream);
            base64 = Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }

    @Override
    public List<BatchDataEntity> convertEnergyListToPowerList(List<BatchDataEntity> batchDataEntityList) {
        List <BatchDataEntity> batchEnergyDataList = new ArrayList<>();

        for(int i = 0; i<batchDataEntityList.size()-2; i++){
            BatchDataEntity batchDataEntityForEnergy = new BatchDataEntity();
            batchDataEntityForEnergy.setFileName(batchDataEntityList.get(i).getFileName());
            batchDataEntityForEnergy.setDate(batchDataEntityList.get(i).getDate());
            batchDataEntityForEnergy.setPmax(batchDataEntityList.get(i).getPmax());

            if(batchDataEntityList.get(i + 1).getCP() != 0 || batchDataEntityList.get(i).getCP() !=0){
                double cp = BigDecimal.valueOf((batchDataEntityList.get(i + 1).getCP() - batchDataEntityList.get(i).getCP()) / 24).setScale(2, RoundingMode.HALF_UP).doubleValue();
            batchDataEntityForEnergy.setCP(cp);
        }
            else{
                batchDataEntityForEnergy.setCP(0.0);
            }

            if(batchDataEntityList.get(i + 1).getCO() != 0 || batchDataEntityList.get(i).getCO() !=0){
                double co = BigDecimal.valueOf((batchDataEntityList.get(i + 1).getCO() - batchDataEntityList.get(i).getCO()) / 24).setScale(2, RoundingMode.HALF_UP).doubleValue();
                batchDataEntityForEnergy.setCO(co);
            }
            else{
                batchDataEntityForEnergy.setCO(0.0);
            }

            if(batchDataEntityList.get(i + 1).getBPP() != 0 || batchDataEntityList.get(i).getBPP() !=0){
                double bpp = BigDecimal.valueOf((batchDataEntityList.get(i + 1).getBPP() - batchDataEntityList.get(i).getBPP()) / 24).setScale(2, RoundingMode.HALF_UP).doubleValue();
                batchDataEntityForEnergy.setBPP(bpp);
            }
            else{
                batchDataEntityForEnergy.setBPP(0.0);
            }

            if(batchDataEntityList.get(i + 1).getBPO() != 0 || batchDataEntityList.get(i).getBPO() !=0){
                double bpo = BigDecimal.valueOf((batchDataEntityList.get(i + 1).getBPO() - batchDataEntityList.get(i).getBPO()) / 24).setScale(2, RoundingMode.HALF_UP).doubleValue();
                batchDataEntityForEnergy.setBPO(bpo);
            }
            else{
                batchDataEntityForEnergy.setBPO(0.0);
            }

            if(batchDataEntityList.get(i + 1).getBOO() != 0 || batchDataEntityList.get(i).getBOO() !=0){
                double boo = BigDecimal.valueOf((batchDataEntityList.get(i + 1).getBOO() - batchDataEntityList.get(i).getBOO()) / 24).setScale(2, RoundingMode.HALF_UP).doubleValue();
                batchDataEntityForEnergy.setBOO(boo);
            }
            else{
                batchDataEntityForEnergy.setBOO(0.0);
            }

            if(batchDataEntityList.get(i + 1).getBOP() != 0 || batchDataEntityList.get(i).getBOP() !=0){
                double bop = BigDecimal.valueOf(((batchDataEntityList.get(i + 1).getBOP() * 1000) - (batchDataEntityList.get(i).getBOP() * 1000)) / 24).setScale(2, RoundingMode.HALF_UP).doubleValue();
                batchDataEntityForEnergy.setBOP(bop);
            }
            else{
                batchDataEntityForEnergy.setBOP(0.0);
            }
            batchEnergyDataList.add(batchDataEntityForEnergy);
        }

        return batchEnergyDataList;
    }


}
