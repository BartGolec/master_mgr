package com.mgr.bg.Service;

import com.mgr.bg.Model.NewLoad;

import java.util.ArrayList;
import java.util.List;

public class NewLoadDto {
    List<NewLoad> loadList;

    public void addLoadToList(NewLoad load) {
        this.loadList.add(load);
    }

    public List<NewLoad> getLoadList() {
        return loadList;
    }

    public void setLoadList(List<NewLoad> loadList) {
        this.loadList = loadList;
    }

    public NewLoadDto(List<NewLoad> loadList) {
        this.loadList = loadList;
    }

    public NewLoadDto(){
        this.loadList = new ArrayList<>();
    }
}
