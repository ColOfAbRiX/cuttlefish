import org.typelevel.scalacoptions.ScalacOptions

// Project Information

val scala3Version = "3.7.4"

val caseInsensitiveVersion = "1.4.0"
val catsEffectVersion      = "3.5.4"
val catsVersion            = "2.12.0"
val circeCoreVersion       = "0.14.10"
val fs2Version             = "3.9.3"
val h4sbtVersion           = "1.0.0"
val http4sClientVersion    = "0.23.24"
val log4catsVersion        = "2.7.0"
val pureconfigVersion      = "0.17.4"
val scalatestVersion       = "3.2.17"

// Global Settings

Global / run / fork              := true
Global / onChangedBuildSource    := ReloadOnSourceChanges
Global / tpolecatExcludeOptions ++= Set(ScalacOptions.warnUnusedLocals)
Test / tpolecatScalacOptions     := Set.empty

lazy val root =
  project
    .in(file("."))
    .settings(
      name          := "cuttlefish",
      version       := "1.0.0",
      description   := "A functional, type-safe client for the Octopus Energy API",
      organization  := "com.colofabrix.scala",
      scalaVersion  := scala3Version,
      scalacOptions += "-preview",
      libraryDependencies ++= List(
        "co.fs2"                %% "fs2-io"              % fs2Version,
        "com.colofabrix.scala"  %% "h4sbl"               % h4sbtVersion,
        "com.github.pureconfig" %% "pureconfig-core"     % pureconfigVersion,
        "io.circe"              %% "circe-core"          % circeCoreVersion,
        "io.circe"              %% "circe-parser"        % circeCoreVersion % Test,
        "org.http4s"            %% "http4s-circe"        % http4sClientVersion,
        "org.http4s"            %% "http4s-client"       % http4sClientVersion,
        "org.http4s"            %% "http4s-core"         % http4sClientVersion,
        "org.http4s"            %% "http4s-ember-client" % http4sClientVersion,
        "org.scalatest"         %% "scalatest"           % scalatestVersion % Test,
        "org.typelevel"         %% "case-insensitive"    % caseInsensitiveVersion,
        "org.typelevel"         %% "cats-core"           % catsVersion,
        "org.typelevel"         %% "cats-effect-kernel"  % catsEffectVersion,
        "org.typelevel"         %% "cats-effect-std"     % catsEffectVersion,
        "org.typelevel"         %% "cats-effect"         % catsEffectVersion,
        "org.typelevel"         %% "log4cats-core"       % log4catsVersion,
        "org.typelevel"         %% "log4cats-slf4j"      % log4catsVersion,
      ),
      semanticdbEnabled := true,
      semanticdbVersion := scalafixSemanticdb.revision,
    )
    .settings(publishSettings)

// Publishing Settings

lazy val publishSettings =
  Seq(
    homepage             := Some(url("https://github.com/ColOfAbRiX/cuttlefish")),
    startYear            := Some(2025),
    organizationName     := "ColOfAbRiX",
    organizationHomepage := Some(url("https://github.com/ColOfAbRiX")),
    licenses             := Seq("MIT" -> url("https://opensource.org/licenses/MIT")),
    scmInfo := Some(
      ScmInfo(
        url("https://github.com/ColOfAbRiX/cuttlefish"),
        "scm:git@github.com:ColOfAbRiX/cuttlefish.git",
      ),
    ),
    developers := List(
      Developer(
        "ColOfAbRiX",
        "Fabrizio Colonna",
        "colofabrix@tin.it",
        url("https://github.com/ColOfAbRiX"),
      ),
    ),
    pomIncludeRepository := { _ => false },
    publishMavenStyle    := true,
    publishTo := {
      if (isSnapshot.value)
        Some(Resolver.sonatypeCentralSnapshots)
      else
        localStaging.value
    },

    // Scaladoc settings
    Compile / doc / scalacOptions ++= Seq(
      "-doc-title",
      "Cuttlefish API Documentation",
      "-doc-version",
      version.value,
      "-encoding",
      "UTF-8",
    ),
  )
