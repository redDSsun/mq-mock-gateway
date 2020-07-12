package com.example.datacenter.controller;

import com.example.datacenter.service.data.DataProvider;
import com.example.datacenter.service.data.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class DataController {

    @Autowired
    DataService dataService;

    @RequestMapping("/id/{id}/name/{name}")
    public Map modifyName(@PathVariable("id") Long id, @PathVariable("name") String name){
        return dataService.modifyName(id, name);
    }

    @RequestMapping("/id/{id}/desc/{desc}")
    public Map modifyDesc(@PathVariable("id") Long id, @PathVariable("desc") String desc){
        return dataService.modifyDescription(id, desc);
    }

    @RequestMapping("/id/{id}/num/{num}")
    public Map modifyDesc(@PathVariable("id") Long id, @PathVariable("num") int num){
        return dataService.modifyNum(id, num);
    }

}
