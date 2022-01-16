package com.example.project2;

public class bookedRoom {
    private String id;
    private String dateIn ;
    private String dateOut;
    private String cardnum;
    private String cvcCode;
    private String userName;
    private String idRoom;

    public bookedRoom(String id, String dateIn, String dateOut, String cardnum, String cvcCode, String userName, String idRoom) {
        this.id = id;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.cardnum = cardnum;
        this.cvcCode = cvcCode;
        this.userName = userName;
        this.idRoom = idRoom;
    }

    public String getId() {
        return id;
    }

    public String getDateIn() {
        return dateIn;
    }

    public String getDateOut() {
        return dateOut;
    }

    public String getCardnum() {
        return cardnum;
    }

    public String getCvcCode() {
        return cvcCode;
    }

    public String getUserName() {
        return userName;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public void setCvcCode(String cvcCode) {
        this.cvcCode = cvcCode;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }
}
