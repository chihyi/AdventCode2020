package AdventCode2020;

import java.io.*;
import java.nio.channels.FileLockInterruptionException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class day01_1 {

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public Integer[] readInput() throws FileNotFoundException {
        try {
            File file = new File(System.getProperty("user.dir")+"/Inputs/day01_1.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                arrayList.add(Integer.valueOf(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Integer[] array = arrayList.toArray(new Integer[arrayList.size()]);
        return array;

    }

    public void solve(int sum) throws FileNotFoundException {
        Integer[] inputArray = readInput();
        List newArray[] = new List[sum+1];

        for(int i=0; i < newArray.length; i++){
            newArray[i] = new ArrayList<Integer>();
        }

        for(int i=0; i < inputArray.length; i++){
            if(inputArray[i] <= sum){
                newArray[inputArray[i]].add(i);
            }
        }

        for(int i=0; i < newArray.length/2; i++){
            if(!newArray[i].isEmpty() && !newArray[sum-i].isEmpty()){

                int result = i * (sum-i);
            }
            if(!newArray[i].isEmpty()) {
                for (int j = i + 1; j < newArray.length; j++) {
                    if(!newArray[j].isEmpty() && sum-i-j > 0 && !newArray[sum-i-j].isEmpty()){
                        System.out.println(String.valueOf(i*j*(sum-i-j)));
                        System.out.println(String.valueOf(i) + ", " + String.valueOf(j) + ", " + String.valueOf(sum-i-j));
                    }


                }
            }

        }







    }
}
