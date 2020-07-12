package com.example.datacenter.service.data;

import com.example.datacenter.service.observer.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Service
public class DataService {

    Observer observer;
    HashMap<Long, Data> ORIGINAL_DATA = DataProvider.ORIGINAL_DATA;


    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public Map<Long, Data> modifyName(Long id, String name){
        Data data = ORIGINAL_DATA.get(id);
        data.setName(name);
        observer.handle(data, "name");
        ORIGINAL_DATA.replace(id, data);
        return ORIGINAL_DATA;
    }

    public Map<Long, Data> modifyDescription(Long id, String desc){
        Data data = ORIGINAL_DATA.get(id);
        data.setDescription(desc);
        observer.handle(data, "desc");
        ORIGINAL_DATA.replace(id, data);
        return ORIGINAL_DATA;
    }

    public Map<Long, Data> modifyNum(Long id, int num){
        Data data = ORIGINAL_DATA.get(id);
        data.setNum(num);
        observer.handle(data, "num");
        ORIGINAL_DATA.replace(id, data);
        return ORIGINAL_DATA;
    }


}
