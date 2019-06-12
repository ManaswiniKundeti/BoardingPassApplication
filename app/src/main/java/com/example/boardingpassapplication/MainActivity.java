package com.example.boardingpassapplication;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.boardingpassapplication.databinding.ActivityMainBinding;
import com.example.boardingpassapplication.utilities.FakeDataUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * DataBindUtil.setContentView replaces our normal call of setContent view.
         * DataBindingUtil also created our ActivityMainBinding that we will eventually use to
         * display all of our data.
         */
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //load boardingPassInfo POJO with fakedata from utils
        BoardingPassInfo fakeBoardingPassData = FakeDataUtils.generateFakeBoardingPassInfo();
        //now bind data one by one using binding instance created
        displayBoardingPassInfo(fakeBoardingPassData);

    }

    private void displayBoardingPassInfo(BoardingPassInfo boardingPassInfo) {

        //set text in all textviews
        mainBinding.textViewPassengerName.setText(boardingPassInfo.passengerName);
        mainBinding.textViewOriginAirport.setText(boardingPassInfo.originCode);
        mainBinding.textViewFlightCode.setText(boardingPassInfo.flightCode);
        mainBinding.textViewDestinationAirport.setText(boardingPassInfo.destCode);

        //use SimpleDateFormat formatter to set formatted values
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String boardingTime = formatter.format(boardingPassInfo.boardingTime);
        String departureTime = formatter.format(boardingPassInfo.departureTime);
        String arrivalTime = formatter.format(boardingPassInfo.arrivalTime);

        //set values using formatted data
        mainBinding.textViewBoardingTime.setText(boardingTime);
        mainBinding.textViewDepartureTime.setText(departureTime);
        mainBinding.textViewArrivalTime.setText(arrivalTime);

        //use TimeUnit methods to format total minutes until boarding
        long totalMinutesUntilBoarding = boardingPassInfo.getMinutesUntilBoarding();
        long hoursUntilBoarding = TimeUnit.MINUTES.toHours(totalMinutesUntilBoarding);
        long minutesLessHoursUntilBoarding = totalMinutesUntilBoarding - TimeUnit.HOURS.toMinutes(hoursUntilBoarding);

        String hoursAndMinutesUntilBoarding = getString(R.string.countDownFormat,
                hoursUntilBoarding,
                minutesLessHoursUntilBoarding);

        mainBinding.textViewBoardingInCountdown.setText(hoursAndMinutesUntilBoarding);
        mainBinding.textViewTerminal.setText(boardingPassInfo.departureTerminal);
        mainBinding.textViewGate.setText(boardingPassInfo.departureGate);
        mainBinding.textViewSeat.setText(boardingPassInfo.seatNumber);





    }
}
