package config;


import org.aeonbits.owner.Config;
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:${DSConfigFile}",
        "classpath:config/app.properties",
        "system:properties",
        "system:env"
})
public interface TestConfigType extends Config {

    @Key("browser")
    String getBrowser();

}

