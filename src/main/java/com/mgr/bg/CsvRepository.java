package com.mgr.bg;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



/**
 * Created by Bartosz on 11/19/2018.
 */

@Repository
public interface CsvRepository extends CrudRepository<CsvEntity, Integer> {
}
