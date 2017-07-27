/*
 * Copyright 2016 CGnal S.p.A.
 *
 */

organization := "com.cgnal.spark"

name := "spark-opentsdb"

version := "2.0"

val assemblyName = "spark-opentsdb-assembly"

scalaVersion := "2.11.1"

val sparkVersion = "2.0.0"
val hbaseVersion = "1.2.0-cdh5.7.0"
val hadoopVersion = "2.6.0"
val opentsdbVersion  = "2.3.0"



val sparkExcludes =
  (moduleId: ModuleID) => moduleId.
    exclude("org.apache.hadoop", "hadoop-client").
    exclude("org.slf4j", "slf4j-log4j12").
    exclude("org.apache.hadoop", "hadoop-yarn-client").
    exclude("org.apache.hadoop", "hadoop-yarn-api").
    exclude("org.apache.hadoop", "hadoop-yarn-common").
    exclude("org.apache.hadoop", "hadoop-yarn-server-common").
    exclude("org.apache.hadoop", "hadoop-yarn-server-web-proxy").
    exclude("org.apache.zookeeper", "zookeeper").
    exclude("commons-collections", "commons-collections").
    exclude("ch.qos.logback","logback-classic").
    exclude("commons-beanutils", "commons-beanutils")

val hbaseExcludes =
  (moduleID: ModuleID) => moduleID.
    exclude("org.slf4j", "slf4j-log4j12").
    exclude("org.apache.thrift", "thrift").
    exclude("org.jruby", "jruby-complete").
    exclude("org.slf4j", "slf4j-log4j12").
    exclude("org.mortbay.jetty", "jsp-2.1").
    exclude("org.mortbay.jetty", "jsp-api-2.1").
    exclude("org.mortbay.jetty", "servlet-api-2.5").
    exclude("com.sun.jersey", "jersey-core").
    exclude("com.sun.jersey", "jersey-json").
    exclude("com.sun.jersey", "jersey-server").
    exclude("org.mortbay.jetty", "jetty").
    exclude("org.mortbay.jetty", "jetty-util").
    exclude("tomcat", "jasper-runtime").
    exclude("tomcat", "jasper-compiler").
    exclude("org.jboss.netty", "netty").
    exclude("io.netty", "netty").
    exclude("com.google.guava", "guava").
    exclude("io.netty", "netty").
    exclude("commons-logging", "commons-logging").
    exclude("org.apache.xmlgraphics", "batik-ext").
    exclude("commons-collections", "commons-collections").
    exclude("xom", "xom").
    exclude("ch.qos.logback","logback-classic").
    exclude("commons-beanutils", "commons-beanutils")

val hadoopClientExcludes =
  (moduleId: ModuleID) => moduleId.
    exclude("org.slf4j", "slf4j-api").
    exclude("ch.qos.logback","logback-classic").
   // exclude("org.slf4j", "slf4j-log4j12").
    exclude("javax.servlet", "servlet-api")


libraryDependencies ++= Seq(
  sparkExcludes("org.apache.spark" % "spark-core_2.11" % sparkVersion % "provided"),
  sparkExcludes("org.apache.spark" % "spark-sql_2.11" % sparkVersion),
  sparkExcludes("org.apache.spark" % "spark-streaming_2.11" % sparkVersion % "provided"),
  sparkExcludes("org.apache.spark" % "spark-streaming-kafka-0-8_2.11" % "2.0.0" % "provided"),
  sparkExcludes("net.opentsdb" % "opentsdb" % opentsdbVersion),
  hbaseExcludes("org.hbase" % "asynchbase" % "1.8.0"),
  hbaseExcludes("org.apache.commons" % "commons-pool2" % "2.4.2"),
  hbaseExcludes("org.apache.hbase" % "hbase" % hbaseVersion),
  hbaseExcludes("org.apache.hbase" % "hbase-server" % hbaseVersion),
  hbaseExcludes("org.apache.hbase" % "hbase-server" % hbaseVersion classifier "tests"),
  hbaseExcludes("org.apache.hbase" % "hbase-client" % hbaseVersion),
  hbaseExcludes("org.apache.hbase" % "hbase-common" % hbaseVersion),
  hbaseExcludes("org.apache.hbase" % "hbase-common" % hbaseVersion classifier "tests"),
  hbaseExcludes("org.apache.hbase" % "hbase-annotations" % hbaseVersion),
  hbaseExcludes("org.apache.hbase" % "hbase-testing-util" % hbaseVersion % "test"),
  hadoopClientExcludes("org.apache.hadoop" % "hadoop-minicluster" % hadoopVersion),
  hadoopClientExcludes("org.apache.hadoop" % "hadoop-mapreduce-client-jobclient" % hadoopVersion classifier "tests"),
  hadoopClientExcludes("org.apache.hadoop" % "hadoop-hdfs" % hadoopVersion),
  hadoopClientExcludes("org.apache.hadoop" % "hadoop-hdfs" % hadoopVersion classifier "tests"),
  hadoopClientExcludes("org.apache.hbase" % "hbase-hadoop-compat" % hbaseVersion),
  hadoopClientExcludes("org.apache.hbase" % "hbase-hadoop-compat" % hbaseVersion classifier "tests"),
  hadoopClientExcludes("org.apache.hbase" % "hbase-hadoop2-compat" % hbaseVersion),
  hadoopClientExcludes("org.apache.hbase" % "hbase-hadoop2-compat" % hbaseVersion classifier "tests"),
  hadoopClientExcludes("org.apache.hadoop" % "hadoop-common" % hadoopVersion),
  hadoopClientExcludes("org.apache.hadoop" % "hadoop-common" % hadoopVersion classifier "tests"),
  hadoopClientExcludes("org.apache.hadoop" % "hadoop-annotations" % hadoopVersion)
  //"org.scalatest" %% "scalatest" % "2.2.6" % "test"
)



//libraryDependencies +=  "org.apache.hbase" % "hbase-common" % "1.0.3" excludeAll(ExclusionRule(organization = "javax.servlet", name="javax.servlet-api"), ExclusionRule(organization = "org.mortbay.jetty", name="jetty"), ExclusionRule(organization = "org.mortbay.jetty", name="servlet-api-2.5"), ExclusionRule(organization = "org.codehaus.jackson", name="jackson-core-asl"))
//libraryDependencies +=  "org.apache.hbase" % "hbase-server" % "1.0.3" excludeAll(ExclusionRule(organization = "javax.servlet", name="javax.servlet-api"), ExclusionRule(organization = "org.mortbay.jetty", name="jetty"), ExclusionRule(organization = "org.mortbay.jetty", name="servlet-api-2.5"), ExclusionRule(organization = "org.codehaus.jackson", name="jackson-core-asl"))
//libraryDependencies +=  "org.scalatest" % "scalatest_2.10" % "2.2.4" % "test"

assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
  case PathList("org", "apache", "jasper", xs @ _*)  => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}