package AdventCode2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class day07 {

    private HashMap<String, Bag> hashMap_Bags = new HashMap<>();
    private HashSet<String> hashSet_found = new HashSet<>();
    private HashSet<String> hashSet_toDo = new HashSet<>();

    private String contain_bag = "shiny gold";
    private Integer num_bags = 0;

    public void solve() throws IOException {
        readFile();


        hashSet_toDo.add(contain_bag);
        // Part 1 solution
        /*while (!hashSet_toDo.isEmpty()){
            String target_color = hashSet_toDo.iterator().next();
            for(Map.Entry<String, Bag> me:hashMap_Bags.entrySet()){
                Bag bag = me.getValue();
                if(bag.children.containsKey(target_color)){
                    hashSet_toDo.add(bag.color);
                    hashSet_found.add(bag.color);
                    System.out.println(bag.color);
                }
            }
            hashSet_toDo.remove(target_color);
        }

        System.out.println(hashSet_found.size());*/

        // Part02 solution
        /*int multiple_base = 1;
        while (!hashSet_toDo.isEmpty()){
            String target_color = hashSet_toDo.iterator().next();
            Bag bag = hashMap_Bags.get(target_color);
            for(Map.Entry<String, Integer> me:bag.children.entrySet()){
                num_bags += me.getValue() * multiple_base;
                hashSet_toDo.add(me.getKey());
            }
            hashSet_toDo.remove(target_color);
        }
        System.out.println(num_bags);*/
        System.out.println(num_bags_inside(hashMap_Bags.get(contain_bag)) - 1);


    }

    private Integer num_bags_inside(Bag bag){
        if(bag.children.isEmpty()){
            return 1;
        }else{
            int sum = 0;
            for(Map.Entry<String, Integer> me: bag.children.entrySet()){
                Bag child = hashMap_Bags.get(me.getKey());
                sum += me.getValue() * num_bags_inside(child) ;
            }
            return sum + 1;
        }
    }

    private void readFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/Inputs/day07.txt"));
        String line;
        while ((line=bufferedReader.readLine())!=null){
            //System.out.println(line);

            Bag bag = new Bag();

            String[] split_parent = line.split(" bags contain");
            String parent_bag_color = split_parent[0];
            bag.color = parent_bag_color;

            String children_bag = split_parent[1];
            String[] split_children = children_bag.split(",");
            for(int i=0;i<split_children.length;i++){
                String[] split = split_children[i].split(" ");
                if(split.length>=5) {
                    Integer child_number = Integer.valueOf(split[1]);
                    String child_color = split[2] + " " + split[3];
                    bag.children.put(child_color, child_number);
                }
            }
            hashMap_Bags.put(parent_bag_color, bag);
        }
    }


    private class Bag{
        public String color = null;
        public HashMap<String, Integer> children = new HashMap<>();
    }


}
