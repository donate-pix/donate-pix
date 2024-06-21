package io.github.donatepix.mercadopago.authentication;

import io.github.donatepix.mercadopago.authentication.model.OAuthRequest;
import io.github.donatepix.mercadopago.authentication.model.OAuthResponse;
import retrofit2.HttpException;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface OAuthService {

    @POST("/oauth/token")
    CompletableFuture<OAuthResponse> doAuthRequest(@Body OAuthRequest authReq) throws HttpException;
}
