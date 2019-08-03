package com.xkorey.subscribe.service.reply;

import com.xkorey.subscribe.pojo.MessageRequest;

@FunctionalInterface
public interface IReplay {
    MessageRequest replay(MessageRequest request);
}
