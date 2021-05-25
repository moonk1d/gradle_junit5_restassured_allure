package utils;

import static java.lang.System.getProperty;
import static java.util.Optional.ofNullable;

import api.configuration.AppConfiguration;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public final class AllureEnvironmentWriter {

  private static final String PROPERTIES_PATH = "build/allure-results/environment.properties";

  public static void writeEnvironment() {

    try (FileOutputStream fos = new FileOutputStream(PROPERTIES_PATH);) {
      Properties props = new Properties();

      ofNullable(getProperty("env")).ifPresent(s -> props.setProperty("Environment:", s));
      props.setProperty("Base url:", AppConfiguration.getEnvironment().getUrl());

      props.store(fos, "Writing properties to output stream");

    } catch (IOException e) {
      // TODO: add meaningful exception
    }
  }
}
