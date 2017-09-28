// ライブラリ群
val scalactic = "org.scalactic" %% "scalactic" % "3.0.1"
// テストのために使う ScalaTest ライブラリ
val scalatest = "org.scalatest" %% "scalatest" % "3.0.1" % "test"
// データ生成テストのために使う ScalaCheck ライブラリ
val scalacheck = "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
// 並行処理のための Akka Actor ライブラリ
val akka_actor = "com.typesafe.akka" % "akka-actor_2.12" % "2.5.4"

lazy val commonSettings = Seq(
  // scalac に与えるオプション
  scalacOptions := Seq("-feature", "-unchecked", "-deprecation")
)

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
    name := "lx01",             // プロジェクトの名称
    scalaVersion := "2.12.3",   // コンパイルに使う scalac のバージョン
    version := "1.0",           // プロジェクトのバージョン番号

    // プロジェクトで使う非標準 Scala ライブラリ
    libraryDependencies ++= Seq(scalactic, scalatest, scalacheck, akka_actor),

    fork in (Test, run) := true,
    connectInput := true,
    scalaSource in Compile := baseDirectory.value / "src",  // ソースコードを非標準の場所に設定
    scalaSource in Test    := baseDirectory.value / "test",

    // コンパイル結果を非標準の場所に設定
    // この設定はコンパイルの副産物がDropbox等のクラウドストレージに保存されることを
    // 避けるためのものです。これによりクラウドストレージとの同期時間が短縮されます。
    target := Path.userHome / "tmp" / "sbt" / "prg1b" / name.value
  )
