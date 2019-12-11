package vn.vnu.uet.iot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import vn.vnu.uet.iot.service.IotService;

@Configuration
@EnableScheduling
public class SpringConfig {
    @Autowired
    IotService iotService;

    @Scheduled(fixedDelay = 3600000)
    public void autoCleanJwtLock() {
        iotService.checkError();
    }
}
