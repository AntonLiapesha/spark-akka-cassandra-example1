import sbt.Keys._
import sbt._

trait LibsBuild extends Build {
  self: BuildHelper =>


  lazy val Core = {
    generalProject("core-lib", "libs")
  }


  //**************************************************
  // LIBS AGGREGATE PROJECT
  //**************************************************
  lazy val LibsProject = {
    Project(id = "libs", base = file("libs"))
      .settings(generalSettings: _*)
      .settings(generalDeps: _*)
      .aggregate(allLibsProjectsRefs: _*)
      .dependsOn(Core)
  }
  private lazy val allLibsProjects = Seq(Core)
  private lazy val allLibsProjectsRefs = allLibsProjects map Project.projectToRef
}
