package AdventCode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Stack;

public class day18 {

    private ArrayList<String> expressions = new ArrayList<>();
    private ArrayList<String> expressions_postFix = new ArrayList<>();

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Inputs/day18.txt"));
        String line;
        while ((line=bufferedReader.readLine())!=null){
            String[] split = line.split(" ");
            String expression = new String();

            for(int i=0;i<split.length;i++){
                if(split[i].length() == 1){
                    expression += split[i].charAt(0);
                }else{
                    String[] split_sub = split[i].split("");
                    for(int j=0;j<split_sub.length;j++){
                        expression += split_sub[j].charAt(0);
                    }
                }
            }
            expressions.add(expression);
        }

        for(int i=0;i<expressions.size();i++){
            String expression_postFix = infixToPostfix(expressions.get(i));
            expressions_postFix.add(expression_postFix);
        }

        BigInteger sum = BigInteger.valueOf(0);
        for(int i=0;i<expressions_postFix.size();i++){
            long result = evaluatePostFix(expressions_postFix.get(i));
            System.out.println(result);
            sum = sum.add(BigInteger.valueOf(result));
        }

        System.out.println(sum);




    }

    private long evaluatePostFix(String expression){
        Stack<Long> stack = new Stack<>();
        for(int i=0;i<expression.length();i++){
            Character c = expression.charAt(i);
            if(Character.isDigit(c)){
                long c_long = (long)(c -'0');
                stack.push(c_long);
            }else {
                long val1 = stack.pop();
                long val2 = stack.pop();

             switch (c){
                 case '+':
                     stack.push(val1+val2);
                     break;
                 case '*':
                     stack.push(val1*val2);
                     break;
             }
            }
        }
        return stack.pop();
    }

    private int priority(char ch){

        switch (ch) {
            case '+':
                return 2;
            case '*':
                return 1;
        }
        return -1;
    }

    private String infixToPostfix(String expression){
        String result = new String("");
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<expression.length();i++){
            Character c = expression.charAt(i);
            if(Character.isDigit(c)){
                result += c;
            }else if(c == '('){
                stack.push(c);
            }else if(c == ')'){
                while (!stack.isEmpty() && stack.peek()!='('){
                    result += stack.pop();
                }
                stack.pop();
            }else{
                if(!stack.isEmpty() && priority(c) <= priority(stack.peek())){
                    result += stack.pop();
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()){
            result += stack.pop();
        }

        return result;
    }
}
