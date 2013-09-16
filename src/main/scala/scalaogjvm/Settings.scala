package scalaogjvm

import com.typesafe.config.Config

class Settings(config: Config)

object Settings {
  def load() = ???

  def load(env: String) = ???

  def load(env: String, profiles: String *) = ???
}
