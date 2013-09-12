package scalaogjvm

import com.typesafe.config.{Config, ConfigFactory}

class Settings(config: Config) {
  val env = config.getString("env")
  val jdbcPassword = config.getString("jdbc.password")
  val jdbcUser = config.getString("jdbc.username")
  val jdbcUrl = config.getString("jdbc.url")
  val jdbcTimeout = config.getMilliseconds("jdbc.timeout")
  val debug = config.getBoolean("debug")
  val fileSize = config.getBytes("file-size")
}

object Settings {
  def load() = new Settings(loadDefault.getConfig("my-app"))

  def load(env: String) = new Settings(loadEnv(env))

  def load(env: String, profiles: String *) = {
    val config = loadEnv(env)
    new Settings(profiles.foldLeft(config) {(conf, profile) =>
      if (conf.hasPath(profile))
        conf.getConfig(profile).withFallback(conf)
      else
        conf
    })
  }

  private def loadEnv(env: String) = {
    val default = loadDefault.getConfig("my-app")
    default.getConfig(env).withFallback(default)
  }

  private def loadDefault = loadSecret.withFallback(ConfigFactory.load())

  private def loadSecret = ConfigFactory.load("secret.conf")
}
