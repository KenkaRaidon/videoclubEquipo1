package uabc.videoclubs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uabc.videoclubs.entities.Store;
import uabc.videoclubs.entities.StoreIndex;

public interface StoreRepository extends JpaRepository<Store, Integer>{
    @Query(nativeQuery=true)
    List<StoreIndex> findStoreAndAddress();
}
