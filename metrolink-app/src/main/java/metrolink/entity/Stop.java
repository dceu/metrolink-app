package metrolink.entity;

class Stop{
    private String name;

    private Stop() {};
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

    
    
    
}