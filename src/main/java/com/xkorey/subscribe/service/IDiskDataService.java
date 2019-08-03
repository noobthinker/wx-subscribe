package com.xkorey.subscribe.service;

import java.util.List;

public interface IDiskDataService<T> {


    String getPrefix();

    String getDataFile();

    void loadData();

    void saveData();

    List<T> getAllData();

}
