package requestutils;

import com.google.common.base.Joiner;
import com.squareup.okhttp.*;
import lombok.Getter;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class RestRequest {
    private static final OkHttpClient client =  new OkHttpClient();
    String hostName;
    static Map<String, String> headers = new HashMap<>();
    static Map<String, String> queryParams = new HashMap<>();
    private Request request;
    private Response response;
    private JSONObject jsonResponse;
    private int statusCode;


    public RestRequest get() {
        this.request = new Request.Builder()
                .url("https://" + System.getProperty("hostName")
                        + setPath() + getQueryString())
                .build();
        return this;
    }

    public RestRequest post() {
        this.request = new Request.Builder()
                .url("https://" + System.getProperty("hostName")
                        + setPath() + getQueryString())
                .post(RequestBody
                        .create(MediaType.parse("application/json"), setDto()))
                .build();
        return this;
    }

    @SneakyThrows
    public RestRequest sendRequest() {
        client.interceptors().add(new RequestInterceptor());
        Call call = client.newCall(request);
        this.response = call.execute();
        this.jsonResponse = new JSONObject(response.body().string());
        this.statusCode = response.code();
        return this;
    }


    private String getQueryString() {
        if (queryParams.size() == 0) {
            return "";
        }
        return "?" + Joiner.on("&")
                .withKeyValueSeparator("=")
                .join(queryParams);
    }

    public abstract String setPath();

    public abstract String setDto();
}
