package io.github.donatepix.user.repository;

import io.github.donatepix.user.profile.UserProfile;
import io.micronaut.data.mongodb.annotation.MongoRepository;
import io.micronaut.data.repository.PageableRepository;

import java.util.UUID;

@MongoRepository
public interface UserProfileRepository extends PageableRepository<UserProfile , UUID> {
}
