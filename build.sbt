name := """play-scala-neo4j"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"


resolvers ++= Seq(
  "anormcypher" at "http://repo.anormcypher.org/",
 "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  jdbc,
  cache,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "org.jscience" % "jscience" % "4.3.1",
  "com.typesafe.play" % "play-ws_2.11" % "2.4.6",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.3",
  "org.anormcypher" %% "anormcypher" % "0.7.1"
)

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )
