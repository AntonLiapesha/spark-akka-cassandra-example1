
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import sbt.Keys._
import sbt._
import sbtassembly.AssemblyPlugin
import sbtassembly.AssemblyPlugin.autoImport._

trait AppBuild extends Build {
  self: BuildHelper with LibsBuild =>

  //**************************************************
  // SPARK PROJECT
  //**************************************************


  lazy val sparkSettings = generalSettings ++ Seq(
    assemblyJarName := "testTask.jar",
    mainClass := Some("com.aliapesha.testtask.spark.Main")
  )

  lazy val sparkDeps = libraryDependencies ++= Dependencies.sparkDeps

  lazy val SparkApp = {
    akkaProject("spark-app", "apps")
      .settings(sparkSettings: _*)
      .settings(sparkDeps: _*)
      .dependsOn(Core)
      .enablePlugins(AssemblyPlugin)
      .enablePlugins(JavaAppPackaging)
  }


  //**************************************************
  // AKKA PROJECT
  //**************************************************

  lazy val akkaSettings = generalSettings ++ Seq(
  )

  lazy val AkkaApp = {
    akkaProject("akka-app", "apps")
      .settings(akkaSettings: _*)
      .settings(generalDeps: _*)
      .dependsOn(Core)
      .enablePlugins(JavaAppPackaging)
  }

  //**************************************************
  // APPLICATION AGGREGATE PROJECT
  //**************************************************

  private lazy val allAppProjects = Seq(AkkaApp, SparkApp)
  private lazy val allAppProjectsRefs = allAppProjects map Project.projectToRef

  lazy val AppProject = {
    Project(id = "apps", base = file("apps"))
      .configs(IntegrationTest)
      .settings(generalSettings: _*)
      .aggregate(allAppProjectsRefs: _*)
      .dependsOn(AkkaApp, SparkApp)
  }
}