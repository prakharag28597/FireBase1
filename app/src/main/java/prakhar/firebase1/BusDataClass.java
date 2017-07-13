package prakhar.firebase1;

/**
 * Created by prakharag on 13-07-2017.
 */

public class BusDataClass {
    private int bus_number;
    private String time;
    private String start;
    private String via1;
    private String via2;
    private String end;


    public BusDataClass(int bus_number,String time, String start, String via1, String via2, String end) {
        this.bus_number=bus_number;
        this.time =time;
        this.start = start;
        this.end=end;
        this.via1 = via1;
        this.via2 = via2;
    }
}
