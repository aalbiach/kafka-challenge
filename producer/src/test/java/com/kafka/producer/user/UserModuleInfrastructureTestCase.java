package com.kafka.producer.user;

import com.kafka.producer.ContextInfrastructureTestCase;
import com.kafka.producer.user.infrastructure.persistence.InMemoryUserRepository;

public abstract class UserModuleInfrastructureTestCase extends ContextInfrastructureTestCase {

    protected InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();

}
