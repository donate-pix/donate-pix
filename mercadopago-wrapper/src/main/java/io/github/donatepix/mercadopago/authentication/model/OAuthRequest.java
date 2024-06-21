package io.github.donatepix.mercadopago.authentication.model;

import com.google.gson.annotations.SerializedName;

public record OAuthRequest(
        @SerializedName("grant_type") String grantType,
        @SerializedName("client_id") String clientId,
        @SerializedName("client_secret") String clientSecret,
        @SerializedName("refresh_token") String refreshToken,
        @SerializedName("code") String code,
        @SerializedName("redirect_uri") String redirectUri,
        @SerializedName("code_verifier") String codeVerifier
) {
    public static class Builder {
        private String grantType;
        private String refreshToken;
        private String code;
        private String clientId;
        private String clientSecret;
        private String redirectUri;
        private String codeVerifier;

        public Builder grantType(String grantType) {
            this.grantType = grantType;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder redirectUri(String redirectUri) {
            this.redirectUri = redirectUri;
            return this;
        }

        public Builder codeVerifier(String codeVerifier) {
            this.codeVerifier = codeVerifier;
            return this;
        }

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public OAuthRequest build() {
            return new OAuthRequest(
                    grantType,
                    this.clientId,
                    this.clientSecret,
                    refreshToken,
                    code,
                    redirectUri,
                    codeVerifier
            );
        }
    }
}