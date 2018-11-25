package com.mgr.bg.Service;

import com.mgr.bg.Entity.CsvEntity;
import com.mgr.bg.Repository.CsvRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;

/**
 * Created by Bartosz on 11/19/2018.
 */

@Controller
@Transactional
public class CsvService {

    private static final Logger log = LoggerFactory.getLogger(CsvService.class);

    @Autowired
    private CsvRepository csvRepository;

    public void findEntites(){
        log.info("Find all entitys from " + csvRepository.getClass().getName());
        csvRepository.findAll().forEach(a -> {
            log.info("Entity with id " + a.getId() + " : " + a.toString());
        });
    }

    // Bean provides Spring context
    @Bean
    public CommandLineRunner oparateOnEntitesFromCMD(CsvRepository repository) {
        return (args) -> {
            // Save entity
            repository.save(new CsvEntity("data", 1,2,3,4,5,6,7));

            // fetch all CsvEntities
            log.info("Csv Entities found with findAll():");
            for (CsvEntity eachRow : repository.findAll()) {
                log.info(eachRow.toString());
            }
        };
    }
}
