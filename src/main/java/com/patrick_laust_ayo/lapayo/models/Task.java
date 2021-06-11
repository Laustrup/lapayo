package com.patrick_laust_ayo.lapayo.models;

import java.util.ArrayList;

//Authors Ayo,Patrick and Laust
public class Task extends com.patrick_laust_ayo.lapayo.models.Assignment {

    private double estimatedWorkHours;
    private ArrayList<com.patrick_laust_ayo.lapayo.models.Participant> participants;

    public Task(double estimatedWorkHours,ArrayList<Participant> participants) {
        super(null,null,null,false,null);
        this.estimatedWorkHours = estimatedWorkHours;
        this.participants = participants;
    }

    public Task(double estimatedWorkHours,ArrayList<Participant> participants,
                String start,String end,String title,boolean isCompleted) {
        super(start,end,title,isCompleted,null);
        this.estimatedWorkHours = estimatedWorkHours;
        this.participants = participants;
    }

    public double getEstimatedWorkHours() {
        return estimatedWorkHours;
    }

    public void setEstimatedWorkHours(double estimatedWorkHours) {
        this.estimatedWorkHours = estimatedWorkHours;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants){
        this.participants = participants;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public double totalCost() {
        return participants.size() * estimatedWorkHours * 37.5;
    }
}
