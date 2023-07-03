package org.devlive.sdk.openai.interceptor;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.ObjectUtils;
import org.devlive.sdk.openai.exception.AuthorizedException;
import org.devlive.sdk.openai.response.DefaultResponse;
import org.devlive.sdk.openai.utils.JsonUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@Slf4j
public class DefaultInterceptor
        implements Interceptor
{
    @Getter
    @Setter
    private String apiKey;

    public DefaultInterceptor()
    {
        log.warn("Default Interceptor");
    }

    public Request headers(Request original)
    {
        return original.newBuilder()
                .header("Authorization", String.format("Bearer %s", this.apiKey))
                .header("Content-Type", "application/json")
                .method(original.method(), original.body())
                .build();
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException
    {
        Request original = chain.request();
        Request request = this.headers(original);
        Response response = chain.proceed(request);
        if (!response.isSuccessful()) {
            log.error("Failed to intercept request");
        }

        if (ObjectUtils.isEmpty(response.body())) {
            log.error("Failure to intercept request because no body");
            Preconditions.checkState(false, "Failure to intercept request because no body");
        }

        // Unauthorized
        if (response.code() == 401) {
            log.error("Failure to intercept request because not authorized");
            JsonUtils<DefaultResponse> jsonUtils = JsonUtils.getInstance();
            DefaultResponse defaultResponse = jsonUtils.getObject(response.body().string(), DefaultResponse.class);
            throw new AuthorizedException(defaultResponse.getError().getMessage());
        }
        return response;
    }
}