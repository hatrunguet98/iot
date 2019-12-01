package vn.vnu.uet.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vnu.uet.iot.model.Iot;
import vn.vnu.uet.iot.model.IotDto;
import vn.vnu.uet.iot.repository.IotRepository;
import vn.vnu.uet.iot.service.IotService;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class IotServiceImpl implements IotService
{
    @Autowired
    private IotRepository iotRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
                    .time(iot.getCreatedOn().format(formatter))
                    .build()
            );
        });
        return iotDtoList;
    }

    @Override
    public Iot save(String t1, String t2, String p, String h) {
        Iot iot = new Iot();
        iot.setT1(t1);
        iot.setT2(t2);
        iot.setH(h);
        iot.setP(p);
        return iotRepository.save(iot);
    }

    @Override
    public IotDto getFirst() {
        Iot iot = iotRepository.getfist();
        return IotDto.builder()
                .t1(iot.getT1())
                .t2((iot.getT2()))
                .h(iot.getH())
                .p(iot.getP())
                .time(iot.getCreatedOn().format(formatter)).build();
    }
}
