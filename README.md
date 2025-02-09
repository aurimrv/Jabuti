# JABUTI 2.0.0

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
$ java -jar target/jabuti-2.0.0-full.jar
```

Other ways to run Jabuti
------------------------

To execute a test case

```
$ java -cp target/jabuti-2.0.0-full.jar br.jabuti.probe.ProberLoader -p <project name> <class to execute> [execution parameters]
```

To instrument and store it instrumented

```
$ java -cp target/jabuti-2.0.0-full.jar br.jabuti.probe.ProberInstrum -o <file.jar> -p <project name> <class to execute>
```
Once instrumented, to execute a test case use the command below. Please, add `-noverify` to disable `.class` verification.

```
$ java -noverify -cp <file.jar> <class to execute> [execution parameters]
```

Importing JUnit test cases
--------------------------

After generated the instrumented jar file of the application under testing, we may run the `JUnitJabutiCore` to execute the JUnit test set and collect coverage information:

To collecti trace infomation, parameter `-trace`, as in the example below, need to be defined.

```
java -noverify -cp jabuti-2.0.0-full-jar-with-dependencies.jar br.jabuti.junitexec.JUnitJabutiCore -trace IDENT.trc -cp IDENT_instr.jar:target/test-classes -tcClass identifier.ValidateIdentifierTest
```

Withou `-trace`, the runner only collects test case execution status:

```
java -noverify -cp jabuti-2.0.0-full-jar-with-dependencies.jar br.jabuti.junitexec.JUnitJabutiCore -cp IDENT_instr.jar:target/test-classes -tcClass identifier.ValidateIdentifierTest
```

It is also possible to specify the names of individual test cases to collect the trace only for these tests, as shown in the following example:

```
java -noverify -cp jabuti-2.0.0-full-jar-with-dependencies.jar br.jabuti.junitexec.JUnitJabutiCore -trace IDENT.trc -cp IDENT_instr.jar:target/test-classes -tcClass identifier.ValidateIdentifierTest validateValidTest validateInvalidTest01
```


The output will display something like this:

```
Collecting mode
TC Name: validateValidTest STATUS: S
TC Name: validateInvalidTest05 STATUS: E
TC Name: identifierMain STATUS: I
TC Name: validateInvalidTest01 STATUS: S
TC Name: validateInvalidTest02 STATUS: S
TC Name: validateInvalidTest03 STATUS: S
TC Name: validateInvalidTest04 STATUS: S
Instrumenting mode
JUnit/JaBUTi Integrator: Instrumentor Mode
Trace file: IDENT.trc
T....T
Trace collection finished
```

The `T....T` indicates that the trace was collected for the first and last tests, but not for the others.