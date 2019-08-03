package com.xkorey.subscribe.service;

import com.xkorey.subscribe.pojo.Function;
import com.xkorey.subscribe.pojo.MessageRequest;
import org.springframework.ui.Model;

import java.util.List;

public interface IFunctionService {

    void addFunction(Function function);

    String getOne(Model model, String id);

    MessageRequest handleMessage(MessageRequest request);

}
