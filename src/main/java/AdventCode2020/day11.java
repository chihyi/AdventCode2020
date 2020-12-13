package AdventCode2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class day11 {

    private ArrayList<ArrayList<Character>> inputs = new ArrayList<>();
    private ArrayList<ArrayList<Character>> inputs_old = new ArrayList<>();


    public void solve() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Inputs/day11.txt"));
        String line;
        while ((line=bufferedReader.readLine())!=null){
            ArrayList<Character> input_line = new ArrayList<>();
            for(int i=0;i<line.length();i++){
                input_line.add(line.charAt(i));
            }
            inputs.add(input_line);
            inputs_old.add(new ArrayList<>(input_line));
        }

        boolean changed = true;
        while(changed) {
            changed = false;

            for (int row = 0; row < inputs_old.size(); row++)
                for (int col = 0; col < inputs_old.get(0).size(); col++) {
                    Character old_char = inputs_old.get(row).get(col);
                    Character new_char = update_element(old_char,row,col);
                    if(new_char != old_char){
                        changed = true;
                    }
                    inputs.get(row).set(col,new_char);
                    //System.out.println(old_char);
                    //System.out.println(new_char);
                }
            for (int row = 0; row < inputs_old.size(); row++)
                for (int col = 0; col < inputs_old.get(0).size(); col++){
                    char update_char = inputs.get(row).get(col);
                    inputs_old.get(row).set(col,update_char);
                }
        }
        int seats_occupied = 0;
        for (int row = 0; row < inputs.size(); row++)
            for (int col = 0; col < inputs.get(0).size(); col++){
                if(inputs.get(row).get(col) == '#'){
                    seats_occupied++;
                }
            }
        System.out.println(seats_occupied);
    }

    private Character update_element(Character old_char, int row, int col){
        Character new_char = old_char;

        // seat status. true is occupied, false is empty.
        Boolean[] status  = new Boolean[8];

        for(int k=0;k<status.length;k++){
            status[k]=false;
        }

        int i = 1;
        while(row-i >= 0 && col-i>=0){
            if(inputs_old.get(row-i).get(col-i) == '#'){
                status[0] = true;
                break;
            }else if(inputs_old.get(row-i).get(col-i) == 'L'){
                break;
            }
            i++;
        }

        i = 1;
        while (row-i >= 0){
            if(inputs_old.get(row-i).get(col) == '#'){
                status[1] = true;
                break;
            }else if(inputs_old.get(row-i).get(col) == 'L'){
                break;
            }
            i++;
        }

        i = 1;
        while (row-i >= 0 && col+i <= inputs_old.get(0).size()-1){
            if(inputs_old.get(row-i).get(col+i) == '#'){
                status[2] = true;
                break;
            }else if(inputs_old.get(row-i).get(col+i) == 'L') {
                break;
            }
            i++;
        }

        i = 1;
        while (col-i>=0){
            if(inputs_old.get(row).get(col-i) == '#'){
                status[3] = true;
                break;
            }else if(inputs_old.get(row).get(col-i) == 'L'){
                break;
            }
            i++;
        }

        i = 1;
        while (col+i<=inputs_old.get(0).size()-1){
            if(inputs_old.get(row).get(col+i) == '#'){
                status[4] = true;
            }else if(inputs_old.get(row).get(col+i) == 'L'){
                break;
            }
            i++;
        }

        i = 1;
        while (row+i <=inputs_old.size()-1 && col-i>=0){
            if(inputs_old.get(row+i).get(col-i) == '#'){
                status[5] = true;
                break;
            }else if(inputs_old.get(row+i).get(col-i) == 'L'){
                break;
            }
            i++;
        }

        i = 1;
        while (row+i <=inputs_old.size()-1){
            if(inputs_old.get(row+i).get(col) == '#'){
                status[6] = true;
                break;
            }else if(inputs_old.get(row+i).get(col) == 'L'){
                break;
            }
            i++;
        }

        i = 1;
        while (row+i <=inputs_old.size()-1 && col+i <= inputs_old.get(0).size()-1){
            if(inputs_old.get(row+i).get(col+i) == '#'){
                status[7] = true;
                break;
            }else if(inputs_old.get(row+i).get(col+i) == 'L'){
                break;
            }
            i++;
        }

        //System.out.println(Arrays.asList(status));
        if(old_char == 'L'){
            boolean should_change = false;
            for(int j=0;j<status.length;j++){
                should_change = should_change || status[j];
            }
            if(!should_change) {
                new_char = '#';
            }
        }

        if(old_char == '#'){
            boolean should_change = false;
            int num_occupied = 0;
            for(int j=0;j<status.length;j++){
                if(status[j] == true){
                    num_occupied++;
                }
            }
            if(num_occupied >= 5){
                should_change = true;
            }
            if(should_change) {
                new_char = 'L';
            }
        }
        return new_char;
    }
}
