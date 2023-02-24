# Graph Package

This package generates the def-use graph representation of a Java class for further instrumentation. It parses the bytecode of each class and converts it to its graph representation.

## How to Run

Among all the classes of this package, `ClassSummary.java` provides a command line interface to parse and generate graphs for some class.

Considering the Vending Machine example, available at `Example/vending` direcory. 

```
$ cd cd Example/vending
$ mvn compile test-compile

```

After build the project, the command below provides a simple documentation of `ClassSummary` application:

```
$ java -cp ../../target/JaBUTi/jabuti-1.0.4-full.jar br.jabuti.graph.ClassSummary
```

This is the application output:

```
Java Bytecode ClassSummary

USAGE:
java graph.ClassSummary [options] -i <class files> | -jar <compressed file>

      -i <class files>        A list of classes to be instrumented these classes
                              should be reachable from the <main_class>.

      -jar <compressed file>  A compressed .jar or .zip file.

      [options] could be: [-h | -v | -du | -li | -do | -hi | -cfg | -all ]
      -h:                     This help.
      -v:                     ClassSummary version number.
      -call:                  Creates the CFG with call nodes.
      -du:                    Shows Variables Definitions and Uses of each Node, if any.
      -li:                    Shows Alive Variables of each Node, if any.
      -do:                    Shows Dominators and Inverse Dominators of each Node, if any.
      -ch:                    Shows Primary and Secundary Children of each Node, if any.
      -cfg:                   Generates a text file representation (dot format) with the CFG of each method.
      -all:                   Enables all options except -h and -v.
 If no option is specified a very simple summary w.r.t. 
 the given class files is printed out. 

Copyright (c) 2002
```

For instance, calling the application to provides the minimal information:

```
$ java -cp ../../target/JaBUTi/jabuti-1.0.4-full.jar br.jabuti.graph.ClassSummary -i target/classes/vending/VendingMachineInterface.class 
```

The application output is:

```
Class File: vending.VendingMachineInterface
		Number of Blocks: 2
		Number of Decisions: 0
		Number of Blocks: 22
		Number of Decisions: 7
```

## Building the graphs

Running the application to provides maximum information. In this case, once the class belongs to a package `vending` it is necessary to create a directory with this same name to output generated files:

```
$ mkdir vending

$ java -cp ../../target/JaBUTi/jabuti-1.0.4-full.jar br.jabuti.graph.ClassSummary -all -i target/classes/vending/VendingMachineInterface.class
```

In the standard output the application will print the information below:

