package com.barisceylan.lil.landonhotel.web.api;

import com.barisceylan.lil.landonhotel.data.entity.Reservation;
import com.barisceylan.lil.landonhotel.data.repository.ReservationRepository;
import com.barisceylan.lil.landonhotel.web.exception.BadRequestException;
import com.barisceylan.lil.landonhotel.web.exception.NotFoundException;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {
    private final ReservationRepository reservationRepository;

    public ReservationApiController(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    @GetMapping()
    public List<Reservation> getAllReservations(@RequestParam(value = "date", required = false)String dateString){
        if (StringUtils.isNotBlank(dateString)){
            Date date = new Date(new java.util.Date().getTime());
            return this.reservationRepository.findAllByReservationDate(date);
        }
        return this.reservationRepository.findAll();
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation addReservation(@RequestBody Reservation reservation){
        return this.reservationRepository.save(reservation);
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id")long id){
        Optional<Reservation> reservation = this.reservationRepository.findById(id);
        if (reservation.isEmpty()){
            throw new NotFoundException("reservation not found with id: " + id);
        }
        return reservation.get();
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable("id")long id, @RequestBody Reservation reservation){
        if (id != reservation.getId()){
            throw new BadRequestException("id on path doesn't match");
        }
        return this.reservationRepository.save(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteReservation(@PathVariable("id")long id){
        this.reservationRepository.deleteById(id);
    }
}
