package AdventCode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

public class day14 {

    private String mask;
    private HashMap<Long, Long> memory = new HashMap<>();

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Inputs/day14.txt"));
        String line;
        while ((line=bufferedReader.readLine())!=null){
            String[] split = line.split(" = ");
            if(split[0].equals("mask")) {
                mask = split[1];
            }else{
                String[] split_mem_1 = split[0].split("\\[");
                String[] split_mem_2 = split_mem_1[1].split("\\]");
                String address_str = split_mem_2[0];
                String value_str = split[1];
                long value = Long.parseLong(value_str);
                long address = Long.valueOf(address_str);
                String address_binary = String.format("%36s", Long.toBinaryString(address)).replace(' ', '0');
                System.out.println(mask);
                System.out.println(address_binary);

                int n_X = 0;
                for(int i=0;i<mask.length();i++){
                    if(mask.charAt(i) == 'X'){
                        address_binary = changeCharInPosition(i, 'X', address_binary);
                        n_X++;
                    }else if(mask.charAt(i) == '1'){
                        address_binary = changeCharInPosition(i, '1', address_binary);
                    }
                }

                System.out.println(address_binary);
                System.out.println(n_X);

                for(int i=0;i<Math.pow(2, n_X);i++){
                    String address_binary_new = address_binary;
                    String i_str = Integer.toBinaryString(i);
                    int n_lacked_zeros = n_X-i_str.length();
                    for(int j=0;j<n_lacked_zeros;j++){
                        i_str = "0" + i_str;
                    }
                    System.out.println(i_str);
                    int i_X = 0;
                    for(int k=0;k<address_binary.length();k++){
                        if(address_binary.charAt(k) == 'X'){
                            address_binary_new = changeCharInPosition(k, i_str.charAt(i_X), address_binary_new);
                            i_X++;
                        }
                    }
                    System.out.println(address_binary_new);
                    System.out.println(i_X);
                    System.out.println(n_X);
                    if(!memory.containsKey(Long.parseLong(address_binary_new, 2))){
                        memory.put(Long.parseLong(address_binary_new, 2), value);
                    }else{
                        memory.replace(Long.parseLong(address_binary_new, 2), value);
                    }

                }
            }

        }

        long sum = 0;
        for(Map.Entry<Long, Long> me: memory.entrySet()){
            sum += me.getValue();
        }
        System.out.println(sum);
    }

    public String changeCharInPosition(int position, char ch, String str){
        char[] charArray = str.toCharArray();
        charArray[position] = ch;
        return new String(charArray);
    }
}
