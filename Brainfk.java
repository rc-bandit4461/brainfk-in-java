// package com.brainfk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

class Item{
    protected char character;

}
class OpenBracketItem extends Item{
    public int index;
}
class Interpreter{
    private Scanner sc = new Scanner(System.in);
    private Stack<Item> arith = new Stack<>();
    private String result = "";
    private List<Integer> charList = new ArrayList<>(Arrays.asList(0));
    private int ptr = 0;
    public int getPtr(){
        return ptr;
    }
    public void setPtr(int ptr){
        this.ptr = ptr;
    }
    
    public List<Integer> getCharList() {
        return charList;
    }

    public void setArith(List<Integer> charList) {
        this.charList = charList;
    }
 
    public String fromFile(String location)
            throws Exception {
        File file = new File(location);
        StringBuffer buf = new StringBuffer();
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            char[] cbuf = new char[(int) file.length()];
            br.read(cbuf);
            buf.append(cbuf);
            br.close();
        } finally {
            if (fr != null) {
                fr.close();
            }
        }
        return fromString(buf.toString());
    }
    public String fromString(String code) throws Exception{
        int stringLength = code.length();
        for (int i = 0; i < stringLength; i++ ) {
            if(i>=code.length()) break;
            char currentChar = code.charAt(i);
            switch (currentChar) {
                    case '+':
                        charList.set(ptr, (charList.get(ptr) + 1)%128);
                        break;
                    
                    case '-':
                        if(charList.get(ptr) == 0) 
                            charList.set(ptr, 127);
                        else
                            charList.set(ptr, charList.get(ptr) -1);
                        break;
                    
                    case '<':
                        if(ptr == 0){
                            throw new IndexOutOfBoundsException("Index out of bound homie");
                        }
                        ptr--;
                        break;
                    
                    case '>':
                        ptr++;
                        if(charList.size() <= ptr) charList.add(0);
                        break;
                    
                    case '[':
                        OpenBracketItem loopItem = new OpenBracketItem();
                        loopItem.index = i;
                        loopItem.character = code.charAt(i);
                        arith.push(loopItem);
                        break;
                    case ']':
                        if(charList.get(ptr) == 0){
                           Item item = arith.pop();
                        }else{
                            OpenBracketItem item = (OpenBracketItem) arith.peek();
                            i = item.index;
                        }
                        
                        break;
                    case '.':
                        result = result.concat("" + ((char) charList.get(ptr).intValue()));
                    break;
                    case ';':
                        charList.set(ptr, sc.nextInt() % 128);
                    break;
                default: 
                    break;
            }
        }
        if(arith.size() != 0) throw new Exception("Not valid Expression");
        return result;
    }
}
public class Brainfk{
    public static Interpreter interpreter = new Interpreter();
    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("Invalid argsuments");
            return;
        }
        try{

            if (args[0].equalsIgnoreCase("-f")) {
                String result = interpreter.fromFile(args[1]);
                System.out.println(result);
            }
            else if (args[0].equalsIgnoreCase("-s")) {
                String result = interpreter.fromString(args[1]);
                System.out.println(result);
            }
            else if (args[0].equalsIgnoreCase("-h")) {
                System.out.println("java Brainfk <option> <arg>\n. Options: \n -f: passing a file path in args.\n -s passing a string in args. \n -s: Shows help");
            }
            else {
                System.out.println("Invalid option");
                return;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }      
    }
}