package tqs.airquality.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class AirQualityHttpClient implements HttpClient {

    private CloseableHttpClient client;
    private String token = "51cc13e4a6a2059270314e866c626c37cc938ecc";

    public AirQualityHttpClient() {
        this.client = HttpClients.createDefault();
    }

    @Override
    public String get(String url) throws IOException {
        HttpGet request = new HttpGet(url + "/?token=" + this.token);
        CloseableHttpResponse response = this.client.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
}
