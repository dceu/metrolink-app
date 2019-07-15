package metrolink;

import metrolink.entity.Stop;

public class AppOutput{


    AppOutput(){};

    private static AppOutput instance = new AppOutput();

    public static AppOutput getInstance() {
        return instance;
    }

    

    public void print(String s){
        System.out.println(s);
    }
    
    public void printStop(Stop s){
        System.out.println(s.toString());
    }


}
