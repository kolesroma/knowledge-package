package com.aurosks.kolesnyk.service;

import com.aurosks.kolesnyk.entity.PackageEntity;

import java.util.List;

public interface PackageService {
    PackageEntity create(PackageEntity packageEntity);

    List<PackageEntity> findAll();

    void deleteById(Integer id);
}
