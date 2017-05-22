package com.itis.shop.business.service.impl;

import com.itis.shop.business.repository.StorageRepository;
import com.itis.shop.business.service.StorageService;
import com.itis.shop.model.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created on 17.05.17.
 */
@Service
@Transactional
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository storageRepository;

    @Override
    public Storage saveOrUpdate(Storage storage) {
        return storageRepository.save(storage);
    }

    @Override
    public Storage find(Long id) {
        return storageRepository.findOne(id);
    }

    @Override
    public List<Storage> findAll() {
        return storageRepository.findAll();
    }
}
