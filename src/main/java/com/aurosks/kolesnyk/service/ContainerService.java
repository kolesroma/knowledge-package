package com.aurosks.kolesnyk.service;

import com.aurosks.kolesnyk.entity.ContainerEntity;
import com.aurosks.kolesnyk.entity.PackageEntity;

import java.util.List;

public interface ContainerService {
    List<ContainerEntity> findAll();

    void deleteById(Integer id);

    List<PackageEntity> findAllById(Integer containerId);
}
