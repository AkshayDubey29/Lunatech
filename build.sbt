import play.sbt.PlayImport._
import play.sbt.routes.RoutesKeys._


name         := """spark-play"""
organization := "ch.alexmass"
version      := "0.0.1"
scalaVersion := Version.scala
val sparkVersion = "2.0.0"



lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaSource in Compile <<= baseDirectory / "src/scala"

libraryDependencies ++= Dependencies.sparkAkkaHadoop

dependencyOverrides ++= Set(
    "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.4",
  "org.apache.spark"      %% "spark-sql"        % "2.0.0",
  "com.databricks" %% "spark-csv" % "1.2.0"
)

releaseSettings

scalariformSettings

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

//routesGenerator := InjectedRoutesGenerator


fork in run := true