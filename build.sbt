name := """eimontaite-code-sample"""
organization := "eimontaite.aiste"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.3"

libraryDependencies += guice
libraryDependencies ++= Seq(
  "org.postgresql"   % "postgresql"                     % "42.2.16",
  "org.scalikejdbc" %% "scalikejdbc"                    % "3.5.0",
  "org.scalikejdbc" %% "scalikejdbc-config"             % "3.5.0",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer"   % "2.8.0-scalikejdbc-3.5",
  "org.scalactic"   %% "scalactic"                      % "3.2.0",
  "org.scalatest"   %% "scalatest"                      % "3.2.0"                   % "test")
