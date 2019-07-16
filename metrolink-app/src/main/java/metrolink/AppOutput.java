package metrolink;

import metrolink.entity.Stop;

public class AppOutput{


    AppOutput(){};

    private static AppOutput instance = new AppOutput();

    public static AppOutput getInstance() {
        return instance;
    }

    

    public static void print(String s){
        System.out.println(s);
    }
    
    public void printStop(Stop s){
        System.out.println(s.toString());
    }

    public static void prompt(){
        System.out.println("What station are you currently at?");
    }

    public void countDown(){
        System.out.println("The Next train arrives at...");
    }

}
