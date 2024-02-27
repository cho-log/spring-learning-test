package cholog.property.config;

import cholog.property.GoogleDriveRestClient;
import cholog.property.GoogleMapsRestClient;
import org.springframework.core.env.Environment;

// TODO: Java-based Configuration을 하기 위한 클래스로 지정하기
// TODO: ext-api.properties 파일을 활용하기 위한 설정 추가하기
public class PropertySourceConfig {

    private final Environment env;

    public PropertySourceConfig(Environment env) {
        this.env = env;
    }

    // TODO: ext-api.properties의 google.api.endpoint 값을 Environment를 사용해서 가져오기
    // TODO: 위 endpoint 값을 사용하여 GoogleMapsRestClient를 빈으로 등록하기
    public GoogleMapsRestClient googleMapsRestClient() {
        return new GoogleMapsRestClient("");
    }

    // TODO: ext-api.properties의 google.api.endpoint 값을 어노테이션을 사용해서 가져오기
    // TODO: 위 endpoint 값을 사용하여 GoogleMapsRestClient를 빈으로 등록하기
    public GoogleDriveRestClient googleDriveRestClient() {
        return new GoogleDriveRestClient("");
    }
}
