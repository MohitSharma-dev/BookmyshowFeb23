package com.example.bookmyshowfeb23.services;

import com.example.bookmyshowfeb23.models.Show;
import com.example.bookmyshowfeb23.models.ShowSeat;
import com.example.bookmyshowfeb23.models.ShowSeatType;
import com.example.bookmyshowfeb23.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceCalculatorService {
    ShowSeatTypeRepository showSeatTypeRepository;
    @Autowired
    PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository){
        this.showSeatTypeRepository = showSeatTypeRepository;
    }
    public int calculatePrice(List<ShowSeat> showSeats, Show show){
        // 1. Find all the showSeatTypes for this show
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        int amount = 0;
        for(ShowSeat showSeat : showSeats){
            for(ShowSeatType showSeatType : showSeatTypes){
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())){
                    amount += showSeatType.getPrice();
                }
            }
        }
        return amount;
    }
}

// show : 1
//       Platinum : 100
//       Gold : 50
//       Silver : 25

// 1 4 : Gold +50
// 1 5 : Gold +50
// 1 7 : Silver +25