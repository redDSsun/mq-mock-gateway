package com.example.datacenter.service.data;

import com.example.datacenter.service.data.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataProvider {
    static HashMap<Long, Data> ORIGINAL_DATA = new HashMap<>();
    static {
        Data a = new Data(1L,"a","it's a", 10, true);
        Data b = new Data(2L,"b","it's b", 20, false);
        Data c = new Data(3L,"c","it's c", 30, true);
        ORIGINAL_DATA.put(a.getId(),a);
        ORIGINAL_DATA.put(b.getId(),b);
        ORIGINAL_DATA.put(c.getId(),c);
    }

}
