# Brainf*ck Interpreter using java
## Compile
Compile with: `javac Brainfk.java`
## Run
run with: `java Brainfk` <option> <arg>
## Arguments
- use `-h` to display help. 
- use `-f` argument to pass a file in the second argument. Example: `java Brainfk "-f" "target.txt"`  
- use `-s` argument to pass a string directly in the second argument. 
#### Example: 

`java Brainfk "-s" ">++++++++[<+++++++++>-]<.>++++[<+++++++>-]<+.+++++++..+++.>>++++++[<+++++++>-]<++.------------.>++++++[<+++++++++>-]<+.<.+++.------.--------.>>>++++[<++++++++>-]<+."`

This would return a `"Hello World!"` message.