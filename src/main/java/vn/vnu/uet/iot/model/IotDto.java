package vn.vnu.uet.iot.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
public class IotDto {
    private String hour;

    private String Dht22;

    private String Gy68;

    private String h;

    private String p;
}
