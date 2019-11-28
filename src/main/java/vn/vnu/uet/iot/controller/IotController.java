package vn.vnu.uet.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.vnu.uet.iot.model.IotDto;
import vn.vnu.uet.iot.service.IotService;

import java.util.List;

@RestController
@RequestMapping("iot")
public class IotController {
    @Autowired
    private IotService iotService;

    @PostMapping
    public String senserInsert(@RequestBody IotDto iot){
        return "success";
    }

    @GetMapping
    public List<IotDto> getData(){
        return iotService.getAll();
    }
}
