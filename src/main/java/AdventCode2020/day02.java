package AdventCode2020;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class day02 {


    private ArrayList<Entry> entries = new ArrayList<>();


    public void solve() throws IOException {
        int result_count = 0;
        BufferedReader csvReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Inputs/day02.csv"));
        String row = "";
        while ((row = csvReader.readLine()) != null){
            String[] separatedRow = row.split(";");

            String[] frequencies = separatedRow[0].split("-");
            String[] alphabetRaw = separatedRow[1].split(":");

            int lowFrequency = Integer.valueOf(frequencies[0]);
            int highFrequency = Integer.valueOf(frequencies[1]);
            String alphabet = alphabetRaw[0];
            String password = separatedRow[2];

            // Calculate valid
            int count = 0;
            char character = alphabet.charAt(0);

            for(int i=0;i<password.length();i++){
                if(password.charAt(i) == character){
                    count++;
                }
            }

            boolean valid = false;
 /*           if (lowFrequency <= count && highFrequency >= count){
                valid = true;
                result_count++;
            }*/

            if(0<lowFrequency && lowFrequency-1 < password.length() && 0<highFrequency && highFrequency-1 < password.length()) {
                if ((password.charAt(lowFrequency - 1) == character && password.charAt(highFrequency - 1) != character)
                        || (password.charAt(lowFrequency - 1) != character && password.charAt(highFrequency - 1) == character)){
                    valid = true;
                    result_count++;
                }
            }


            Entry entry = new Entry();
            entry.lowFrequency = lowFrequency;
            entry.highFrequency = highFrequency;
            entry.alphabet = alphabet;
            entry.password = password;
            entry.valid = valid;

            entries.add(entry);

            System.out.println("lowFrequency: "+String.valueOf(lowFrequency)+", highFrequency: "+String.valueOf(highFrequency)+
                    ", alphabet: "+alphabet+", password: " + password+", valid:" + String.valueOf(valid));

        }

        System.out.println(String.valueOf(result_count));
        System.out.println(entries.size());
    }

    static class Entry {
        private Integer lowFrequency;
        private Integer highFrequency;
        private String alphabet;
        private String password;
        private Boolean valid;

        public Entry(){}

        public Entry(Integer lowFrequency, Integer highFrequency, String alphabet, String password, Boolean valid){
            super();
            this.lowFrequency = lowFrequency;
            this.highFrequency = highFrequency;
            this.alphabet = alphabet;
            this.password = password;
            this.valid = valid;
        }
    }

}
