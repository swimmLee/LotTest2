package lottest2;

import java.time.Duration;
import java.time.LocalTime;

public class ChargesLT2 implements RatesLT2 {
    private int timeParked;
    private LocalTime inClock;
    private LocalTime outClock;
    
    public ChargesLT2(){
    }
    
    public ChargesLT2(LocalTime inC, LocalTime outC){
        this.inClock = inC;
        this.outClock = outC;
    }
    
    @Override
    public double getCharges(Duration elapseTime){
        Duration d1 = elapseTime;
        double charge = 0;
        timeParked = (int)d1.toHours();
        if(timeParked<=3){
            charge = MIN_PARKING_FEE;
        } else if (timeParked > 3 && timeParked <= 13){
            charge = MIN_PARKING_FEE +(timeParked-3)*HOURLY_PAEKING_FEE;
        } else if (timeParked>13 && timeParked<=24){
            charge = MAX_PARKING_FEE;
        }
        return charge;
    }

}