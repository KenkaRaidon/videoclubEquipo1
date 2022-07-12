package uabc.videoclubs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import uabc.videoclubs.entities.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>{
    public abstract Staff findByUsername(String userName);
    
    @Query(value ="select s.email from staff s where s.email ==?1" , nativeQuery=true)
    public abstract String findByEmail(String email);

    @Query(value ="select * from staff s where s.username ==?1" , nativeQuery=true)
    public Staff findStaffByEmail(String username);

}
