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
                long value = Long.valueOf(value_str);
                String value_binary = String.format("%36s", Long.toBinaryString(value)).replace(' ', '0');
                //System.out.println(mask);
                //System.out.println(value_binary);

                for(int i=0;i<mask.length();i++){
                    if(mask.charAt(i) == '0'){
                        value_binary = changeCharInPosition(i, '0', value_binary);
                    }else if(mask.charAt(i) == '1'){
                        value_binary = changeCharInPosition(i, '1', value_binary);
                    }
                }
                value = Long.parseLong(value_binary,  2);

                if(!memory.containsKey(Long.parseLong(address_str))){
                    memory.put(Long.parseLong(address_str), value);
                }else{
                    memory.replace(Long.parseLong(address_str), value);
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
