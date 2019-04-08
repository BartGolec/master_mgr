package com.mgr.bg.Service;

import com.mgr.bg.Repository.SingleDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Bartosz on 11/19/2018.
 */

@Service
@Transactional
public class ManualEntityManager {

    private static final Logger log = LoggerFactory.getLogger(ManualEntityManager.class);

    @Autowired
    private SingleDataRepository singleDataRepository;

    public void findEntites(){
        log.info("Find all entitys from " + singleDataRepository.getClass().getName());
        singleDataRepository.findAll().forEach(a -> {
            log.info("Model with id " + a.getId() + " : " + a.toString());
        });
    }

//    @Bean
//    public CommandLineRunner logAllEntities(SingleDataRepository repository) {
//        return (args) -> {
//            // Save entity
//            //repository.save(new SingleDataEntity("", 1,2,3,4,5,6,7));
//
//            // fetch all CsvEntities
//            log.info("Csv Entities found with findAll():");
//            for (SingleDataEntity eachRow : repository.findAll()) {
//                log.info(eachRow.toString());
//            }
//        };
//    }
}
