package com.example.demo4;

public class Passenger {

    private String id;
    private String name;
    private String destination;
    private String nation;

    public Passenger(String name, String destination, String nation) {
//        this.id = id;
        this.name = name;
        this.destination = destination;
        this.nation = nation;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
