package com.aurosks.kolesnyk.service;

import com.aurosks.kolesnyk.dao.PackageDao;
import com.aurosks.kolesnyk.entity.PackageEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {
    private final PackageDao packageDao;

    public PackageServiceImpl(PackageDao packageDao) {
        this.packageDao = packageDao;
    }

    @Override
    public PackageEntity create(PackageEntity packageEntity) {
        packageEntity.setCreatedAt(LocalDate.now());
        packageEntity.setId(packageDao.create(packageEntity));
        return packageEntity;
    }

    @Override
    public List<PackageEntity> findAll() {
        return packageDao.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        packageDao.deleteById(id);
    }
}
