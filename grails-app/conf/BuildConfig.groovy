/**
 * This module defines the build dependencies for the plugin to support such items as the OSCache functionality, etc.
 */
grails.project.work.dir = "target"
grails.project.dependency.resolution =
  {
    inherits "global" // inherit Grails' default dependencies
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories
    {
      grailsCentral()
      mavenLocal()
      mavenCentral()
    }
    dependencies
    {
      // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
      // This allows the plugin to compile.  If the user wants to use the OSCache multi tenant capability they will
      // need to add the jars to their runtime configuration for the application.
      compile "commons-dbcp:commons-dbcp:1.4"
    }
    plugins
    {
      build(":release:3.0.1",
              ":rest-client-builder:1.0.3") {
        export = false
      }

      build ":tomcat:7.0.47"

      compile ":falcone-util:1.2-DEV"
      compile ":hibernate:3.6.10.6"
    }
  }
