package com.mgr.bg.Repository;

import com.mgr.bg.Model.BatchDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchDataRepository extends JpaRepository<BatchDataEntity, Long> {
    List<BatchDataEntity> findByFileName(String fileName);
}
