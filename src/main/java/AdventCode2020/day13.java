package AdventCode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class day13 {

    private int timestamp_start;
    private BigInteger timestamp = BigInteger.valueOf(0);
    private ArrayList<String> buses = new ArrayList<>();

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Inputs/day13.txt"));
        String line;
        line=bufferedReader.readLine();
        timestamp_start = Integer.valueOf(line);

        line = bufferedReader.readLine();
        String[] split = line.split(",");
        for(int i=0;i<split.length;i++){
                buses.add(split[i]);
        }

        // 67,7,59,61
        /*buses.add("67");
        buses.add("7");
        buses.add("59");
        buses.add("61");*/

        Long buses_multiply = 1L;
        for(int i = 0;i<buses.size();i++){
            if(!buses.get(i).equals("x")){
                buses_multiply *= Long.valueOf(buses.get(i));
            }
        }

        for(int i=0;i<buses.size();i++){
            if(!buses.get(i).equals("x")){
                int prime = Integer.valueOf(buses.get(i));

                BigInteger offset = BigInteger.valueOf(prime - (i % prime)).mod(BigInteger.valueOf(prime));

                BigInteger base = BigInteger.valueOf((buses_multiply / prime) % prime);
                int power = prime-2;

                BigInteger n_periods = offset.multiply(base.pow(power)).mod(BigInteger.valueOf(prime));
                System.out.println(base.pow(power).multiply(base).mod(BigInteger.valueOf(prime)));
                BigInteger timestamp_new = n_periods.multiply(BigInteger.valueOf((buses_multiply / prime)));
                System.out.println(timestamp_new.mod(BigInteger.valueOf(prime)).equals(offset));

                System.out.println(timestamp_new.mod(BigInteger.valueOf((buses_multiply / prime))).equals(BigInteger.valueOf(0)));

                timestamp = timestamp.add(timestamp_new);

            }
       }
        System.out.println(timestamp.mod(BigInteger.valueOf(buses_multiply)));







    }
}
