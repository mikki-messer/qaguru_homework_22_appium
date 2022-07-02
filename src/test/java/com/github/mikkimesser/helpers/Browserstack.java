package com.github.mikkimesser.helpers;

import com.github.mikkimesser.configuration.BrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {

    public static String videoUrl(String sessionId) {
        BrowserstackConfig browserstackConfig = ConfigFactory.create(BrowserstackConfig.class);

        String url = format(browserstackConfig.sessionJsonUrl(), sessionId);

        //TODO: add owner-stored credentials
        return given()
                .auth().basic(browserstackConfig.login(), browserstackConfig.password())
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}

