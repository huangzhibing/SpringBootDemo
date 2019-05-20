package com.example.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class BossMain {
    private static List<String[]> LL1List = new ArrayList<>();
    private static List<String> sentences = new ArrayList<>();

    public static void readFile(String filePath){
        try{
            FileInputStream file = new FileInputStream(filePath);
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = null;
            int length = 1;
            while((line = bufferedReader.readLine())!=null){
                if(length%2 == 1){
                    String[] arr = line.split(" ");
                    LL1List.add(arr);
                }else {
                    sentences.add(line);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("文件名有误！");
        }
    }

    public static void gramAnaly(Map<String,Map<String,String>> map,String sentence,String tou){
        Stack<String> stringStack = new Stack<String>();
        stringStack.push("#");
        stringStack.push(tou);
        sentence = sentence + "#";

        for(int i = 0; i<sentence.length(); i++) {
            while (true) {
                if(stringStack.peek().equals(sentence.charAt(i)) && sentence.charAt(i) == '#'){ //匹配的跳出情况
                    System.out.println("句子："+sentence+"符合语法规则");
                }
                if(stringStack.peek().equals("#") && sentence.charAt(i) != '#'){
                    System.out.println("句子："+sentence+"不符合语法规则");
                }
                if(stringStack.peek().equals(String.valueOf(sentence.charAt(i)))){
                    stringStack.pop();
                    break;
                }else {
                    String value = map.get(stringStack.pop()).get(String.valueOf(sentence.charAt(i)));
                    if(value == "kong"){        //这里要改一下
                        continue;
                    }else {
                        char[] chars = value.toCharArray();
                        for (int j = 0; j < chars.length; j++) {
                            stringStack.push(String.valueOf(chars[j]));
                        }
                    }
                }

            }
        }

    }
}
