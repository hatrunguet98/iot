package vn.vnu.uet.iot.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class IotResponseFirst {
    private String t1;
    private String t2;
    private String h;
    private String p;
    private Instant time;
}
