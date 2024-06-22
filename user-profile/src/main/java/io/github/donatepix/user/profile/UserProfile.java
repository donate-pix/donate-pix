package io.github.donatepix.user.profile;


import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedEntity
public class UserProfile {

    @Id
    @GeneratedValue(GeneratedValue.Type.AUTO)
    private UUID id;

    @NonNull
    @NotBlank
    private String displayName;

    @NonNull
    @NotBlank
    private String avatarUrl;
}
