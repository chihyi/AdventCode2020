package AdventCode2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class day08 {

    private ArrayList<Command> commands = new ArrayList<>();
    private int accumulator = 0;

    public void solve() throws IOException {
        readFile();

        // Part 1
        /*int index_toBeExecuted = 0;
        Command command = commands.get(index_toBeExecuted);

        while (!command.executed){
            if(command.command_string.equals("nop")){
                index_toBeExecuted += 1;
            }else if(command.command_string.equals("acc")){
                accumulator += command.command_number;
                index_toBeExecuted += 1;
            }else if(command.command_string.equals("jmp")){
                index_toBeExecuted += command.command_number;
            }
            command.executed = true;
            command = commands.get(index_toBeExecuted);
        }*/

        // Part 2

        int index_last = commands.size();
        for(int index_toBeChanged=0; index_toBeChanged < commands.size();index_toBeChanged++){

            //System.out.println(String.valueOf(index_toBeChanged) + ", " + commands_new.get(index_toBeChanged).command_string);

            if(commands.get(index_toBeChanged).command_string.equals("nop")){
                commands.get(index_toBeChanged).command_string = "jmp";
            }else if(commands.get(index_toBeChanged).command_string.equals("jmp")){
                commands.get(index_toBeChanged).command_string = "nop";
            }else{
                continue;
            }

            int index_toBeExecuted = 0;
            accumulator = 0;
            Command command = commands.get(index_toBeExecuted);

            while (!command.executed){
                if(command.command_string.equals("nop")){
                    index_toBeExecuted += 1;
                }else if(command.command_string.equals("acc")){
                    accumulator += command.command_number;
                    index_toBeExecuted += 1;
                }else if(command.command_string.equals("jmp")){
                    index_toBeExecuted += command.command_number;
                }
                command.executed = true;
                if(index_toBeExecuted < index_last) {
                    command = commands.get(index_toBeExecuted);
                }
            }

            System.out.println(index_toBeExecuted);
            if(index_toBeExecuted == index_last)
                break;

            if(commands.get(index_toBeChanged).command_string.equals("nop")){
                commands.get(index_toBeChanged).command_string = "jmp";
            }else if(commands.get(index_toBeChanged).command_string.equals("jmp")){
                commands.get(index_toBeChanged).command_string = "nop";
            }

            for(int i=0; i<commands.size();i++){
                commands.get(i).executed = false;
            }
        }


        System.out.println(accumulator);


    }

    private void readFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/Inputs/day08.txt"));
        String line;
        while ((line=bufferedReader.readLine())!=null){
            Command command = new Command();
            String[] split = line.split(" ");
            command.command_string = split[0];
            command.command_number = Integer.valueOf(split[1]);
            command.executed = false;

            commands.add(command);
        }
    }

    private class Command{
        public String command_string;
        public Integer command_number;
        public boolean executed = false;
    }
}
