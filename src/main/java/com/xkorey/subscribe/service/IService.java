package com.xkorey.subscribe.service;

import com.xkorey.subscribe.pojo.MessageRequest;
import com.xkorey.subscribe.pojo.MessageResponse;

import java.util.Map;

public interface IService {

    String token();

    MessageResponse responseUserTxtMessage(MessageRequest request);

    Map commonProp();



}
