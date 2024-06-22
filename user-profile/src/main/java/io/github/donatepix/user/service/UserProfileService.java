package io.github.donatepix.user.service;

import io.github.donatepix.user.profile.UserProfile;
import io.github.donatepix.user.repository.UserProfileRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.UUID;

@Singleton
public class UserProfileService {

    @Inject
    private UserProfileRepository repository;

    @Inject
    private CacheService cacheService;

    public List<UserProfile> getAll(Pageable pageable) {



        Page<UserProfile> page = repository.findAll(pageable);
        return page.getContent();
    }

    public UserProfile save(UserProfile userProfile) {
        return repository.save(userProfile);
    }

    public void deleteById(UUID profileUniqueId) {
        repository.deleteById(profileUniqueId);
    }
}