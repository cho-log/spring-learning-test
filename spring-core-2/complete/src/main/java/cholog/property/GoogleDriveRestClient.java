package cholog.property;

public class GoogleDriveRestClient implements RestClient {
    private final String endpoint;

    public GoogleDriveRestClient(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String getEndpoint() {
        return endpoint;
    }
}
