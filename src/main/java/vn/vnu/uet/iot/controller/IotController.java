package vn.vnu.uet.iot.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.vnu.uet.iot.model.Iot;
import vn.vnu.uet.iot.model.IotResponse;
import vn.vnu.uet.iot.model.IotResponseFirst;
import vn.vnu.uet.iot.service.IotService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public IotResponse getListData(){
        return iotService.getData();
    }

    @GetMapping("/list{date}")
    public IotResponse getDataByDate(@PathVariable String date) {
        return iotService.getDataByDate(date);
    }

    @GetMapping("/new")
    public IotResponseFirst getData(){
        return iotService.getFirst();
    }

    @GetMapping("/checkError")
    public String checkError(){
        iotService.checkError();
        return "success";
    }
  
    @GetMapping("new10")
    public List<IotResponseFirst> getTop10(){
        return iotService.getTop10();
    }

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    @PostMapping("/import")
    public String importData(@RequestParam("file")MultipartFile reapExcelDataFile) throws IOException {
        List<Iot> data = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
            Iot ioit = new Iot();

            XSSFRow row = worksheet.getRow(i);
            getDataRow(row);
        }
        return "success";
    }

    private Iot getDataRow(XSSFRow row) {
        Iot iot = new Iot();
        try {
            String time = getCell(row.getCell(0));
            String t1 = getCell(row.getCell(1));
            String h = getCell(row.getCell(2));
            String t2 = getCell(row.getCell(3));
            String p = getCell(row.getCell(4));

            Date date = format.parse(time);
            System.out.println(date);
            Long t = date.getTime();
            iot.setCreatedOn(t);
            iot.setH(h);
            iot.setT1(t1);
            iot.setT2(t2);
            iot.setP(p);
        }catch (Exception e){
            return null;
        }
        System.out.println(iot);
        return iot;
    }


    private String getCell(Cell cell){
        if(cell == null ) return "";
        if(cell.getCellTypeEnum() == CellType.STRING){
            return cell.getStringCellValue();
        }else if(cell.getCellTypeEnum() == CellType.NUMERIC) {
            Double  cellData = cell.getNumericCellValue();
            return cellData.toString();
        } else {
            return "";

        }
    }
}