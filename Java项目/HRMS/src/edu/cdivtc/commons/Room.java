package edu.cdivtc.commons;

public class Room {
    private String RoomNo;
    private String RoomType;
    private double Floor;
    private int Status ;
    private String Price;
    private String bedType;
    private  String MaxGuest;
    private  String Facilities;

    public String getRoomNo() {
        return RoomNo;
    }

    public void setRoomNo(String roomNo) {
        RoomNo = roomNo;
    }

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String roomType) {
        RoomType = roomType;
    }

    public double getFloor() {
        return Floor;
    }

    public void setFloor(double floor) {
        Floor = floor;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getMaxGuest() {
        return MaxGuest;
    }

    public void setMaxGuest(String maxGuest) {
        MaxGuest = maxGuest;
    }

    public String getFacilities() {
        return Facilities;
    }

    public void setFacilities(String facilities) {
        Facilities = facilities;
    }
}
