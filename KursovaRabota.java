
package kursovarabota;

import java.util.Random;
import java.util.Scanner;

public class KursovaRabota {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        boolean numbers [] = new boolean [10];
        int spare [] = new int [4];
        int zero [] = new int [4];
        int four [] = new int [4];
        dataBase a = new dataBase();
        Scanner scanner = new Scanner(System.in);
        int tmp;
        
        do{ //untill the guessed number = 0 Bulls and 0 Cows
            newNumber(numbers);
            generate(numbers, spare);
            tmp = arrayToInt(spare);
            a.addNumber(tmp);
            enterBullsAndCows(spare,a,scanner);
        }while(!((a.getBulls(a.length()-1)==0)&&(a.getCows(a.length()-1)==0)));
        
        
        do{ //untill we have the four numbers (bulls+cows=4)
            newWithoutNumber(numbers, spare);
            generate(numbers, zero);
            tmp=arrayToInt(zero);
            if(fullCheck(a, tmp)){
                a.addNumber(tmp);
                enterBullsAndCows(zero, a, scanner);
            }
        }while(!((a.getBulls(a.length()-1)+a.getCows(a.length()-1))==4));
        
        
        if((a.getBulls(a.length()-1))==4){ //if the last number is the right one:
            System.out.println("GAME OVER");
            System.out.println("The PC found your number in "+a.length()+" turns.");
        }
        else{//else, generate number out of the four in the last guessed
            do{
                newOutOfFour(numbers, zero);
                generate(numbers, four);
                tmp=arrayToInt(four);
                if(fullCheck(a, tmp)){
                    a.addNumber(tmp);
                    enterBullsAndCows(four, a, scanner);
                }
            }while(a.getBulls(a.length()-1)<4);
            System.out.println("GAME OVER");
            System.out.println("The PC found your number in "+a.length()+" turns.");
        }
        long endTime = System.currentTimeMillis();
        createFile j = new createFile();
        toTxt(a, j);
        j.turnsAndTime(startTime, endTime, a.length());
        j.closeFile();
    }
    
    
    private static boolean check(dataBase b,int tmp,int i) {

            int prd = tmp;
            int Bulls = 0;
            int Cows = 0;
            for (int j = 3; j >= 0; j--) {
                int s = b.getNumber(i);
                for (int q = 3; q >= 0; q--) {
                   if((j==q)&&(prd%10==s%10)){
                        Bulls++;
                        s=s/10;
                    }
                    else if(j!=q && prd%10==s%10){
                        Cows++;
                        s=s/10;
                    }
                    else s=s/10;
                }
                prd=prd/10;
            }
        return ((Bulls==b.getBulls(i))&&(Cows==b.getCows(i)));
    }
    
    
    private static boolean fullCheck(dataBase a,int tmp){
        for (int i = 0; i < a.length(); i++) {
            if(check(a, tmp, i)==false) return false;
        }
        return true;
    }
    
    
    private static void newNumber(boolean[] asd) {
        for (int j = 0; j < 10; j++) {
            asd[j] = false;
        }
    }
    
    private static void newWithoutNumber(boolean [] asd,int [] qwe){
        for (int i = 0; i < 10; i++) {
            asd[i] = i==qwe[0]||i==qwe[1]||i==qwe[2]||i==qwe[3];
        }
    }
    
    private static void newOutOfFour(boolean [] asd, int [] qwe){
        for (int i = 0; i < 10; i++) {
            asd[i] = !(i==qwe[0]||i==qwe[1]||i==qwe[2]||i==qwe[3]);
        }
    }
    
    private static void generate(boolean arrayForUniqueNumbers[],int number[]) {
        int i=0;
        while(i<4){
            Random random = new Random();
            int p = random.nextInt(10);
            if(arrayForUniqueNumbers[p]==false){
                number[i]=p;
                i++;
                arrayForUniqueNumbers[p]=true;
            }
        }
    }
    
    private static int arrayToInt(int [] numberr){
        int Sum=10000;
        int k=1000;
        for (int i = 0; i < 4; i++) {
            Sum+=numberr[i]*k;
            k/=10;
        }
        return Sum;
    }
    
    private static void intToArray(int [] f,dataBase a, int i){
        int tmp = a.getNumber(i);
        for(int t = 3; t >= 0;t--){
            f[t]=tmp%10;
            tmp=tmp/10;
        }
    }
    
    private static void enterBullsAndCows(int spare[],dataBase h,Scanner scanner){
        System.out.println(spare[0]+""+spare[1]+""+spare[2]+""+spare[3]);
        System.out.println("Enter bulls:");
        h.setBulls(scanner.nextInt());
        System.out.println("Enter cows:");
        h.setCows(scanner.nextInt());
    }
    
    private static void toTxt(dataBase a, createFile j){
        int rty [] = new int[4];
        j.openFile();
        j.basis();
        for (int i = 0; i < a.length(); i++) {
            intToArray(rty, a, i);
            j.addRecord(rty, a, i);
        }
    }
    
}
