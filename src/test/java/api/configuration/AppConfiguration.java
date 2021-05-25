package api.configuration;

public final class AppConfiguration {

  private static Environment environment;
  public static Environment getEnvironment() {
    return environment;
  }

  public static void initialize() {
    setupEnvironment();
  }

  private static void setupEnvironment() {
    String envProperty = System.getProperty("env");

    if (envProperty == null) {
      envProperty = "default";
    }

    switch (envProperty) {
      case "prod":
        environment = Environment.PROD;
        break;
      case "test":
        environment = Environment.TEST;
        break;
      default:
        // TODO: Add default environment
        environment = Environment.PROD;
        break;
    }
  }
}
