package AdventCode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.util.ArrayList;

public class day17 {

    private ArrayList<ArrayList<ArrayList<ArrayList<Boolean>>>> input = new ArrayList<>();
    private int n_cycles = 6;
    public void solve() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Inputs/day17_test.txt"));
        String line;
        ArrayList<ArrayList<ArrayList<Boolean>>> input_xyz = new ArrayList<>();
        ArrayList<ArrayList<Boolean>> input_xy = new ArrayList<>();
        while ((line=bufferedReader.readLine())!=null){
            ArrayList<Boolean> input_x = new ArrayList<>();
            String[] split = line.split("");
            for(int i=0;i<split.length;i++){
                if(split[i].equals(".")){
                    input_x.add(false);
                }else if(split[i].equals("#")){
                    input_x.add(true);
                }
            }
            input_xy.add(input_x);
        }
        input_xyz.add(input_xy);
        input.add(input_xyz);

        ArrayList<ArrayList<ArrayList<ArrayList<Boolean>>>> result = new ArrayList<>(input);;
        for(int i = 0; i<n_cycles;i++){
            result = cycle_calculation(result);
        }
        System.out.println(result.toString());

        int sum_true = 0;
        for(int i=0;i<result.size();i++) {
            for (int j = 0; j < result.get(0).size(); j++) {
                for (int k = 0; k < result.get(0).get(0).size(); k++) {
                    for(int w=0;w<result.get(0).get(0).get(0).size();w++){
                        if(result.get(i).get(j).get(k).get(w) == true){
                            sum_true++;
                        }
                    }
                }
            }
        }

        System.out.println(sum_true);


    }

    private ArrayList<ArrayList<ArrayList<ArrayList<Boolean>>>> cycle_calculation(ArrayList<ArrayList<ArrayList<ArrayList<Boolean>>>> input){

        ArrayList<ArrayList<ArrayList<ArrayList<Boolean>>>> input_padded = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<ArrayList<Boolean>>>> result = new ArrayList<>();

        for(int w=0;w<input.size() + 2;w++) {
            ArrayList<ArrayList<ArrayList<Boolean>>> input_padded_xyz = new ArrayList<>();
            ArrayList<ArrayList<ArrayList<Boolean>>> result_xyz = new ArrayList<>();
            for (int i = 0; i < input.get(0).size() + 2; i++) {
                ArrayList<ArrayList<Boolean>> input_padded_xy = new ArrayList<>();
                ArrayList<ArrayList<Boolean>> result_xy = new ArrayList<>();
                for (int j = 0; j < input.get(0).get(0).size() + 2; j++) {
                    ArrayList<Boolean> input_padded_x = new ArrayList<>();
                    ArrayList<Boolean> result_x = new ArrayList<>();
                    for (int k = 0; k < input.get(0).get(0).get(0).size() + 2; k++) {
                        input_padded_x.add(false);
                        result_x.add(false);
                    }
                    input_padded_xy.add(input_padded_x);
                    result_xy.add(result_x);
                }
                input_padded_xyz.add(input_padded_xy);
                result_xyz.add(result_xy);
            }
            input_padded.add(input_padded_xyz);
            result.add(result_xyz);
        }

        for(int i=0;i<input.size();i++){
            for(int j=0;j<input.get(0).size();j++){
                for(int k=0;k<input.get(0).get(0).size();k++){
                    for(int w=0;w<input.get(0).get(0).get(0).size();w++) {
                        input_padded.get(i + 1).get(j + 1).get(k + 1).set(w + 1, input.get(i).get(j).get(k).get(w));
                        result.get(i + 1).get(j + 1).get(k+1).set(w + 1, result.get(i).get(j).get(k).get(w));
                    }
                }
            }
        }

        for(int i=0;i<input_padded.size();i++){
            for(int j=0;j<input_padded.get(0).size();j++){
                for(int k=0;k<input_padded.get(0).get(0).size();k++) {
                    for (int w = 0; w < input_padded.get(0).get(0).get(0).size(); w++) {
                        int sum_true = 0;
                        for (int l = i - 1; l <= i + 1; l++) {
                            for (int m = j - 1; m <= j + 1; m++) {
                                for (int n = k - 1; n <= k + 1; n++) {
                                    for (int p = w - 1; p <= w + 1; p++) {
                                        if (l >= 0 && l < input_padded.size() && m >= 0 && m < input_padded.get(0).size()
                                                && n >= 0 && n < input_padded.get(0).get(0).size() && p >= 0 && p < input_padded.get(0).get(0).get(0).size()
                                                && !(l == i && m == j && n == k && p == w)) {
                                            if (input_padded.get(l).get(m).get(n).get(p) == true) {
                                                sum_true++;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (input_padded.get(i).get(j).get(k).get(w) == true) {
                            if (sum_true == 2 || sum_true == 3) {
                                result.get(i).get(j).get(k).set(w, true);
                            } else {
                                result.get(i).get(j).get(k).set(w, false);
                            }
                        }

                        if (input_padded.get(i).get(j).get(k).get(w) == false) {
                            if (sum_true == 3) {
                                result.get(i).get(j).get(k).set(w, true);
                            } else {
                                result.get(i).get(j).get(k).set(w, false);
                            }
                        }

                    }
                }
            }
        }

        int i_min = result.size()-1, j_min = result.get(0).size()-1;
        int k_min = result.get(0).get(0).size()-1, w_min = result.get(0).get(0).get(0).size()-1;
        int i_max = 0, j_max = 0, k_max = 0, w_max = 0;

        for(int i=0;i<result.size();i++) {
            for (int j = 0; j < result.get(0).size(); j++) {
                for (int k = 0; k < result.get(0).get(0).size(); k++) {
                    for (int w = 0; w < result.get(0).get(0).get(0).size(); w++) {
                        if (result.get(i).get(j).get(k).get(w) == true) {
                            if (i < i_min) {
                                i_min = i;
                            }
                            if (i > i_max) {
                                i_max = i;
                            }

                            if (j < j_min) {
                                j_min = j;
                            }
                            if (j > j_max) {
                                j_max = j;
                            }

                            if (k < k_min) {
                                k_min = k;
                            }
                            if (k > k_max) {
                                k_max = k;
                            }

                            if (w < w_min) {
                                w_min = w;
                            }
                            if (w > w_max) {
                                w_max = w;
                            }
                        }
                    }
                }
            }
        }

        ArrayList<ArrayList<ArrayList<ArrayList<Boolean>>>> result_cut = new ArrayList<>();

        for(int i=0;i<=i_max-i_min;i++){
            ArrayList<ArrayList<ArrayList<Boolean>>> result_cut_xyz = new ArrayList<>();
            for(int j=0;j<=j_max-j_min;j++){
                ArrayList<ArrayList<Boolean>> result_cut_xy = new ArrayList<>();
                for(int k=0;k<=k_max-k_min;k++){
                    ArrayList<Boolean> result_cut_x = new ArrayList<>();
                    for(int w=0; w<=w_max-w_min;w++){
                        result_cut_x.add(result.get(i+i_min).get(j+j_min).get(k+k_min).get(w+w_min));
                    }
                    result_cut_xy.add(result_cut_x);
                }
                result_cut_xyz.add(result_cut_xy);
            }
            result_cut.add(result_cut_xyz);
        }

        return result_cut;
    }
}
