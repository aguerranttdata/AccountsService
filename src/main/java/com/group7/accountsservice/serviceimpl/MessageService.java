package com.group7.accountsservice.serviceimpl;

import com.group7.accountsservice.dto.LinkRequest;
import com.group7.accountsservice.dto.MessageKafka;
import com.group7.accountsservice.dto.Result;
import com.group7.accountsservice.dto.Yanki;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class MessageService {

    @Autowired
    private StreamBridge streamBridge;

    public boolean sendToYanki(Yanki yanki){
        return streamBridge.send("toyanki-out-0",yanki);
    }
    public boolean sendResult(Result result){
        streamBridge.send("result-out-0",result);
        return result.getStatus().equals("Success");
    }

    public boolean sendToLink(LinkRequest linkRequest){
        return streamBridge.send("link-out-0",linkRequest);
    }

    public boolean sendProcess(MessageKafka messageKafka) {
        return streamBridge.send("proccessaccount-out-0", messageKafka);
    }

}
