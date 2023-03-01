package com.aurosks.kolesnyk.service;

import com.aurosks.kolesnyk.entity.PackageEntity;

import java.util.List;

public interface PackageService {
    Integer create(PackageEntity packageEntity);

    List<PackageEntity> findAll();

    void deleteById(Integer id);
}
