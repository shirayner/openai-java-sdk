package org.devlive.sdk.platform.google.interceptor;

import com.google.common.collect.Lists;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import org.devlive.sdk.common.exception.ParamException;
import org.devlive.sdk.common.interceptor.DefaultInterceptor;
import org.devlive.sdk.common.utils.HttpUrlUtils;
import org.devlive.sdk.platform.google.model.VersionModel;

import java.util.List;

@Slf4j
public class GoogleInterceptor
        extends DefaultInterceptor
{
    @Setter
    private VersionModel version;
    @Setter
    private Boolean stream = false;

    public GoogleInterceptor()
    {
        log.info("Google Interceptor");
    }

    @Override
    protected Request prepared(Request original)
    {
        if (StringUtils.isEmpty(this.getApiKey())) {
            log.error("Invalid Google token, must be non-empty");
            throw new ParamException("Invalid Google token, must be non-empty");
        }

        HttpUrl httpUrl = original.url();
        List<String> pathSegments = Lists.newArrayList();
        httpUrl = HttpUrlUtils.removePathSegment(httpUrl);
        // https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=YOUR_API_KEY
        pathSegments.add(0, String.join(":", getModel(), stream ? "streamGenerateContent" : "generateContent"));
        pathSegments.add(0, "models");
        pathSegments.add(0, version.getVersion());
        httpUrl = httpUrl.newBuilder()
                .host(httpUrl.host())
                .port(httpUrl.port())
                .addPathSegments(String.join("/", pathSegments))
                .addQueryParameter("key", this.getApiKey())
                .build();

        if (stream) {
            httpUrl = httpUrl.newBuilder()
                    .addQueryParameter("alt", "sse")
                    .build();
        }

        log.info("Google interceptor request url {}", httpUrl);

        return original.newBuilder()
                .header("Content-Type", "application/json")
                .url(httpUrl)
                .method(original.method(), original.body())
                .build();
    }
}
