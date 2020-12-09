package AdventCode2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class day07 {

    private HashMap<String, Bag> hashMap_Bags = new HashMap<>();
    private HashMap<String, BagNode> hashMap_BagNodes =  new HashMap<>();

    public void solve() throws IOException {
        readFile();

        createBagNodes();

        for(Map.Entry<String, BagNode> bagNodeEntry: hashMap_BagNodes.entrySet()){
            BagNode bagNode = bagNodeEntry.getValue();
            Bag bag = hashMap_Bags.get(bagNodeEntry.getKey());

            for(int i=0;i<bag.children_color.size();i++){

            }

        }

    }

    private void createBagNodes(){
        for(Map.Entry<String, Bag> bag_entry:hashMap_Bags.entrySet()){
            BagNode bagNode = new BagNode(bag_entry.getKey());
            hashMap_BagNodes.put(bag_entry.getKey(), bagNode);
        }
    }

    private void readFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/Inputs/day07.txt"));
        String line;
        while ((line=bufferedReader.readLine())!=null){
            System.out.println(line);

            Bag bag = new Bag();

            String[] split_parent = line.split(" bags contain");
            String parent_bag_color = split_parent[0];
            bag.color = parent_bag_color;

            String children_bag = split_parent[1];
            String[] split_children = children_bag.split(",");
            for(int i=0;i<split_children.length;i++){
                String[] split = split_children[i].split(" ");
                System.out.println(split.length);
                if(split.length>=5) {
                    Integer child_number = Integer.valueOf(split[1]);
                    String child_color = split[2] + " " + split[3];
                    bag.children_color.add(child_color);
                    bag.children_number.add(child_number);
                }
            }
            hashMap_Bags.put(parent_bag_color, bag);
        }
    }


    private class Bag{
        public String color = null;
        public ArrayList<Integer> children_number = new ArrayList<>();
        public ArrayList<String> children_color = new ArrayList<>();
    }

    private class BagNode {
        private String color = null;
        private BagNode parent = null;
        private ArrayList<BagNode> children = new ArrayList<>();

        public BagNode(String color){
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public BagNode getParent() {
            return parent;
        }

        public void setParent(BagNode parent) {
            this.parent = parent;
        }

        public ArrayList<BagNode> getChildren() {
            return children;
        }

        public BagNode addChild(BagNode child){
            child.setParent(this);
            this.children.add(child);
            return child;
        }
    }
}
