package io.github.donatepix.mercadopago.authentication;

import io.github.donatepix.mercadopago.MercadoPagoClient;
import io.github.donatepix.mercadopago.authentication.model.OAuthRequest;
import io.github.donatepix.mercadopago.authentication.model.OAuthResponse;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * OAuthClient is responsible for handling OAuth authentication with Mercado Pago.
 */
public class OAuthClient {

    public static class Builder {
        private String clientId;
        private String clientSecret;
        private Retrofit retrofit;

        /**
         * Sets the client ID for the OAuthClient.
         * @param clientId the client ID
         * @return the builder instance
         */
        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        /**
         * Sets the client secret for the OAuthClient.
         * @param clientSecret the client secret
         * @return the builder instance
         */
        public Builder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        /**
         * Sets the Retrofit instance for the OAuthClient.
         * @param retrofit the Retrofit instance
         * @return the builder instance
         */
        public Builder retrofit(Retrofit retrofit) {
            this.retrofit = retrofit;
            return this;
        }

        /**
         * Builds the OAuthClient with the provided configurations.
         * @return a new OAuthClient instance
         */
        public OAuthClient build() {
            if (this.clientId == null || this.clientSecret == null) {
                throw new IllegalStateException("Client ID and Client Secret must be set");
            }
            if (this.retrofit == null) {
                this.retrofit = new Retrofit.Builder()
                        .baseUrl(MercadoPagoClient.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return new OAuthClient(this);
        }
    }

    private final OAuthService authService;
    private final String clientId;
    private final String clientSecret;

    private OAuthClient(Builder builder) {
        this.clientId = builder.clientId;
        this.clientSecret = builder.clientSecret;
        this.authService = builder.retrofit.create(OAuthService.class);
    }

    /**
     * Executes an OAuth request to obtain an access token.
     *
     * @param authReq the OAuthRequest
     * @return a CompletableFuture that completes with the OAuth response
     * @throws MercadoPagoAuthenticationException if an error occurs during the OAuth request
     */
    private CompletableFuture<OAuthResponse> executeOAuthRequest(final OAuthRequest authReq) throws MercadoPagoAuthenticationException {
        Objects.requireNonNull(authReq, "The OAuth request body should not be null");

        return this.authService.doAuthRequest(authReq)
                .exceptionally(throwable -> {
                    if (throwable instanceof HttpException) {
                        final HttpException httpException = (HttpException) throwable;
                        final int statusCode = httpException.code();
                        final String errorMessage = httpException.message();
                        throw new MercadoPagoAuthenticationException(String.format("Error getting OAuth token (status code: %d, message: %s)",
                                statusCode, errorMessage), throwable);
                    } else {
                        throw new MercadoPagoAuthenticationException("Unexpected error occurred while getting OAuth token", throwable);
                    }
                });
    }

    /**
     * Obtains an access token using the authorization code flow.
     *
     * @param code the authorization code
     * @param redirectUri the redirect URI
     * @param codeVerifier the code verifier
     * @return a CompletableFuture that completes with the OAuth response
     */
    public CompletableFuture<OAuthResponse> getAccessToken(String code, String redirectUri, String codeVerifier) {
        final OAuthRequest request = new OAuthRequest.Builder()
                .grantType("authorization_code")
                .clientId(this.clientId)
                .clientSecret(this.clientSecret)
                .code(code)
                .redirectUri(redirectUri)
                .codeVerifier(codeVerifier)
                .build();

        return executeOAuthRequest(request);
    }

    /**
     * Refreshes an access token using the refresh token flow.
     *
     * @param refreshToken the refresh token
     * @return a CompletableFuture that completes with the OAuth response
     */
    public CompletableFuture<OAuthResponse> refreshAccessToken(String refreshToken) {
        final OAuthRequest request = new OAuthRequest.Builder()
                .grantType("refresh_token")
                .clientId(this.clientId)
                .clientSecret(this.clientSecret)
                .refreshToken(refreshToken)
                .build();

        return executeOAuthRequest(request);
    }

    /**
     * Obtains an access token using the client credentials flow.
     *
     * @return a CompletableFuture that completes with the OAuth response
     */
    public CompletableFuture<OAuthResponse> getClientCredentialsToken() {
        final OAuthRequest request = new OAuthRequest.Builder()
                .grantType("client_credentials")
                .clientId(this.clientId)
                .clientSecret(this.clientSecret)
                .build();

        return executeOAuthRequest(request);
    }
}
