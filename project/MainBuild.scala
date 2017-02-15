
import sbt.Keys._
import sbt._


object MainBuild extends BuildHelper with LibsBuild with AppBuild {

  private lazy val projectName = "TestTaskApp"

  private lazy val specificSettings = Seq(
    name := projectName
  )

  private lazy val MainBuildSettings = generalSettings ++ specificSettings

  private lazy val MainBuildDeps = Seq() ++ generalDeps

  private lazy val allProjects = Seq(LibsProject, AppProject)
  private lazy val allProjectsRefs = allProjects map Project.projectToRef

  lazy val MainBuild = {
    Project(id = projectName, base = file("."))
      .configs(IntegrationTest)
      .settings(MainBuildSettings: _*)
      .settings(MainBuildDeps: _*)
      .aggregate(allProjectsRefs: _*)
      .dependsOn(LibsProject, AppProject)
  }
}