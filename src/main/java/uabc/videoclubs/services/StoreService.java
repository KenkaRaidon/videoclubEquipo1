package uabc.videoclubs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.videoclubs.entities.Store;
import uabc.videoclubs.entities.StoreIndex;
import uabc.videoclubs.repository.StoreRepository;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;

    public List<StoreIndex> findStoreAndAddress() {
        return storeRepository.findStoreAndAddress();
    }

    public Optional<Store> findStoreById(Integer storeId) {
        return storeRepository.findById(storeId);
    }
}
