package org.devlive.sdk.platform.google;

import io.reactivex.Single;
import org.devlive.sdk.common.DefaultApi;
import org.devlive.sdk.platform.google.entity.ChatEntity;
import org.devlive.sdk.platform.google.response.ChatResponse;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface GoogleApi
        extends DefaultApi
{
    @POST
    Single<ChatResponse> fetchChatCompletions(@Url String url,
            @Body ChatEntity configure);
}
