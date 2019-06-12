package com.example.boardingpassapplication;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

/**
 * BoardingPassInfo is a simple POJO that contains information about a boarding
 * pass! This class acts as a placeholder for actual data. This can be filled with data from
 * an ariline API or some database loopkup. Here, as a part of this project, I have designed a
 * fakeData class that generates random data
 **/

public class BoardingPassInfo {

    public String passengerName;
    public String flightCode;
    public String originCode;
    public String destCode;

    public Timestamp boardingTime;
    public Timestamp departureTime;
    public Timestamp arrivalTime;

    public String departureTerminal;
    public String departureGate;
    public String seatNumber;

    public int barCodeImageResource;

    public long getMinutesUntilBoarding() {
        long millisUntilBoarding = boardingTime.getTime() - System.currentTimeMillis();
        return TimeUnit.MILLISECONDS.toMinutes(millisUntilBoarding);
    }
}
