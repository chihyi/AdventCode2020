package AdventCode2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class day09 {

    private ArrayList<Long> inputs = new ArrayList<>();
    private int preamble_size = 25;
    private int goal_number = 1124361034;
    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/Inputs/day09.txt"));
        String line;
        while((line=bufferedReader.readLine())!=null){
            inputs.add(Long.valueOf(line));
        }

        // Part 1 solution
/*        boolean[] valid = new boolean[inputs.size()];
        for(int i=0;i<valid.length;i++){
            valid[i] = false;
        }

        for(int index_tested = preamble_size; index_tested < inputs.size();index_tested++){
           test_sum: for(int i=index_tested-preamble_size;i<index_tested-1;i++){
                for(int j=i+1;j<index_tested;j++){
                    if(inputs.get(i) + inputs.get(j)==inputs.get(index_tested)){
                        valid[index_tested] = true;
                        break test_sum;
                    }
                }
            }
            if(valid[index_tested] == false){
                System.out.println(inputs.get(index_tested));
                break;
            }
        }*/

        // Part 2 solution
        int result = 0;
        for(int i_start = 0;i_start<inputs.size()-1;i_start++){
            for(int i_end = i_start+1;i_end<inputs.size();i_end++){
                int sum = 0;
                for(int i=i_start;i<=i_end;i++){
                    sum += inputs.get(i);
                }
                if(sum == goal_number){
                    Long[] goal_sequence = new Long[i_end - i_start + 1];
                    for(int i=0;i<goal_sequence.length;i++){
                        goal_sequence[i] = inputs.get(i_start + i);
                    }
                    Arrays.sort(goal_sequence);
                    System.out.println(goal_sequence[0]);
                    System.out.println(goal_sequence[goal_sequence.length-1]);
                    System.out.println(goal_sequence[0] + goal_sequence[goal_sequence.length-1]);
                    break;
                }
            }
        }

    }
}
