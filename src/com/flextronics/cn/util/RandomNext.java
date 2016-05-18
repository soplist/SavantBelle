package com.flextronics.cn.util;

public class RandomNext {
	
	private String[] locationCodes;
	private String[] _locationCodes;
	private String location;
	private int index = 0;
	private String lastLocation;
	private int repeatTimes = 0;
	
	/**
	 * 
	 * @param locationCodes must be not null, and it's length must larger than 3
	 */
	public RandomNext(String[] locationCodes) {
		_locationCodes = new String[locationCodes.length];
		for (int i = 0; i < locationCodes.length; i++) {
			_locationCodes[i] = locationCodes[i];
		}
		this.locationCodes = ArrayOperations.redomElements(locationCodes);
		this.locationCodes = ArrayOperations.getRedomElementFromElements(this.locationCodes, this.locationCodes.length);		
	}
	
	/** create next location, it's created randomly
	 * 
	 * @return
	 */
	public String next() {
		
		if (locationCodes.length > 3) {
			location = locationCodes[index];
			index++;
			if (index == locationCodes.length-1) {
				//locationCodes = ArrayOperations.otherElements(_locationCodes, new String[]{location});
				locationCodes = ArrayOperations.redomElements(
						ArrayOperations.otherElements(_locationCodes, new String[]{location}));
				locationCodes = ArrayOperations.redomElements(locationCodes);
				index = 0;
			}
		}else if (locationCodes.length >= 2) {
			
			location = ArrayOperations.getRedomElementFromElements(locationCodes);
			if (location.equals(lastLocation)) {
				repeatTimes ++;
			}
			if (repeatTimes == 2) {
				do {
					locationCodes = ArrayOperations.redomElements(locationCodes);
					location = ArrayOperations.getRedomElementFromElements(locationCodes);
				} while (location.equals(lastLocation));
				repeatTimes = 0;
			}
			
			lastLocation = location;
		}else if (locationCodes.length == 1) {
			location = locationCodes[0];
		}
		
		return location;
	}
}
