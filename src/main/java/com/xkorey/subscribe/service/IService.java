package com.xkorey.subscribe.service;

import com.xkorey.subscribe.pojo.MessageRequest;

public interface IService {

    String token();

    MessageRequest responseUserTxtMessage(MessageRequest request);


}
