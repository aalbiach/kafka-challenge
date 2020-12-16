package com.kafka.producer.user.domain;

import com.kafka.producer.shared.domain.BaseRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, UserId> {

    Optional<User> findByEmail(@NonNull UserEmail email);

}
