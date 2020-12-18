package AdventCode2020;

import org.w3c.dom.ranges.Range;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.temporal.ValueRange;
import java.util.*;

public class day16 {

    private HashMap<String, Field> ticket_fields = new HashMap<>();
    private ArrayList<Integer> my_ticket = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> tickets = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> tickets_valid = new ArrayList<>();

    public void solve() throws IOException {

        // Read file and parse
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/Inputs/day16.txt"));
        String line;
        while (!(line=bufferedReader.readLine()).equals("")){
            System.out.println(line);
            String[] split = line.split(": ");
            String field_name = split[0];
            String[] split_range = split[1].split(" or ");
            String[] split_range1 = split_range[0].split("-");
            int range1_min = Integer.valueOf(split_range1[0]);
            int range1_max = Integer.valueOf(split_range1[1]);
            ValueRange range1 = ValueRange.of(range1_min, range1_max);

            String[] split_range2 = split_range[1].split("-");
            int range2_min = Integer.valueOf(split_range2[0]);
            int range2_max = Integer.valueOf(split_range2[1]);
            ValueRange range2 = ValueRange.of(range2_min, range2_max);

            Field field = new Field(field_name, range1, range2);
            ticket_fields.put(field_name, field);
        }

        bufferedReader.readLine();
        line = bufferedReader.readLine();
        String[] split_my_ticket = line.split(",");
        for(int i=0; i<split_my_ticket.length;i++){
            my_ticket.add(Integer.valueOf(split_my_ticket[i]));
        }

        bufferedReader.readLine();
        bufferedReader.readLine();
        while((line = bufferedReader.readLine())!=null){
            String[] split = line.split(",");
            ArrayList<Integer> ticket = new ArrayList<>();
            for(int i=0;i<split.length;i++){
                ticket.add(Integer.valueOf(split[i]));
            }
            tickets.add(ticket);
        }

        // Delete invalid tickets
        for(int i=0;i<tickets.size();i++) {
            boolean valid = false;
            for (int j = 0; j < tickets.get(i).size(); j++) {
                int number = tickets.get(i).get(j);
                valid = false;
                for (Map.Entry<String, Field> me : ticket_fields.entrySet()) {
                    if (me.getValue().range1.isValidValue(number)) {
                        valid = true;
                        break;
                    }else if(me.getValue().range2.isValidValue(number)){
                        valid = true;
                        break;
                    }
                }
                if(valid == false){
                    break;
                }
            }
            if(valid == true) {
                ArrayList<Integer> ticket_valid = new ArrayList<>(tickets.get(i));
                tickets_valid.add(ticket_valid);
            }
        }


        // Find the possible positions of each field
        for(Map.Entry<String, Field> me: ticket_fields.entrySet()){
            Field field = me.getValue();
            for(int j=0;j<tickets_valid.get(0).size();j++){
                boolean valid = true;
                for(int i=0;i<tickets_valid.size();i++){
                    int number = tickets_valid.get(i).get(j);
                    if(!field.range1.isValidValue(number) && !field.range2.isValidValue(number)){
                        valid = false;
                        break;
                    }
                }
                if(valid == true){
                    field.possible_position.add(j);
                }
            }
        }

        // find position
        int num_position_found = 0;
        Set<Integer> position_occupied = new HashSet<>();
        while (num_position_found < ticket_fields.size()){
            for(Map.Entry<String, Field> me: ticket_fields.entrySet()){
                Field field = me.getValue();
                if(field.possible_position.size() == 1){
                    int position = field.possible_position.iterator().next();
                    if(!position_occupied.contains(position)) {
                        field.position_found = true;
                        num_position_found++;
                        position_occupied.add(position);
                    }
                }else{
                    for(int to_be_deleted_position: position_occupied){
                        if(field.possible_position.contains(to_be_deleted_position)){
                            field.possible_position.remove(to_be_deleted_position);
                        }
                    }
                }
            }
        }

        long multiply = 1L;

        ArrayList<Integer> index = new ArrayList<>();
        for(Map.Entry<String, Field> me: ticket_fields.entrySet()){
            Field field = me.getValue();
            if(me.getKey().contains("departure")){
                index.add(field.possible_position.iterator().next());
            }
        }

        for(int i=0;i<index.size();i++){
            multiply = multiply * my_ticket.get(index.get(i));
        }

        System.out.println(multiply);
















    }

    private class Field{
        public String name;
        public ValueRange range1;
        public ValueRange range2;
        public Set<Integer> possible_position;
        public boolean position_found;

        public Field(String name, ValueRange range1, ValueRange range2){
            this.name = name;
            this.range1 = range1;
            this.range2 = range2;
            this.possible_position = new HashSet<>();
            this.position_found = false;
        }
    }
}
