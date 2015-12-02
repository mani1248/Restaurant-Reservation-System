package solution.restaurant.model;

import java.util.Date;

import services.AutoAssign;

public class Reservation {
	
	private int id_reservation;
	private String name;
	private String email;	
	private String phone;
	private Date date;
	private Date time;
	private int size;
	private boolean status;
	private int table_number;
	private int table_size;
	
	public int getId_reservation() {
		return id_reservation;
	}
	public void setId_reservation(int id_reservation) {
		this.id_reservation = id_reservation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getTable_number() {
		return table_number;
	}
	public void setTable_number(int table_number) {
		this.table_number = table_number;
	}
	public int getTable_size() {
		return table_size;
	}
	public void setTable_size(int table_size) {
		this.table_size = table_size;
	}
	public int getTable_number_to_assign(Reservation resv) {
		table_number = AutoAssign.assignTable(resv);
		return table_number;
	}
	
}
