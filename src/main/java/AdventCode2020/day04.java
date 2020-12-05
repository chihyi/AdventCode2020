package AdventCode2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class day04 {

    int valid_count = 0;
    ArrayList<HashMap<String,String>> passports = new ArrayList<>();

    public void solve() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/Inputs/day04.txt"));
        String line;
        HashMap<String,String> passport = new HashMap<String,String>();
        while((line=reader.readLine()) != null){
            if(!line.isBlank()){
                String[] key_values = line.split(" ");
                for(int i=0; i<key_values.length; i++){
                    String[] key_value = key_values[i].split(":");
                    passport.put(key_value[0], key_value[1]);
                }
                System.out.println(line);
            }
            else{
                check_valid(passport);
                passports.add(passport);
                passport = new HashMap<String,String>();
            }
        }
        check_valid(passports.get(passports.size() - 1));

        System.out.println(String.valueOf(valid_count));
    }

    private void check_valid(HashMap<String,String> passport){
        if(passport.containsKey("byr") && passport.containsKey("iyr") && passport.containsKey("eyr")
                && passport.containsKey("hgt") && passport.containsKey("hcl") && passport.containsKey("ecl")
                && passport.containsKey("pid")){

            boolean valid = check_valid_byr(passport.get("byr"))
                    && check_valid_iyr(passport.get("iyr"))
                    && check_valid_eyr(passport.get("eyr"))
                    && check_valid_hgt(passport.get("hgt"))
                    && check_valid_hcl(passport.get("hcl"))
                    && check_valid_ecl(passport.get("ecl"))
                    && check_valid_pid(passport.get("pid"));
            passport.put("valid", String.valueOf(valid));
            if(valid) {
                valid_count++;
                System.out.println("valid");
            }
            else {
                System.out.println("invalid");
            }
        }
    }

    private boolean check_valid_byr(String byr){
        boolean valid = false;
        if(byr.length()==4){
            int byr_int = Integer.valueOf(byr);
            if(byr_int >= 1920 && byr_int <= 2002){
                valid = true;
            }
        }
        return valid;
    }

    private boolean check_valid_iyr(String iyr){
        boolean valid = false;
        if(iyr.length()==4){
            int byr_int = Integer.valueOf(iyr);
            if(byr_int >= 2010 && byr_int <= 2020){
                valid = true;
            }
        }
        return valid;
    }

    private boolean check_valid_eyr(String eyr){
        boolean valid = false;
        if(eyr.length()==4){
            int byr_int = Integer.valueOf(eyr);
            if(byr_int >= 2020 && byr_int <= 2030){
                valid = true;
            }
        }
        return valid;
    }

    private boolean check_valid_hgt(String hgt){
        boolean valid = false;
        int hgt_int;
        String hgt_str;
        if(hgt.length() >= 3) {
            hgt_str = hgt.substring(0, hgt.length() - 2);
            if(hgt.charAt(hgt.length()-1) == 'm' && hgt.charAt(hgt.length()-2) == 'c'){
                hgt_int = Integer.valueOf(hgt_str);
                if(hgt_int >=150 && hgt_int <= 193){
                    valid = true;
                }
            }

            if(hgt.charAt(hgt.length()-1) == 'n' && hgt.charAt(hgt.length()-2) == 'i'){
                hgt_int = Integer.valueOf(hgt_str);
                if(hgt_int >=59 && hgt_int <= 76){
                    valid = true;
                }
            }
        }

      return valid;
    }

    private boolean check_valid_hcl(String hcl){
        boolean valid = false;
        if(hcl.charAt(0) == '#' && hcl.substring(1).length() == 6){
            if(hcl.substring(1).matches("[a-f0-9]+")){
                valid = true;
            }

        }

        return valid;
    }

    private boolean check_valid_ecl(String ecl){
        boolean valid = false;
            if(ecl.matches("amb|blu|brn|gry|grn|hzl|oth")){
                valid = true;
            }
        return valid;
    }

    private boolean check_valid_pid(String pid){
        boolean valid = false;
        System.out.println(pid);
        if(pid.length() == 9){
            valid = true;
        }
        return valid;
    }
}
