package shop.ayotl.backend.common.constant;

public final class AuthenticationConstants {
    private AuthenticationConstants() {}

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_TYPE_PREFIX = "Bearer ";
    public static final String LOGIN_URL_MAPPING = "/api/login";
    public static final String CREATE_USER_URL_MAPPING = "/api/users";
}
