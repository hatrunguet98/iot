package vn.vnu.uet.iot.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Data
@Getter
@Setter
public class IotResponse {
    private Date date;
    private List<IotDto> index;
}
