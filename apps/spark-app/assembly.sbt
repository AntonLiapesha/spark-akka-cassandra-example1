
assemblyMergeStrategy in assembly := {
  //  case x if x.endsWith(".class") => MergeStrategy.last
  //  case x if x.endsWith(".properties") => MergeStrategy.last
  //  case x if x.contains("/resources/") => MergeStrategy.last
  //  case x if x.contains(".txt") => MergeStrategy.last
  //  case x if x.startsWith("META-INF/mailcap") => MergeStrategy.last
  //  case x if x.startsWith("META-INF/mimetypes.default") => MergeStrategy.first
  //  case x if x.startsWith("META-INF/maven/org.slf4j/slf4j-api/pom.") => MergeStrategy.first
  //  case x =>
  //    val oldStrategy = (assemblyMergeStrategy in assembly).value
  //    if (oldStrategy == MergeStrategy.deduplicate)
  //      MergeStrategy.first
  //    else
  //      oldStrategy(x)
  case x if x.endsWith(".properties") => MergeStrategy.last
  case PathList("org", "aopalliance", xs@_*) => MergeStrategy.last
  case PathList("javax", "inject", xs@_*) => MergeStrategy.last
  case PathList("javax", "servlet", xs@_*) => MergeStrategy.last
  case PathList("javax", "activation", xs@_*) => MergeStrategy.last
  case PathList("org", "apache", xs@_*) => MergeStrategy.last
  case PathList("com", "google", xs@_*) => MergeStrategy.last
  case PathList("com", "esotericsoftware", xs@_*) => MergeStrategy.last
  case PathList("com", "codahale", xs@_*) => MergeStrategy.last
  case PathList("com", "yammer", xs@_*) => MergeStrategy.last
  case x if x.endsWith(".class") => MergeStrategy.last
  case "about.html" => MergeStrategy.rename
  case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
  case "META-INF/mailcap" => MergeStrategy.last
  case "META-INF/mimetypes.default" => MergeStrategy.last
  case "plugin.properties" => MergeStrategy.last
  case "log4j.properties" => MergeStrategy.last
  case x =>
    //    val oldStrategy = (assemblyMergeStrategy in assembly).value
    //    oldStrategy(x)
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    if (oldStrategy == MergeStrategy.deduplicate)
      MergeStrategy.first
    else
      oldStrategy(x)
}
