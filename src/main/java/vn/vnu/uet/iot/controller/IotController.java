package vn.vnu.uet.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.vnu.uet.iot.model.Iot;
import vn.vnu.uet.iot.model.IotDto;
import vn.vnu.uet.iot.service.IotService;

import java.util.List;

@RestController
@RequestMapping("iot")
public class IotController {
    @Autowired
    private IotService iotService;

    @GetMapping
    public String senserInsert(@RequestParam String t1, @RequestParam String t2, @RequestParam String h, @RequestParam String p){
        System.out.println(t1 +" " + t2 +" "+ h +" " + p);
        Iot iot = iotService.save(t1,t2,p,h);
        return "success";
    }

    @GetMapping("/list")
    public List<IotDto> getListData(){
        return iotService.getAll();
    }

    @GetMapping("/new")
    public IotDto getData(){
        return iotService.getFirst();
    }
}
