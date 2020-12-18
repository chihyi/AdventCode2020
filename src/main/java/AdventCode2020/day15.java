package AdventCode2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class day15 {

    private int[] init_number = new int[]{11,0,1,10,5,19};
    private ArrayList<Integer> spoken_number = new ArrayList<>();
    private HashMap<Integer, LastTwoTurns> number_lastTurn = new HashMap<>();
    private int goal_turn = 30000000;
    public void solve(){
        for(int i=0;i<goal_turn;i++){
            if(i<init_number.length){
                spoken_number.add(init_number[i]);
                LastTwoTurns lastTwoTurns = new LastTwoTurns(i, -1);
                number_lastTurn.put(init_number[i], lastTwoTurns);
            }else{
                int last_spoken_number = spoken_number.get(i-1);
                if(number_lastTurn.get(last_spoken_number).second_last_turn == -1){
                    spoken_number.add(0);
                    LastTwoTurns lastTwoTurns = number_lastTurn.get(0);
                    lastTwoTurns.second_last_turn = lastTwoTurns.last_turn;
                    lastTwoTurns.last_turn = i;
                }else{
                    LastTwoTurns lastTwoTurns = number_lastTurn.get(last_spoken_number);
                    int new_spoken_number = lastTwoTurns.last_turn - lastTwoTurns.second_last_turn;
                    spoken_number.add(new_spoken_number);
                    if(!number_lastTurn.containsKey(new_spoken_number)){
                        LastTwoTurns new_lastTwoTurns = new LastTwoTurns(i, -1);
                        number_lastTurn.put(new_spoken_number, new_lastTwoTurns);
                    }else{
                        LastTwoTurns get_lastTwoTurns = number_lastTurn.get(new_spoken_number);
                        get_lastTwoTurns.second_last_turn = get_lastTwoTurns.last_turn;
                        get_lastTwoTurns.last_turn = i;
                    }
                }
            }
        }
        //System.out.println(spoken_number.toString());
        System.out.println(spoken_number.get(spoken_number.size()-1));



    }

    private class LastTwoTurns{
        public int last_turn;
        public int second_last_turn;

        public LastTwoTurns(int last_turn, int second_last_turn){
            this.last_turn = last_turn;
            this.second_last_turn = second_last_turn;
        }
    }
}
