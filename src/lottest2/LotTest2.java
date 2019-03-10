package lottest2;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public class LotTest2 implements RatesLT2 {
    private static Map<Integer, Tick2> lot = new HashMap<Integer, Tick2>();
    private static Map<Integer, LocalTime> lotIn =
            new HashMap<Integer, LocalTime>();
    private static List<Integer> availableTickets = new ArrayList<>();
    Random randNum = new Random();
    Screen2 screen = new Screen2();
    
    public static void main(String[] args) {
        new LotTest2();
    }
    public LotTest2(){
        Scanner kb = new Scanner(System.in);
        String ans = "y";
        int ticketCount = 0;
        double amount = 0;
        int nextTicketNumber = 0, ticketNumber;
        LocalTime timeIn, timeOut;
        Duration parkDuration;
        double divider = .5;
        /*
        for(int i = 1; i<6; i++){
            screen.getInScreen();
            while( !ans.equals("1")){
                ans = kb.nextLine();
                screen.getInScreen();
            }
            nextTicketNumber ++;
            lot.put(nextTicketNumber, new Tick2(nextTicketNumber, clockIn()));
            availableTickets.add(nextTicketNumber);
            ticketCount++;
        }
        */
        ans = "";
        //screen.getInScreen();
        while( !(ans.equals("1") || ans.equals("3")) ){
            screen.getInScreen();
            ans = kb.nextLine();
        }
        if(ans.equals("1")){
            nextTicketNumber++;
            lot.put( nextTicketNumber,new Tick2(nextTicketNumber, clockIn()) );
            availableTickets.add(nextTicketNumber);
            ticketCount++;
        }
        
        
        //ans = "";
        while ( !ans.equalsIgnoreCase("3")){
            Double inOrOut = randNum.nextDouble();
            if(inOrOut < divider){
                //Start checkIn with screen presenation;
                while( !( ans.equals("1") || ans.equals("3") ) ){
                    screen.getInScreen();
                    ans = kb.nextLine();
                }
                if( ans.equals("1")){   //Make Ticket;
                    nextTicketNumber ++;
                    lot.put(nextTicketNumber, 
                            new Tick2(nextTicketNumber, clockIn()));
                    availableTickets.add(nextTicketNumber);
                    ans = "";
                    ticketCount++;
                }
                else{
                    //Close Lot and Summarize;
                    System.out.println("Close The Lot.");
                }
            }
            else{
                while( !( ans.equals("1") || ans.equals("2") ) ){
                    screen.getOutScreen();
                    ans = kb.nextLine();
                }
                //test if possible to check out. Lot must have car to checkout;
                if( !availableTickets.isEmpty()){  //checkOut;
                    //get a ticket to checkout based on location in list
                    if( ans.equals("1")){
                        int aTicket = 
                            randNum.nextInt( availableTickets.size() );
                        ticketNumber = availableTickets.get(aTicket);
                        timeOut = clockOut();
                        lot.get(ticketNumber).setTimeOut(timeOut);   //timeOut
                        timeIn = lot.get(ticketNumber).getTimeIn();
                        parkDuration = Duration.between(timeIn, timeOut);
                        int hoursParked = (int)parkDuration.toHours();
                        amount = getCharges(parkDuration);
                        lot.get(ticketNumber).setFeeAmt(amount);   //fee
                        screen.setReceipt(ticketNumber, amount, hoursParked,
                                timeIn, timeOut);
                        availableTickets.remove(aTicket);
                    }
                    else if(ans.equals("2")){
                        //lost ticket   update statistics
                        int aTicket = 
                            randNum.nextInt( availableTickets.size() );
                        ticketNumber = availableTickets.get(aTicket);
                        timeOut = null;
                        lot.get(ticketNumber).setTimeOut(timeOut);
                        amount = LOST_TICKET_FEE;
                        lot.get(ticketNumber).setFeeAmt(amount);
                        screen.setLostTicketReceipt(amount);
                        availableTickets.remove(aTicket);
                    }
                }
                ans = "";
            }
        }
        getListOut();
        
        System.out.println("Ticket Count " + ticketCount);
            /*
            for (Map.Entry<Integer, LocalTime> ticket : lotIn.entrySet()){
                System.out.print("  "+ ticket.getKey());
            }
            System.out.println("\n\tWhich is your ticket number?");
            count = Integer.parseInt(kb.nextLine());
            if( !lotIn.containsKey(count)){
                System.out.println("\tYour ticket number is not listed."
                        + "\n\tPlease choose from the list above.");
            }
            else{
                timeIn = lotIn.get(count);
                System.out.println("before the loop timeIn: " + timeIn);
                for(Map.Entry<Integer, LocalTime> ticket : lotIn.entrySet()){
                    if(count == ticket.getKey()){
                        timeIn = ticket.getValue();
                    }
                }
                LocalTime timeOut = clockOut();
                Duration d1 = Duration.between(timeIn, timeOut);
                
                amount = getCharges(d1);
                lot.put(count, new Tick2(count, amount, timeIn, timeOut));
                //int timeParked = (int)d1.toHours();
                screen.setReceipt(count, amount, (int)d1.toHours(), timeIn, timeOut);
                lotIn.remove(count);
            }
            System.out.println("Quit q? or Enter to change another");
            ans = kb.nextLine();
        }
        
        for (Map.Entry<Integer, LocalTime> ticket : lotIn.entrySet()){
            System.out.println("map key entry "+ ticket.getKey() +
                    "\tobj LocalTime timeIn " + ticket.getValue());
        }
        for (Map.Entry<Integer, Tick2> ticket : lot.entrySet()){
            System.out.println("map key entry "+ ticket.getKey() +
                    "\tobj no. " + ticket.getValue().getTicketNo() +
                    "\tobj amount " + ticket.getValue().getFeeAmt() +
                    "\tobj timeIn " + ticket.getValue().getTimeIn() +
                    "\tobj timeOut " + ticket.getValue().getTimeOut() );
        }
        
        for (int i=1; i<=lot.size(); i++){
            ans = "ticket no. : " + lot.get(i).getTicketNo() + "\tin: " +
                    lot.get(i).getTimeIn() + "\ttime out: " +
                    lot.get(i).getTimeOut() + "\tfee amount: " +
                    lot.get(i).getFeeAmt();
            System.out.println(ans);
        }
*/
    }
    
    public void getListOut(){
        for (Map.Entry<Integer, Tick2> ticket : lot.entrySet()){
            System.out.println("map key "+ ticket.getKey() +
                "\tobj no. " + ticket.getValue().getTicketNo() +
                "\t amount " + ticket.getValue().getFeeAmt() +
                "\t timeIn " + ticket.getValue().getTimeIn() +
                "\t timeOut " + ticket.getValue().getTimeOut() );
        }
    }

public LocalTime clockIn(){
        int hour = randNum.nextInt(5) + 7;
        int min = randNum.nextInt(60);
        LocalTime hm = LocalTime.of(hour, min);
        return hm;
    }
    
    public LocalTime clockOut(){
        int hour = randNum.nextInt(11) + 13;
        int min = randNum.nextInt(60);
        LocalTime hm = LocalTime.of(hour, min);
        return hm;
    }
    
    public int getTicketNumber(){
        int aTicket = 
            randNum.nextInt( availableTickets.size() );
        return availableTickets.get(aTicket);
    }
    
    @Override
    public double getCharges(Duration eT){
        double charge = 0;
        int timeParked = (int)eT.toHours();
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