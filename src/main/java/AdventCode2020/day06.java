package AdventCode2020;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class day06 {

    private class Group{
        Integer[] questions = new Integer[26];
        Integer sum;
        ArrayList<Person> persons = new ArrayList<>();

        public Group(){
            for(int i=0;i<questions.length;i++){
                questions[i] = 0;
            }
            sum = 0;
        }
    }

    private class Person{
        Integer[] questions = new Integer[26];

        public Person(){
            for(int i=0;i<questions.length;i++){
                questions[i] = 0;
            }
        }
    }

    public void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/Inputs/day06.txt"));
        String line;

        ArrayList<Group> groups = new ArrayList<>();
        Group group = new Group();

        while ((line=bufferedReader.readLine())!=null){
            System.out.println(line);

            if(!line.isBlank()){
                Person person = new Person();
                for(int i=0;i<line.length();i++){
                    person.questions[line.charAt(i) - 'a'] = 1;
                }
                group.persons.add(person);
            }else{
                groups.add(group);
                cal_sum(group);
                group = new Group();
            }

        }
        groups.add(group);
        cal_sum(group);

        int sum_total = 0;
        for(Group iGroup:groups){
            sum_total += iGroup.sum;
        }

        System.out.println(sum_total);
    }

    private void cal_sum(Group group){
        Integer[] questions_answered_yes = new Integer[26];
        for(int i=0;i<questions_answered_yes.length;i++){
            questions_answered_yes[i] = 1;
        }

        for(int i=0;i<group.persons.size();i++){
            for(int j=0;j<questions_answered_yes.length;j++){
                questions_answered_yes[j] = questions_answered_yes[j] * group.persons.get(i).questions[j];
            }
        }

        for(int value:questions_answered_yes){
            group.sum += value;
        }

        System.out.println(group.sum);
    }
}
