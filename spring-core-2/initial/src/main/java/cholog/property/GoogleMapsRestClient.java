package cholog.property;

public class GoogleMapsRestClient implements RestClient {
    private final String endpoint;

    public GoogleMapsRestClient(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String getEndpoint() {
        return endpoint;
    }
}
