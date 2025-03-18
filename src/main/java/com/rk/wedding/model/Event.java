package com.rk.wedding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
	
	private String name;
	private String venue;
	private String date;
	private String time;
	private String mapLocation;

}
