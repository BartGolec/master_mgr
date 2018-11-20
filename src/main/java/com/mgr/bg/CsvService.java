package com.mgr.bg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Bartosz on 11/19/2018.
 */

@Service
public class CsvService {

    @Autowired
    CsvRepository csvRepository;

    CsvEntity csvEntity = new CsvEntity();

    public String saveEntity(){
        csvEntity.setBOP(4);
        csvEntity.setBOO(343);
        csvEntity.setBPO(343);
        csvEntity.setBPP(4324);
        csvEntity.setCO(4324);
        csvEntity.setCP(3423);
        csvEntity.setDate("Date should be place here");
        csvEntity.setPmax(43243);
        csvEntity.setId(32);
        csvRepository.save(csvEntity);

        String result = "Csv Entity : " + csvEntity.toString();
        return result;


    }
}
