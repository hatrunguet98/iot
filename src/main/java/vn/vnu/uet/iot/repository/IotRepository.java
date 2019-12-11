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

    @Query(value = "select * from iot where created_on >= :t1 and created_on <= :t2", nativeQuery = true)
    List<Iot> getByDate(Long t1, Long t2);

    @Query(value = "select * from Iot order by created_on desc limit 10", nativeQuery = true)
    List<Iot> getTop10();
}