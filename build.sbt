ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.bovi-analytics"

ThisBuild / scalaVersion := "2.13.16"

// Enable documentation generation
Compile / doc / scalacOptions ++= Seq(
  "-doc-title", "Bovi-analytics",
  "-doc-version", "0.1",
  "-sourcepath", (baseDirectory in ThisBuild).value.getAbsolutePath,
  "-doc-source-url", "https://bovi-analytics.com/src/docs/€{FILE_PATH}.scala"
)

lazy val root = (project in file("."))
  .settings(
    name := "DairyCowDomainModel"
  )

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test