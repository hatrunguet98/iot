package vn.vnu.uet.iot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.vnu.uet.iot.model.Iot;

import java.util.List;

public interface IotRepository extends JpaRepository<Iot,Integer> {
    List<Iot> findAll();
}
