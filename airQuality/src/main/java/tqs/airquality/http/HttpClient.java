package tqs.airquality.http;

import java.io.IOException;

public interface HttpClient {
    public String get(String url) throws IOException;
}
