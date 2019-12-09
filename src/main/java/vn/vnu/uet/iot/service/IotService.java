package vn.vnu.uet.iot.service;

import vn.vnu.uet.iot.model.Iot;
import vn.vnu.uet.iot.model.IotDto;
import vn.vnu.uet.iot.model.IotResponse;
import vn.vnu.uet.iot.model.IotResponseFirst;

import java.util.List;

public interface IotService {
    List<IotDto> getAll();
    Iot save(String t1, String t2, String p, String h);
    IotResponseFirst getFirst();

    IotResponse getData();
}
