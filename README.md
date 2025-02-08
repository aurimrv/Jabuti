# JABUTI 1.0.4

Registering .jar maven packahe

```
mvn install:install-file \
  -Dfile=lib/capi-1.0.jar \
  -DgroupId=com.graphbuilder \
  -DartifactId=capi \
  -Dversion=1.0 \
  -Dpackaging=jar
```

Building
--------

The easiest way to do it is using Maven, the platform-independent Java-based build tool, by Apache Foundation. You can get it from https://maven.apache.org.

To build Jabuti, at the prompt type:

```
$ mvn package
```

This will build the software within the directory build/jar.

The command 'mvn clean' cleans up all the generated files.

Running
-------

You can run Jabuti by typing:

```
$ java -jar target/JaBUTi/jabuti-1.0.4-full.jar
```

Other ways to run Jabuti
------------------------

To execute a test case

```
$ java -cp target/JaBUTi/jabuti-1.0.4-full.jar br.jabuti.probe.ProberLoader -p <project name> <class to execute> [execution parameters]
```

To instrument and store it instrumented

```
$ java -cp target/JaBUTi/jabuti-1.0.4-full.jar br.jabuti.probe.ProberInstrum -o <file.jar> -p <project name> <class to execute>
```
Once instrumented, to execute a test case

```
$ java -cp <file.jar> <class to execute> [execution parameters]
```