import sbt.Keys._
import sbt._

trait BuildHelper extends Build {

  lazy val sbtSettings = Seq(
    version := "0.0.1",
    compileOrder in Test := CompileOrder.Mixed,
    crossScalaVersions := Seq(Dependencies.scalaVersion),
    scalaVersion := Dependencies.scalaVersion,
    organization := "com.aliapesha",
    doc in Compile <<= target.map(_ / "none"),
    sources in(Compile, doc) := Seq.empty,
    scalacOptions ++= Seq("-feature", "-deprecation", "-unchecked", "-language:postfixOps", "-language:implicitConversions", "-language:reflectiveCalls"),
    ivyScala := ivyScala.value map {
      _.copy(overrideScalaVersion = true)
    },
    javacOptions in Compile ++= Seq("-source", "1.8", "-target", "1.8"),
    updateOptions := updateOptions.value.withCachedResolution(cachedResoluton = true),

    resolvers ++= Seq(
      Resolver.sonatypeRepo("snapshots"),
      Resolver.bintrayRepo("websudos", "oss-releases"),
      "central" at "http://repo1.maven.org/maven2",
      "burtsev" at "http://maven.burtsev.net/",
      "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
      "Oss repository" at "http://oss.sonatype.org/content/repositories/snapshots/",
      "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
      "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
      "Twitter Repository" at "http://maven.twttr.com"
    )
  )

  lazy val testSettings = Seq(
    parallelExecution in Test := false
  )

  lazy val itSettings = Defaults.itSettings ++ Seq(
    testOptions in Test += Tests.Argument(TestFrameworks.Specs2)
  )

  lazy val generalSettings = testSettings ++ sbtSettings ++ itSettings

  lazy val generalDeps = Seq(
    libraryDependencies ++=
      Dependencies.akkaDeps ++
        Dependencies.typesafeDeps ++
        Dependencies.logDeps ++
        Dependencies.testDeps ++
        Dependencies.cassandraDeps
  )


  def akkaProject(name: String, path: String) = {
    generalProject(name, path).settings(generalDeps: _*)
  }

  def generalProject(name: String, path: String) = {
    Project(id = name, base = file(s"$path/$name"))
      .configs(Test, IntegrationTest)
      .settings(generalSettings: _*)
  }
}