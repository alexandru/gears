import sbtcrossproject.CrossPlugin.autoImport.{CrossType, crossProject}
import scalanative.build._

ThisBuild / scalaVersion := "3.3.3"

lazy val root =
  crossProject(JVMPlatform, NativePlatform)
    .crossType(CrossType.Full)
    .in(file("."))
    .settings(
      Seq(
        name := "Gears",
        organization := "ch.epfl.lamp",
        version := "0.2.0-SNAPSHOT",
        testFrameworks += new TestFramework("munit.Framework")
      )
    )
    .jvmSettings(
      Seq(
        javaOptions += "--version 21",
        libraryDependencies += "org.scalameta" %% "munit" % "1.0.0-M10" % Test
      )
    )
    .nativeSettings(
      Seq(
        nativeConfig ~= { c =>
          c.withMultithreadingSupport(true)
        },
        libraryDependencies += "org.scalameta" %%% "munit" % "1.0.0-M10+17-0ca7e7e9-SNAPSHOT" % Test
      )
    )
