package com.barisceylan.lil.landonhotel.web.controller;

import com.barisceylan.lil.landonhotel.service.RoomReservationService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/occupancy")
public class OccupancyController {
    private final RoomReservationService service;

    public OccupancyController(RoomReservationService service) {
        this.service = service;
    }

    @GetMapping
    public String getOccupancy(Model model, @RequestParam(value = "date", required = false) String dateString){
        Date date = new Date();
        if (StringUtils.isNotBlank(dateString)){
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date = dateFormat.parse(dateString);
            } catch (Exception e){

            }
        }
        model.addAttribute("date", date);
        model.addAttribute("reservations", this.service.getRoomReservationForDate(dateString));
        return "occupancy";
    }
}
