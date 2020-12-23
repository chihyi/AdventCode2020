package AdventCode2020;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class day19 {

    private HashMap<Integer, Rule> hashMap_rules = new HashMap<>();
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Inputs/day19_test.txt"));
        String line;
        while (!(line=bufferedReader.readLine()).equals("")){
            System.out.println(line);
            String[] split = line.split(": ");
            int rule_number = Integer.valueOf(split[0]);
            Rule rule = new Rule();
            if(split[1].contains("\"")){
                rule.type = 1; // literal
                rule.literal = split[1].charAt(1);
            }else{
                String[] split_rules = split[1].split(" \\| ");
                if(split_rules.length <= 1){
                    rule.type = 2; // and
                }else{
                    rule.type = 3;
                }
                for(int i=0;i<split_rules.length;i++){
                    String[] split_rule = split_rules[i].split(" ");
                    ArrayList<Integer> rules_and = new ArrayList<>();
                    for(int j=0;j<split_rule.length;j++){
                        rules_and.add(Integer.valueOf(split_rule[j]));
                    }
                    rule.rule.add(rules_and);
                }
            }
            hashMap_rules.put(rule_number, rule);
        }

        // change rules;
        /*Rule rule_8 = hashMap_rules.get(8);
        ArrayList<Integer> new_elements_8 = new ArrayList<>();
        new_elements_8.add(42);
        new_elements_8.add(8);
        rule_8.rule.add(new_elements_8);
        System.out.println(hashMap_rules.get(8).rule);

        Rule rule_11 = hashMap_rules.get(11);
        ArrayList<Integer> new_elements_11 = new ArrayList<>();
        new_elements_11.add(42);
        new_elements_11.add(11);
        new_elements_11.add(31);
        rule_11.rule.add(new_elements_11);
        System.out.println(hashMap_rules.get(11).rule);*/

        int sum_match = 0;
        while ((line=bufferedReader.readLine())!=null){
            System.out.println(line);
            Result_match result_match = match(line, 0);
            System.out.println("line_length:"+line.length());
            System.out.println("n_digit_used:"+result_match.n_digit_used);
            result_match.match = (result_match.n_digit_used == line.length()) && result_match.match;
            System.out.println(result_match.match);
            if(result_match.match == true){
                sum_match++;
            }
        }
        System.out.println(sum_match);
    }

    private Result_match match(String input_string, int rule_number){
        Result_match result_match;

        Rule rule = hashMap_rules.get(rule_number);

        if(rule.type == 1){
            result_match = match_lit(input_string, rule.literal);
        }else if(rule.type == 2){
            result_match = match_and(input_string, rule.rule.get(0));
        }else{
            result_match = match_or(input_string, rule.rule);
        }

        System.out.println("match:" + result_match.match);
        return result_match;
    }

    private Result_match match_and(String input_string, ArrayList<Integer> rules){
        Result_match result_match = new Result_match();
        for(int i=0;i<rules.size();i++){
            Result_match result = match(input_string.substring(result_match.n_digit_used), rules.get(i));
            if(result.match == false){
                result_match.match = false;
                System.out.println("match_and:" + result_match.match);
                return result_match;
            }
            result_match.n_digit_used += result.n_digit_used;
        }
        result_match.match = true;
        System.out.println("match_and:" + result_match.match);
        return result_match;
    }

    private Result_match match_lit(String input_string, char literal){
        Result_match result_match = new Result_match();
        if(input_string.charAt(0) == literal){
            result_match.match = true;
            result_match.n_digit_used = 1;
        }else {
            result_match.match = false;
        }
        System.out.println("match_lit:" + result_match.match);
        return result_match;
    }

    private Result_match match_or(String input_string, ArrayList<ArrayList<Integer>> rules){
        Result_match result_match = new Result_match();
        for(int i=0;i<rules.size();i++){
            Result_match result = match_and(input_string, rules.get(i));
            if(result.match == true){
                result_match.match = true;
                result_match.n_digit_used += result.n_digit_used;
                return  result_match;
            }
        }
        System.out.println("match_or:" + result_match.match);
        return result_match;
    }

    private class Result_match{
        public boolean match = false;
        public int n_digit_used = 0;
    }


    private class Rule{
        public int type; // 1:literal, 2:and, 3:or
        public ArrayList<ArrayList<Integer>> rule = new ArrayList<>();
        public char literal;
    }
}
