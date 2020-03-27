package demo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;


/**
 * PropertySource
 */
@Slf4j
public class SystemInfoEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        Resource resource1 = new ClassPathResource("systemInfo.properties");
        Resource resource2 = new ClassPathResource("mail.properties");
        try {
            PropertySource propertySource1 = loader.load("systemInfo",resource1).get(0);
            PropertySource propertySource2 = loader.load("mail",resource2).get(0);
            propertySources.addFirst(propertySource1);
            propertySources.addAfter("systemInfo",propertySource2);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
