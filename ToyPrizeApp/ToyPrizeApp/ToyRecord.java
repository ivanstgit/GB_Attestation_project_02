package ToyPrizeApp;

public class ToyRecord implements Comparable<ToyRecord> {
    private long id;
    private String desc; 
    private double weight;

    public ToyRecord(long id, String desc, double weight){
        this.id = id;
        this.desc = desc;
        this.weight = weight;
    }

    public Long getId(){
        return id;
    }

    public String getDesc(){
        return desc;
    }

    public Double getWeight(){
        return weight;
    }

    @Override
    public int compareTo(ToyRecord o) {
        return Double.compare(this.weight, o.weight);
    }
}