```
Class File: vending.VendingMachineInterface
DOT FILE: vending/VendingMachineInterface_init.dot
		Number of Blocks: 2
			Block Details
				0 PC: 0 to 1
					Variable definitions:  L@0 PC: 0
					Children: 4
					Dominators: 0 
					Inverse Dominators: 4 0 
					Alive definitions: 
				4 PC: 4 to 4
					Dominators: 4 0 
					Inverse Dominators: 4 
					Alive definitions:   L@0 BK: 0
		Number of Decisions: 0
			Decision Details
DOT FILE: vending/VendingMachineInterface_main.dot
		Number of Blocks: 22
			Block Details
				0 PC: 0 to 28
					Variable definitions:  L@4 PC: 23  L@3 PC: 15  L@2 PC: 7  L@0 PC: 0
					Children: 31 52
					Dominators: 0 
					Inverse Dominators: 425 78 70 0 
					Alive definitions: 
				31 PC: 31 to 49
					Variable uses:  S@java.lang.System.in PC: 39
					Variable definitions:  L@1 PC: 48
					Children: 70
					Dominators: 0 31 
					Inverse Dominators: 425 78 70 31 
					Alive definitions:   L@3 BK: 0  L@4 BK: 0  L@0 BK: 0  L@2 BK: 0
				52 PC: 52 to 69
					Variable uses:  L@0[] PC: 62  L@0 PC: 60
					Variable definitions:  L@1 PC: 69
					Children: 70
					Dominators: 0 52 
					Inverse Dominators: 425 78 70 52 
					Alive definitions:   L@3 BK: 0  L@4 BK: 0  L@0 BK: 0  L@2 BK: 0
				70 PC: 70 to 75
					Variable uses:  S@java.lang.System.out PC: 70
					Children: 78
					Dominators: 70 0 
					Inverse Dominators: 425 78 70 
					Alive definitions:   L@1 BK: 31  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@2 BK: 0
				78 PC: 78 to 84
					Variable uses:  L@1 PC: 78
					Variable definitions:  L@2 PC: 83
					Children: 87 425
					Dominators: 78 70 0 
					Inverse Dominators: 425 78 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 244  L@6 BK: 187  L@6 BK: 111  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@7 BK: 123  L@2 BK: 0  L@6 BK: 123  L@8 BK: 306
				87 PC: 87 to 102
					Variable uses:  L@2 PC: 91
					Variable definitions:  L@5 PC: 95
					Children: 105 111
					Dominators: 78 70 0 87 
					Inverse Dominators: 425 78 111 87 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 244  L@6 BK: 187  L@6 BK: 111  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@7 BK: 123  L@6 BK: 123  L@8 BK: 306
				105 PC: 105 to 110
					Variable uses:  L@5 PC: 105
					Variable definitions:  L@3 PC: 110
					Children: 111
					Dominators: 78 70 0 87 105 
					Inverse Dominators: 425 78 111 105 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 244  L@6 BK: 187  L@6 BK: 111  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@7 BK: 123  L@6 BK: 123  L@8 BK: 306
				111 PC: 111 to 120
					Variable uses:  L@3 PC: 114
					Variable definitions:  L@6 PC: 112
					Children: 123 178
					Dominators: 78 70 111 0 87 
					Inverse Dominators: 425 78 111 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 244  L@6 BK: 187  L@6 BK: 111  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@7 BK: 123  L@6 BK: 123  L@8 BK: 306
				123 PC: 123 to 175
					Variable uses:  S@java.lang.System.out PC: 149  L@4 PC: 137  L@5 PC: 127
					Variable definitions:  L@6 PC: 147  L@7 PC: 135
					Children: 78
					Dominators: 78 123 70 111 0 87 
					Inverse Dominators: 425 78 123 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 111  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@7 BK: 123  L@8 BK: 306
				178 PC: 178 to 184
					Variable uses:  L@3 PC: 178
					Children: 187 221
					Dominators: 78 178 70 111 0 87 
					Inverse Dominators: 425 78 178 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 111  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@7 BK: 123  L@8 BK: 306
				187 PC: 187 to 196
					Variable uses:  L@4 PC: 187
					Variable definitions:  L@6 PC: 192
					Children: 199 210
					Dominators: 78 178 70 111 0 87 187 
					Inverse Dominators: 425 78 187 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 111  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@7 BK: 123  L@8 BK: 306
				199 PC: 199 to 207
					Variable uses:  S@java.lang.System.err PC: 199
					Children: 78
					Dominators: 78 178 70 199 111 0 87 187 
					Inverse Dominators: 425 78 199 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 187  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@7 BK: 123  L@8 BK: 306
				210 PC: 210 to 218
					Variable uses:  S@java.lang.System.out PC: 210
					Children: 78
					Dominators: 78 178 70 111 0 210 87 187 
					Inverse Dominators: 425 78 210 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 187  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@7 BK: 123  L@8 BK: 306
				221 PC: 221 to 227
					Variable uses:  L@3 PC: 221
					Children: 230 414
					Dominators: 78 178 70 111 0 221 87 
					Inverse Dominators: 425 78 221 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 111  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@7 BK: 123  L@8 BK: 306
				230 PC: 230 to 242
					Variable uses:  L@5 PC: 234
					Variable definitions:  L@7 PC: 242
					Children: 244
					Dominators: 78 178 230 70 111 0 221 87 
					Inverse Dominators: 425 78 230 244 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 111  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@7 BK: 123  L@8 BK: 306
				244 PC: 244 to 287
					Variable uses:  S@java.lang.System.out PC: 256  L@7 PC: 246  L@4 PC: 244
					Variable definitions:  L@6 PC: 254
					Children: 78
					Exception Children: 293 306 342 378
					Dominators: 78 178 230 70 111 0 221 87 244 
					Inverse Dominators: 425 78 244 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 111  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@8 BK: 306
				293 PC: 293 to 303
					Variable uses:  S@java.lang.System.out PC: 295
					Variable definitions:  L@8 PC: 293
					Children: 78
					Dominators: 78 178 230 70 111 0 221 293 87 244 
					Inverse Dominators: 425 78 293 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 244  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@8 BK: 306
				306 PC: 306 to 339
					Variable uses:  L@7 PC: 323  S@java.lang.System.out PC: 308
					Variable definitions:  L@8 PC: 306
					Children: 78
					Dominators: 78 178 230 70 111 0 221 87 244 306 
					Inverse Dominators: 425 78 306 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 244  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@8 BK: 306
				342 PC: 342 to 375
					Variable uses:  L@7 PC: 359  S@java.lang.System.out PC: 344
					Variable definitions:  L@8 PC: 342
					Children: 78
					Dominators: 78 178 230 70 111 0 221 87 244 342 
					Inverse Dominators: 425 78 342 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 244  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@8 BK: 306
				378 PC: 378 to 408
					Variable uses:  L@7 PC: 395  S@java.lang.System.out PC: 380
					Variable definitions:  L@8 PC: 378
					Children: 78
					Dominators: 78 178 230 70 378 111 0 221 87 244 
					Inverse Dominators: 425 78 378 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 244  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@8 BK: 306
				414 PC: 414 to 419
					Variable uses:  S@java.lang.System.out PC: 414
					Children: 78
					Dominators: 78 178 70 414 111 0 221 87 
					Inverse Dominators: 425 78 414 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 111  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@7 BK: 123  L@8 BK: 306
				425 PC: 425 to 433
					Variable uses:  S@java.lang.System.out PC: 425
					Dominators: 78 425 70 0 
					Inverse Dominators: 425 
					Alive definitions:   L@1 BK: 31  L@8 BK: 378  L@2 BK: 78  L@3 BK: 105  L@7 BK: 230  L@5 BK: 87  L@6 BK: 244  L@6 BK: 187  L@6 BK: 111  L@8 BK: 342  L@8 BK: 293  L@3 BK: 0  L@4 BK: 0  L@1 BK: 52  L@0 BK: 0  L@7 BK: 123  L@6 BK: 123  L@8 BK: 306
		Number of Decisions: 7
			Decision Details
				0 Start: 0 End: 28
				78 Start: 78 End: 84
				87 Start: 87 End: 102
				111 Start: 111 End: 120
				178 Start: 178 End: 184
				187 Start: 187 End: 196
				221 Start: 221 End: 227
```

