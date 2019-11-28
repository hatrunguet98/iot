package vn.vnu.uet.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vnu.uet.iot.model.Iot;
import vn.vnu.uet.iot.model.IotDto;
import vn.vnu.uet.iot.repository.IotRepository;
import vn.vnu.uet.iot.service.IotService;

import java.util.ArrayList;
import java.util.List;

@Service
public class IotServiceImpl implements IotService {
    @Autowired
    private IotRepository iotRepository;

    @Override
    public List<IotDto> getAll() {
        List<IotDto> iotDtoList = new ArrayList<>();
        List<Iot> iots = iotRepository.findAll();
        iots.forEach(iot -> {
            iotDtoList.add(
                    IotDto.builder()
                    .h(iot.getH())
                    .p(iot.getP())
                    .t1(iot.getT1())
                    .t2(iot.getT2())
                    .build()
            );
        });
        return iotDtoList;
    }
}
