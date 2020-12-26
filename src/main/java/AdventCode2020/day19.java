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
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Inputs/day19.txt"));
        String line;
        while (!(line = bufferedReader.readLine()).equals("")) {
            System.out.println(line);
            String[] split = line.split(": ");
            int rule_number = Integer.valueOf(split[0]);
            Rule rule = new Rule();
            if (split[1].contains("\"")) {
                rule.type = 1; // literal
                rule.literal = split[1].charAt(1);
            } else {
                String[] split_rules = split[1].split(" \\| ");
                if (split_rules.length <= 1) {
                    rule.type = 2; // and
                } else {
                    rule.type = 3;
                }
                for (int i = 0; i < split_rules.length; i++) {
                    String[] split_rule = split_rules[i].split(" ");
                    ArrayList<Integer> rules_and = new ArrayList<>();
                    for (int j = 0; j < split_rule.length; j++) {
                        rules_and.add(Integer.valueOf(split_rule[j]));
                    }
                    rule.rule.add(rules_and);
                }
            }
            hashMap_rules.put(rule_number, rule);
        }

        int sum_match = 0;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            ArrayList<Integer> rule_sequence = new ArrayList<>();
            rule_sequence.add(0);
            boolean result_match = match(line, rule_sequence);
            System.out.println(result_match);
            if (result_match == true) {
                sum_match++;
            }
        }
        System.out.println(sum_match);
    }

    static int q() {
        return 12;
    }

    private boolean match(String input_string, ArrayList<Integer> rule_sequence) {

        boolean result = false;
        if (input_string.isEmpty() && rule_sequence.size() == 0) {
            return true;
        } else if (input_string.isEmpty() ^ rule_sequence.size() == 0) {
            return false;
        }

        Rule rule = hashMap_rules.get(rule_sequence.get(0));

        if (rule.type == 1) {
            ArrayList<Integer> rule_sequence_new = new ArrayList<>(rule_sequence);
            if (rule.literal == input_string.charAt(0)) {
                rule_sequence_new.remove(0);
                //System.out.println("literal: " + input_string.substring(1) + ", " +rule_sequence_new.toString());
                return match(input_string.substring(1), rule_sequence_new);
            } else {
                return false;
            }
        } else if (rule.type == 2) {
            ArrayList<Integer> rule_sequence_new = new ArrayList<>(rule_sequence);
            rule_sequence_new.remove(0);
            int index_add = 0;
            for (int j = 0; j < rule.rule.get(0).size(); j++) {
                rule_sequence_new.add(index_add, rule.rule.get(0).get(j));
                index_add++;
            }
            //System.out.println("and: " + input_string + ", " +rule_sequence_new.toString());
            return match(input_string, rule_sequence_new);
        } else {
            for (int i = 0; i < rule.rule.size(); i++) {
                ArrayList<Integer> rule_sequence_new = new ArrayList<>(rule_sequence);
                rule_sequence_new.remove(0);
                int index_add = 0;
                for (int j = 0; j < rule.rule.get(i).size(); j++) {
                    rule_sequence_new.add(index_add, rule.rule.get(i).get(j));
                    index_add++;
                }
                //System.out.println("or: " + input_string + ", " + rule_sequence_new.toString());
                result = match(input_string, rule_sequence_new);
                if (result == true) {
                    return true;
                }
            }
            return false;
        }
    }




    private class Rule {
        public int type; // 1:literal, 2:and, 3:or
        public ArrayList<ArrayList<Integer>> rule = new ArrayList<>();
        public char literal;
    }
}
