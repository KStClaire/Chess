package Controller;

import java.awt.Point;

import Model.Piece;

public class Behavior {
	
	private boolean isFirstMoveOfGame;
	private boolean isWhitesTurn;
	
	private Point startPoint;
	private Point endPoint;
	private Point p3;
	private Point p4;
	private Piece piece;
	private String str;
	
	public Behavior(Point start, Point end, Point rook, Point castle, Piece piece, String str){
		this.startPoint = start;
		this.endPoint = end;
		this.p3 = rook;
		this.p4 = castle;
		this.piece = piece;
		this.str = str;
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	public Point getP3() {
		return p3;
	}
	public void setP3(Point p3) {
		this.p3 = p3;
	}
	public Point getP4() {
		return p4;
	}
	public void setP4(Point p4) {
		this.p4 = p4;
	}
	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
	public String toString(){
		return str;
	}
	
	
}
