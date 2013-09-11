package scalaogjvm

import org.scalatest._
import com.typesafe.config.{ConfigException, Config, ConfigFactory}

class SettingsTest extends FlatSpec {
  "The settings object" should "load default my-app config from file" in {
    val settings = Settings.load()
    assert(settings.env === "dev")
    assert(settings.jdbcUrl === "jdb:hsql:mem")
  }

  it should "load secret properties from secret.conf" in {
    val settings = Settings.load()
    assert(settings.jdbcPassword === "secret-password")
  }

  it should "fail fast on missing config keys" in {
    intercept[ConfigException] {
      new Settings(ConfigFactory.empty())
    }
  }

  it should "override default values from environment specific sections" in {
    val settings = Settings.load("qa")
    assert(settings.env === "qa")
    assert(settings.jdbcUrl === "jdbc:enterprise:oracle")
  }

  it should "load user specific username and password from system properties" in {
    System.setProperty("my-app.jdbc.username", "frode")
    System.setProperty("my-app.jdbc.password", "db-123")
    ConfigFactory.invalidateCaches()
    val settings = Settings.load()
    assert(settings.jdbcUser === "frode")
    assert(settings.jdbcPassword === "db-123")
  }

  it should "load time units as milliseconds" in {
    val settings = Settings.load()
    assert(settings.jdbcTimeout === 30000)
  }

  it should "override settings using profiles" in {
    val settings = Settings.load("qa", "debugging", "slow-db")
    assert(settings.env === "qa")
    assert(settings.debug === true)
    assert(settings.jdbcTimeout === 5*60000)
  }
}