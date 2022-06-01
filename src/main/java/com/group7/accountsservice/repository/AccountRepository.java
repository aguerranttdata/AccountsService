package com.group7.accountsservice.repository;

import com.group7.accountsservice.model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository extends ReactiveMongoRepository<Account,String> {

    Mono<Account> findByNumber(String number);
    Flux<Account> findAccountByClientAndType(String client, String type);
    Flux<Account> findAccountByClient(String client);

}
