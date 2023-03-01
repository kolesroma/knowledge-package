package com.aurosks.kolesnyk.service;

import com.aurosks.kolesnyk.dao.PackageDao;
import com.aurosks.kolesnyk.entity.PackageEntity;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {
    private final PackageDao packageDao;

    public PackageService(PackageDao packageDao) {
        this.packageDao = packageDao;
    }

    public Integer create(PackageEntity packageEntity) {
        return packageDao.create(packageEntity);
    }

    public List<PackageEntity> findAll() {
        return packageDao.findAll();
    }

    public void deleteById(Integer id) {
        packageDao.deleteById(id);
    }
}
