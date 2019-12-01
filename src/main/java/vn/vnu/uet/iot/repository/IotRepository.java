package vn.vnu.uet.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.vnu.uet.iot.model.Iot;

import java.util.List;

@Repository
public interface IotRepository extends JpaRepository<Iot,Integer> {
    List<Iot> findAll();
    @Query(value = "select * from Iot order by created_on desc limit 1", nativeQuery = true)
    Iot getfist();
    //Iot findTop1ByCreatedOnDesc();
}
