lazy val root = (project in file("."))
  .settings(
    name := "Compiler Help",
    scalaVersion := "2.12.4",
    libraryDependencies ++= Seq(
      "eu.timepit" %% "refined" % "0.8.4"
    ),
    scalacOptions ++= Seq(
      "-deprecation",
      "-unchecked",
      "-Xfuture",
      "-Xlint:-unused",
      "-Ywarn-unused:imports,privates,locals"
    )
  )
