package com.aurosks.kolesnyk.service;

import com.aurosks.kolesnyk.dao.ContainerDao;
import com.aurosks.kolesnyk.entity.ContainerEntity;
import com.aurosks.kolesnyk.entity.PackageEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContainerServiceImpl implements ContainerService {
    private final ContainerDao containerDao;

    public ContainerServiceImpl(ContainerDao containerDao) {
        this.containerDao = containerDao;
    }

    @Override
    public List<ContainerEntity> findAll() {
        return containerDao.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        containerDao.deleteById(id);
    }

    @Override
    public List<PackageEntity> findAllById(Integer containerId) {
        return containerDao.findAllById(containerId);
    }
}
