package com.group7.accountsservice.serviceimpl;
import com.group7.accountsservice.dto.MessageKafka;
import com.group7.accountsservice.model.Account;
import com.group7.accountsservice.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapReactive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Service
@Slf4j
public class ListenService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MessageService messageService;

    @Bean
    Consumer<MessageKafka> proccessaccount() {
        return messageKafka -> {
            if(messageKafka.getType().equalsIgnoreCase("request")) {
                if(messageKafka.getDocument().equalsIgnoreCase("account")) {
                    accountRepository.findByNumber(messageKafka.getNumber())
                            .hasElement()
                            .map(hasElement -> {
                                messageKafka.setType("response");

                                if(hasElement) {
                                    messageKafka.setSuccess(true);
                                }
                                else {
                                    messageKafka.setSuccess(false);
                                }
                                return messageService.sendProcess(messageKafka);
                            })
                            .subscribe();

                }
            }
        };
    }
}
