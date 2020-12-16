package com.kafka.producer.shared.domain;

import com.kafka.producer.shared.domain.vo.Identifier;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface BaseRepository<ENTITY extends AggregateRoot, ID extends Identifier> {

    ENTITY saveOrUpdate(@NonNull ENTITY data);

    Optional<ENTITY> findById(@NonNull ID id);

//    ENTITY update(@NonNull ENTITY data);

    void delete(@NonNull ID id);

    void delete(@NonNull ENTITY id);

}
