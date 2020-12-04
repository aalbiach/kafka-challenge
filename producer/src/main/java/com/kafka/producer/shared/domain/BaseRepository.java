package com.kafka.producer.shared.domain;

import java.util.Optional;

public interface BaseRepository<ENTITY, ID> {

    ENTITY saveOrUpdate(ENTITY data);

    Optional<ENTITY> findById(ID id);

//    ENTITY update(ENTITY data);

    void deleteById(ID id);

    void delete(ENTITY id);

}
