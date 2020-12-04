package CSI3450Project.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CSI3450Project.dao.*;
import CSI3450Project.model.*;

@RestController
@RequestMapping("/csi3450project/v1")
public class ReadController {
    @Autowired private BookingRepository bookingRepository;
    @Autowired private BusinessRepository businessRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private EventRepository eventRepository;
    @Autowired private RoomRepository roomRepository;

    // BOOKING endpoints
    @GetMapping("/booking")
    public Booking getBookingById(HttpServletRequest request, @RequestParam Integer bookingId) {
        return bookingRepository.findById(bookingId).get();
    }

    @GetMapping("/booking/customer")
    public List<Booking> getBookingsForCustomer(HttpServletRequest request, Integer customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @GetMapping("/booking/business")
    public List<Booking> getBookingsForBusiness(HttpServletRequest request, Integer businessId) {
        return bookingRepository.findByBusinessId(businessId);
    }

    //BUSINESS endpoints
    @GetMapping("/business")
    public Business getBusiness(HttpServletRequest request, @RequestParam Integer businessId) {
        return businessRepository.findById(businessId).get();
    }

    @GetMapping("/businesses")
    public List<Business> getBusinesses(HttpServletRequest request, String zip) {
        return businessRepository.findByZip(zip);
    }

    //CUSTOMER endpoints
    @GetMapping("/customer")
    public Customer getCustomer(HttpServletRequest request, Integer customerId) {
        return customerRepository.findById(customerId).get();
    }

    //EVENT endpoints
    @GetMapping("/event")
    public Event getEvent(HttpServletRequest request, Integer eventId) {
        return eventRepository.findById(eventId).get();
    }

    @GetMapping("/events")
    public List<Event> getEvents(HttpServletRequest request, String zip) {
        return eventRepository.findByZip(zip);
    }

    //ROOM endpoints
    @GetMapping("/room")
    public Room getRoom(HttpServletRequest request, Integer roomId) {
        return roomRepository.findById(roomId).get();
    }

    @GetMapping("/room/business")
    public List<Room> getRoomsForBusiness(HttpServletRequest request, Integer businessId) {
        Optional<Business> business = businessRepository.findById(businessId);

        if (business.isPresent()) {
            return business.get().getRooms();
        } else {
            return null;
        }
    }
}