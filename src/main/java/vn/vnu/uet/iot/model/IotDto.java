package vn.vnu.uet.iot.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class IotDto {
    private String t1;

    private String h;

    private String t2;

    private String p;
}
