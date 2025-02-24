package com.api.model;

public class BookingDetailsDTO
{
    public int bookingid;
    public int roomid;
    public String firstname;
    public String lastname;
    public boolean depositpaid;
    public BookingDatesDTO bookingdates;

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDatesDTO getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDatesDTO bookingdates) {
        this.bookingdates = bookingdates;
    }
}