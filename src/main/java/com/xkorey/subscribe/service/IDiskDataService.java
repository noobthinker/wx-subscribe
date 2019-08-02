package com.xkorey.subscribe.service;

public interface IDiskDataService<T> {


    String getPrefix();

    String getDataFile();

    void loadData();

    void saveData();

}
