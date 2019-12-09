package vn.vnu.uet.iot.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class IotResponse {
    private LocalDate date;
    private List<IotDto> index = new ArrayList<>();
}