And inside the vendig directory, application generates several files:

```
$ tree vending/
vending/
├── VendingMachineInterface_init_Dominator.dot
├── VendingMachineInterface_init.dot
├── VendingMachineInterface_init_IDominator.dot
├── VendingMachineInterface_init_MergedTree.dot
├── VendingMachineInterface_init_SuperBlock.dot
├── VendingMachineInterface_main_Dominator.dot
├── VendingMachineInterface_main.dot
├── VendingMachineInterface_main_IDominator.dot
├── VendingMachineInterface_main_MergedTree.dot
└── VendingMachineInterface_main_SuperBlock.dot
```

Basically, for each method of a given class, the application generates the def-use graph (`<class_name>_<method_name>.dot`), and the other graphs wchich allow us to build the Super Block graph (`<class_name>_<method_name>_SuperBlock.dot`). They are: Dominator graph  (`<class_name>_<method_name>_Dominator.dot`), Inverse Dominator graph (`<class_name>_<method_name>_IDominator.dot`), and Merged Tree graph (`<class_name>_<method_name>_MergedTree.dot`).

All this graphs are in GraphViz tool format. To transform them on images, ones may use the command below:

```
$ dot -Tpng some_file.dot -o some_file.png
```

To convert all these files to its corresponding images ones may run:

```
$ dot -Tpng VendingMachineInterface_main.dot -o VendingMachineInterface_main.png
```

Or using a `bash` for each looping:

```
$ cd vending
$ for dotF in $(ls *.dot); do   pngF="${dotF%%.*}.png";   dot -Tpng $dotF -o $pngF; done
```

Command above is an inline version of these commands:

```
for dotF in $(ls *.dot); do
  pngF="${dotF%%.*}.png"
  dot -Tpng $dotF -o $pngF
done
```