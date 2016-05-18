package com.flextronics.cn.util;

import java.util.ArrayList;

public class PositionConstants {
	//开始结束为一点
	private Point p;
	public ArrayList<Point> errorPoints = new ArrayList<Point>();
	public ArrayList<Point> correctPoints = new ArrayList<Point>();

	public Point getPointsByTraNo(int traNo){
    	switch (traNo) {
		case 1:
			p = new Point(85,143);
			break;

		default:
			break;
		}
    	return p;
    }
    
    public void clearErrorPoints(){
    	errorPoints = new ArrayList<Point>();
    }
    public void clearCorrectPoints(){
    	correctPoints = new ArrayList<Point>();
    }
}
