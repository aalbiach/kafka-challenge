package com.kafka.producer.user.domain;

import com.kafka.producer.shared.domain.AggregateRoot;
import com.kafka.producer.user.domain.event.UserCreatedDomainEvent;
import com.kafka.producer.user.domain.event.UserDeletedDomainEvent;
import com.kafka.producer.user.domain.event.UserUpdatedDomainEvent;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(fluent = true)
public class User extends AggregateRoot {

    @NonNull
    private final UserId     id;
    @NonNull
    private       UserName   name;
    @NonNull
    private       UserEmail  email;
    @NonNull
    private       UserActive active = UserActive.inactive();

    public static User create(UserId id, UserName name, UserEmail email) {
        var user = new User(id, name, email);

        user.record(new UserCreatedDomainEvent(id.value(), name.value(), email.value(), user.active().value()));

        return user;
    }

    public void updateName(UserName name) {
        if (!Objects.equals(this.name, name)) {
            this.name = name;

            record(new UserUpdatedDomainEvent(id.value(), name.value(), email.value(), active.value()));
        }
    }

    public void updateEmail(UserEmail email) {
        if (!Objects.equals(this.email, email)) {
            this.email = email;

            record(new UserUpdatedDomainEvent(id.value(), name.value(), email.value(), active.value()));
        }
    }

    public void activate(UserActive active) {
        if (!Objects.equals(this.active, active)) {
            this.active = active;

            record(new UserUpdatedDomainEvent(id.value(), name.value(), email.value(), active.value()));
        }
    }

    public void delete() {
        if (checkCanDelete()) {
            record(new UserDeletedDomainEvent(id.value(), name.value(), email.value(), active.value()));
        }
    }

    private boolean checkCanDelete() {
        return true;
    }

}
