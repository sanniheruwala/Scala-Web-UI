name := """play-scala-seed"""
organization := "PlayStarter"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.11.561" 
libraryDependencies += "org.apache.hadoop" % "hadoop-aws" % "2.7.3" exclude("org.slf4j", "slf4j-log4j12")

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "PlayStarter.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "PlayStarter.binders._"
