name := "CafeXProject"

version := "0.1"

scalaVersion in ThisBuild := "2.12.0"

scalacOptions in ThisBuild += "-deprecation"

libraryDependencies in ThisBuild ++= Seq(

  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"

)
