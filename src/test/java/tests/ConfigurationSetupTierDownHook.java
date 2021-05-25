package tests;

import api.configuration.AppConfiguration;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import utils.AllureEnvironmentWriter;

public final class ConfigurationSetupTierDownHook implements BeforeAllCallback, AfterAllCallback {

  @Override
  public void beforeAll(ExtensionContext context) {
    AppConfiguration.initialize();
  }

  @Override
  public void afterAll(ExtensionContext context) {
    AllureEnvironmentWriter.writeEnvironment();
  }
}
