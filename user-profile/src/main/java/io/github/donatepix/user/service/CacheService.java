package io.github.donatepix.user.service;

import io.lettuce.core.api.StatefulRedisConnection;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CacheService {

    @Inject
    private StatefulRedisConnection<String, String> connection;

    public void put(String key, String value) {
        connection.sync().set(key, value);
    }

    public String get(String key) {
        return connection.sync().get(key);
    }
}
