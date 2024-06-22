package io.github.donatepix.user.controller;

import io.github.donatepix.user.profile.UserProfile;
import io.github.donatepix.user.service.UserProfileService;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@Controller("/v1/user-profile")
class UserProfileController {

    @Inject
    private UserProfileService userProfileService;

    @Get("/all")
    @Produces(MediaType.APPLICATION_JSON)
    HttpResponse<List<UserProfile>> all(Pageable pageable) {
        return HttpResponse.ok(userProfileService.getAll(pageable));
    }

    @Post("/save")
    @Produces(MediaType.APPLICATION_JSON)
    HttpResponse<UserProfile> save(UserProfile userProfile) {
        return HttpResponse.ok(userProfileService.save(userProfile));
    }

    @Get("/delete/{profileUniqueId}")
    @Produces(MediaType.APPLICATION_JSON)
    HttpResponse<Void> deleteById(UUID profileUniqueId) {
        userProfileService.deleteById(profileUniqueId);
        return HttpResponse.ok();
    }

}
