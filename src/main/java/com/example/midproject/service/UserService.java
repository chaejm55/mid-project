package com.example.midproject.service;

import com.example.midproject.entity.Reservation;
import com.example.midproject.entity.Role;
import com.example.midproject.entity.User;
import com.example.midproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ReservationService reservationService;
    public List<Reservation> getUserReservations(String userName) {
        User user = userRepository.findByName(userName).orElseThrow(
                () -> new NoSuchElementException("해당 유저가 없습니다.")
        );
        if (user.getRole() == Role.ADMIN) {
            return reservationService.getAllReservation();
        }
        return user.getReservationList();
    }
    public User getUser(String userName) {
        return userRepository.findByName(userName).orElseThrow(
                () -> new NoSuchElementException("해당 유저가 없습니다.")
        );
    }
}
