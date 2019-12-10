package vn.vnu.uet.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vnu.uet.iot.model.Iot;
import vn.vnu.uet.iot.model.IotDto;
import vn.vnu.uet.iot.model.IotResponse;
import vn.vnu.uet.iot.model.IotResponseFirst;
import vn.vnu.uet.iot.repository.IotRepository;
import vn.vnu.uet.iot.service.IotService;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IotServiceImpl implements IotService {
    @Autowired
    private IotRepository iotRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<IotDto> getAll() {
        return null;
    }

    @Override
    public Iot save(String t1, String t2, String p, String h) {
        Iot iot = new Iot();
        iot.setT1(t1);
        iot.setT2(t2);
        iot.setH(h);
        iot.setP(p);
        iot.setCreatedOn(System.currentTimeMillis() / 1000 + 7 * 3600);
        return iotRepository.save(iot);
    }

    @Override
    public IotResponseFirst getFirst() {
        Iot iot = iotRepository.getfist();
        Instant time = Instant.ofEpochSecond(iot.getCreatedOn());
        return IotResponseFirst.builder().t1(iot.getT1())
                .t2(iot.getT2())
                .h(iot.getH())
                .p(iot.getP())
                .time(time)
                .build();
    }

    @Override
    public IotResponse getData() {
        LocalDate date = new Date(System.currentTimeMillis() + 7 * 3600 * 1000).toLocalDate();
        Long time = date.toEpochDay() * 86400;
        List<Iot> iotList = iotRepository.getByDate(time, time + 86400);
        Map<Long, List<Iot>> mapData = new HashMap<>();

        for (long i = 1; i <= 24; i++) {
            mapData.put(i, new ArrayList<>());
        }

        int hour = 1;

        for (Iot iot : iotList) {
            Long t = (iot.getCreatedOn() - time) / 3600 + 1;
            mapData.get(t).add(iot);
        }

        IotResponse iotResponse = new IotResponse();
        iotResponse.setDate(date);
        mapData.forEach((k, value) -> {
            Float dht22 = 0F;
            Float gy66 = 0F;
            Float h = 0F;
            Float p = 0F;
            int size = value.size();
            if (size > 0) {
                for (Iot i : value) {
                    dht22 += Float.parseFloat(i.getT1());
                    gy66 += Float.parseFloat(i.getT2());
                    h += Float.parseFloat(i.getH());
                    p += Float.parseFloat(i.getP());
                }
                dht22 /= size;
                gy66 /= size;
                h /= size;
                p /= size;
            }

            IotDto iotDto = IotDto.builder()
                    .hour(k.toString())
                    .Dht22(dht22.toString())
                    .Gy68(gy66.toString())
                    .h(h.toString())
                    .p(p.toString())
                    .build();
            iotResponse.getIndex().add(iotDto);
        });
        return iotResponse;
    }

    @Override
    public List<IotResponseFirst> getTop10() {
        List<Iot> iots = iotRepository.getTop10();
        List<IotResponseFirst> iotResponseFirsts = new ArrayList<>();
        for (Iot iot: iots) {
            Instant time = Instant.ofEpochSecond(iot.getCreatedOn());
            iotResponseFirsts.add(IotResponseFirst.builder().t1(iot.getT1())
                    .t2(iot.getT2())
                    .h(iot.getH())
                    .p(iot.getP())
                    .time(time)
                    .build());
        }
        return iotResponseFirsts;
    }
}
