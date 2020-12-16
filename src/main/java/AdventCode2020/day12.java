package AdventCode2020;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class day12 {

    private ArrayList<Command> commands = new ArrayList<>();
    private int[] direction = new int[]{1, 0};
    private int[] position = new int[]{0, 0};
    private int[] waypoint = new int[]{10, 1};
    public void solve() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Inputs/day12.txt"));
        String line;
        while ((line=bufferedReader.readLine())!=null){
            Command command = new Command();
            command.command = line.charAt(0);
            command.value = Integer.valueOf(line.substring(1));
            commands.add(command);
        }

        for(int i=0;i<commands.size();i++){
            System.out.println(commands.get(i).command + ", " + String.valueOf(commands.get(i).value));
            if(commands.get(i).command == 'N'){
                waypoint[1] += commands.get(i).value;
            }else if(commands.get(i).command == 'S'){
                waypoint[1] -= commands.get(i).value;
            }else if(commands.get(i).command == 'E'){
                waypoint[0] += commands.get(i).value;
            }else if(commands.get(i).command == 'W'){
                waypoint[0] -= commands.get(i).value;
            }else if(commands.get(i).command == 'L'){
                double radians = Math.toRadians(commands.get(i).value);
                int direction_new_x, direction_new_y;
                direction_new_x = waypoint[0]*(int)(Math.cos(radians)) - waypoint[1]*(int)(Math.sin(radians));
                direction_new_y = waypoint[0]*(int)Math.sin(radians) + waypoint[1]*(int)Math.cos(radians);

                waypoint[0] = direction_new_x;
                waypoint[1] = direction_new_y;

            }else if(commands.get(i).command == 'R'){
                double radians = Math.toRadians(-commands.get(i).value);
                int direction_new_x, direction_new_y;
                direction_new_x = waypoint[0]*(int)(Math.cos(radians)) - waypoint[1]*(int)(Math.sin(radians));
                direction_new_y = waypoint[0]*(int)Math.sin(radians) + waypoint[1]*(int)Math.cos(radians);

                waypoint[0] = direction_new_x;
                waypoint[1] = direction_new_y;

            }else if(commands.get(i).command == 'F'){
                position[0] += commands.get(i).value * waypoint[0];
                position[1] += commands.get(i).value * waypoint[1];
            }else{
                System.out.println("error");
            }
            System.out.println(String.valueOf(position[0]) + ", " + String.valueOf(position[1]));
            System.out.println(String.valueOf(waypoint[0]) + ", " + String.valueOf(waypoint[1]));
        }

        System.out.println(Math.abs(position[0]) + Math.abs(position[1]));




    }

    private class Command{
        public char command;
        public int value;
    }
}
