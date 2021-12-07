package config;
import org.aeonbits.owner.ConfigCache;

public class TestConfig {
    public static TestConfigType getInstance(){return instance;}
    private final static TestConfigType instance = ConfigCache.getOrCreate(TestConfigType.class);
}
