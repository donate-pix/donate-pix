package io.github.donatepix.mercadopago.authentication.model;

import com.google.gson.annotations.SerializedName;

public record OAuthResponse(
        @SerializedName("access_token") String accessToken,
        @SerializedName("token_type") String tokenType,
        @SerializedName("expires_in") long expiresIn,
        String scope,
        @SerializedName("user_id") long userId,
        @SerializedName("refresh_token") String refreshToken,
        @SerializedName("public_key") String publicKey,
        @SerializedName("live_mode") boolean liveMode
) {}