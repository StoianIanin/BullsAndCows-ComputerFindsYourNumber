
package kursovarabota;

import java.util.Formatter;

public class createFile {
    private Formatter x;
    private String newline = System.getProperty("line.separator");
    
    public void openFile(){
        try{
            x = new Formatter("BullsAndCowsRecords.txt");
        }
        catch(Exception e){
            System.out.println("Error.");
        }
    }
    
    public void addRecord(int [] q, dataBase v, int i){
        x.format("%d%d%d%d%s%d%s%d%s", q[0],q[1],q[2],q[3],"        ",v.getBulls(i),"       ",v.getCows(i),newline);
    }
    public void basis(){
        x.format("%s%s","Numbers   Bulls   Cows",newline);
    }
    public void turnsAndTime(long a,long b,int c){
        x.format("%s%d%s%d%s%s","Took ",(b-a)," miliseconds and ",c," turns.",newline);
    }
    
    public void closeFile(){
        x.close();
    }
}
