package com.calendar;

import java.util.ArrayList;
import java.util.Date;

public class Event {

	private String eventDate, startTime, endTime, description;
	
	public static ArrayList<String> events=new ArrayList<String>();
	private ArrayList<Event> localEvents = new ArrayList<Event>();
	public Event(){
		
	}
	public Event(String s){
		new Event(s,"","","");
	}
	public Event(String d, String start, String end, String comments){
		eventDate=d;
		startTime=start;
		endTime=end;
		description=comments;
	}
	
	public String getDate(){
		return eventDate;
	}
	
	public String getStartTime(){
		return startTime;
	}
	
	public String getEndTime(){
		return endTime;
	}
	
	public ArrayList<String> getEvents(){
		return events;
	}
	
	public void addLocalEvent(Event e){
		localEvents.add(e);
	}
	
	public void addImageEvent(String s){
		events.add(s);
	}
	
	public ArrayList<String> getArray(){
		return events;
	}
	
	//public String toString(){
		//return eventDate + " " + startTime + " " + endTime + " " + description;
	//}
}
