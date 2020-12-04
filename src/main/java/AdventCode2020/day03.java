package AdventCode2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class day03 {

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/Inputs/day03.txt"));
        String line;
        int lineNumber = 0;


        int slope_x = 7;
        int slope_y = 1;

        int index_x_initial = slope_x;
        int index_y_initial = slope_y;

        int index_x_next = index_x_initial;
        int index_y_next = index_y_initial;

        int count_tree = 0;
        while ((line = bufferedReader.readLine()) != null){

            if(lineNumber == index_y_next){
                index_y_next = index_y_next + slope_y;
                System.out.println(line.charAt(index_x_next));
                if(line.charAt(index_x_next) == '#'){
                    count_tree++;
                }
                index_x_next = (index_x_next + slope_x)%line.length();
                //System.out.println(String.valueOf(index_x_next));
            }

            lineNumber++;

        }

        System.out.println(String.valueOf(count_tree));

    }
}
