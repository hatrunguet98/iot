package vn.vnu.uet.iot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("DHT-22")
    private String Dht22;

    @JsonProperty("GY-68")
    private String Gy68;

    private String h;

    private String p;
}
