package com.kafka.producer.user.infrastructure.persistence;

import com.kafka.producer.user.domain.User;
import com.kafka.producer.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class InMemoryUserRepository implements UserRepository {

//    private final UuidGenerator generator;

    private final Map<String, User> users = new LinkedHashMap<>();

    @Override
    public User saveOrUpdate(User user) {
        return users.computeIfAbsent(user.id().value(), key -> user);
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public void deleteById(String id) {
        users.remove(id);
    }

    @Override
    public void delete(User user) {
        users.remove(user.id().value(), user);
    }

}
