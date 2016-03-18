import com.typesafe.sbt.osgi.SbtOsgi._
import sbt._
import sbt.Keys._

object OSGi {
  val core = osgiSettings ++ Seq(
    OsgiKeys.importPackage := scalaVersion(scalaImport).value.toSeq ++ Seq("*"),
    OsgiKeys.exportPackage := Seq("!fs2.internal", "fs2.*"),
    OsgiKeys.privatePackage := Seq("fs2.internal")
  )
  def scalaImport(version: String): Option[String] =
    CrossVersion.partialVersion(version) map { case (major, minor) =>
      s"""scala.*;version="[$major.$minor,$major.${minor+1})""""
    }
}
