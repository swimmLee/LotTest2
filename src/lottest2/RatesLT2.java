package lottest2;

import java.time.Duration;

public interface RatesLT2 {
    double MIN_PARKING_FEE = 5.00;
    double HOURLY_PAEKING_FEE = 1.00;
    double MAX_PARKING_FEE = 15.00;
    double LOST_TICKET_FEE = 25.00;
    
    public double getCharges(Duration d);
}
