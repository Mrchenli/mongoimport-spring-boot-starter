package com.kiibos.mongoexport.controller;

import com.kiibos.mongoexport.service.MongoBatchImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName MongoBatchImportController
 * @Description TODO
 * @Author cl
 * @Date 2018/12/29 上午10:33
 **/
@RestController
@Slf4j
public class MongoBatchImportController {

    @Autowired
    private MongoBatchImportService mongoBatchImportService;


    //多文件上传
    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
    public String handleFileUpload(HttpServletRequest request) {
        return mongoBatchImportService.batchImport(request);
    }


}
