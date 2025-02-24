package com.api.model;

public class BookingDTO
{
    public int bookingid;
    public BookingDetailsDTO booking;

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public BookingDetailsDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDetailsDTO booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}