
package kursovarabota;

import java.util.ArrayList;

public class dataBase {
    private ArrayList<Integer> numbers = new ArrayList<>();
    private ArrayList<Integer> bulls = new ArrayList<>();
    private ArrayList<Integer> cows = new ArrayList<>();

    public void addNumber(int x){
        numbers.add(x);
    }
    
    public int length(){
        return numbers.size();
    }
    
    public int getNumber(int i){
        return numbers.get(i);
    }
    
    public void setBulls(int x){
        bulls.add(x);
    }
    
    public int getBulls(int i){
        return bulls.get(i);
    }
    
    public void setCows(int x){
        cows.add(x);
    }
    
    public int getCows(int i){
        return cows.get(i);
    }
}
