package com.github.mikkimesser.configuration;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties",
        "classpath:configuration/browserstackConfig.properties"})
public interface BrowserstackConfig extends Config {
    @Key("browserstack_login")
    String login();

    @Key("browserstack_password")
    String password();

    @Key("browserstack_applId")
    String applId();

    @Key("browserstack_device")
    String device();

    @Key("browserstack_os_version")
    String osVersion();

    @Key("browserstack_url")
    String browserstackURL();

    @Key("browserstack_session_json_url")
    String sessionJsonUrl();
}
