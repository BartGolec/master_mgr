package com.mgr.bg.Repository;

import com.mgr.bg.Model.CsvEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Bartosz on 11/19/2018.
 */

@Repository
public interface CsvRepository extends JpaRepository<CsvEntity, Long> {
}
