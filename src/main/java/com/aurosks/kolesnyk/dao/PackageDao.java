package com.aurosks.kolesnyk.dao;

import com.aurosks.kolesnyk.entity.PackageEntity;

import java.util.List;

public interface PackageDao {
    Integer create(PackageEntity packageEntity);

    List<PackageEntity> findAll();

    void deleteById(Integer id);
}
