package AdventCode2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class day10 {

    private ArrayList<Integer> inputs = new ArrayList<>();

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Inputs/day10.txt"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            inputs.add(Integer.valueOf(line));
        }

        Collections.sort(inputs);
        inputs.add(0,0);
        inputs.add(inputs.get(inputs.size()-1)+3);
        System.out.println(inputs);

        // Part 1 solution
        /*int current_jolt = 0;
        int sum_three = 0;
        int sum_one = 0;

        for (int i = 0; i < inputs.size(); i++) {

            if (inputs.get(i) - current_jolt == 1) {
                sum_one++;
                current_jolt = inputs.get(i);
            } else if (inputs.get(i) - current_jolt == 3) {
                sum_three++;
                current_jolt = inputs.get(i);
            } else {
                System.out.println("error");
            }


        }
        System.out.println(sum_one);
        System.out.println(sum_three + 1);
        System.out.println(sum_one * (sum_three + 1));*/

        // Part 2 solution
        Long num_arrangements = Long.valueOf(1);
        int current_jolt = 0;

        int num_continuous_ones = 0;
        for(int i=0;i<inputs.size();i++) {
            if (inputs.get(i) - current_jolt == 1) {
                num_continuous_ones++;
                current_jolt++;
                continue;
            } else if (inputs.get(i) - current_jolt == 3) {
                current_jolt = current_jolt + 3;
                if (num_continuous_ones > 1) {
                    int num_arrangements_inner = 0;
                    ArrayList<Integer> inputs_inner = new ArrayList<>();

                    for(int j=num_continuous_ones;j>1;j--){
                        inputs_inner.add(inputs.get(i-j));
                    }
                    System.out.println(inputs_inner);

                    int start_jolt = inputs_inner.get(0) - 1;
                    int end_jolt = inputs_inner.get(inputs_inner.size()-1) + 1;
                    for(int k=0;k<(1<<inputs_inner.size());k++){
                        ArrayList<Integer> subset = new ArrayList<>();
                        subset.add(start_jolt);

                        for(int l=0;l<inputs_inner.size();l++){
                            if((k & (1 << l)) > 0){
                                subset.add(inputs_inner.get(l));
                            }
                        }
                        subset.add(end_jolt);
                        //System.out.println(subset);

                        for(int i_subset = 1;i_subset<subset.size();i_subset++){
                            if(subset.get(i_subset) - subset.get(i_subset-1) > 3){
                                break;
                            }
                            if(i_subset == subset.size()-1){
                                num_arrangements_inner++;
                            }
                        }

                    }
                    if(num_arrangements_inner > 0){
                        num_arrangements = num_arrangements * num_arrangements_inner;
                        System.out.println(num_arrangements_inner);
                    }

                }
                num_continuous_ones = 0;
            }
        }

        System.out.println(num_arrangements);








      // wrong
      /*
        Long num_arrangements = Long.valueOf(1);
        int current_jolt = 0;
        ArrayList<Integer> diff = new ArrayList<>();
        for(int i=0;i<inputs.size();i++){
            diff.add(inputs.get(i) - current_jolt);
            current_jolt = inputs.get(i);
        }

        System.out.println(diff);

        int num_continuous_ones = 0;
        for(int i=0; i<diff.size();i++) {
            if (diff.get(i) == 1) {
                num_continuous_ones++;
                continue;
            }

            if (num_continuous_ones > 0) {
                int num_arrangements_inner = 1;
                for (int j = 0; j < (1 << num_continuous_ones); j++) {

                    String bin = String.format("%4s", Integer.toBinaryString(j)).replace(' ', '0');
                    System.out.println(bin);
                    int diff_jolt = 1;

                    //System.out.println(bin.length());
                    // check arrangement valid
                    for (int k = 0; k < num_continuous_ones; k++) {
                        if (bin.charAt(k) == '0') {
                            //System.out.println("bin is 0");
                            diff_jolt++;
                            if (diff_jolt > 3) {
                                System.out.println("invalid");
                                break;
                            }
                        } else if (bin.charAt(k) == '1') {
                            //System.out.println("bin is 1");
                            diff_jolt = 1;
                        }
                        if (k == bin.length() - 1) {
                            System.out.println("valid");
                            num_arrangements_inner++;
                        }
                    }
                }
                System.out.println(num_arrangements_inner);
                num_continuous_ones = 0;

                num_arrangements = num_arrangements_inner * num_arrangements;
                System.out.println(num_arrangements);
            }
        }*/

        //System.out.println(num_arrangements);

    }
}
