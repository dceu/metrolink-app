package metrolink.entity;

public class Stop{
    private String name;

    public Stop() {};
    private Stop stop;

    public Stop getInstance(){
        if (stop == null){
            stop = new Stop();
        }
        return stop;
    }
    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

    
    
    
}