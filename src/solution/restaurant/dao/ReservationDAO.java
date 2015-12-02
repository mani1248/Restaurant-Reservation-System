package solution.restaurant.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import solution.restaurant.exceptions.AppException;
import solution.restaurant.model.Reservation;
import solution.restaurant.util.DBUtils;

public class ReservationDAO {
	
	public List<Reservation> fetchAll() throws AppException{
		
		List<Reservation> reservation_List = new ArrayList<Reservation>();
		
		Connection conn = DBUtils.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("SELECT * FROM reservation_system.table_reservations");
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				Reservation resv = new Reservation();
				resv.setId_reservation(rs.getInt("ID_Reservations"));
				resv.setName(rs.getString("Name"));
				resv.setEmail(rs.getString("Email"));
				resv.setPhone(rs.getString("Phone"));
				resv.setDate(rs.getDate("Date"));
				resv.setTime(rs.getTime("Time"));
				resv.setSize(rs.getInt("Size"));
				resv.setStatus(rs.getBoolean("Status"));
				resv.setTable_number(rs.getInt("Table_Number"));
				resv.setTable_size(rs.getInt("Table_Size"));
			
				reservation_List.add(resv);
			}				
		} catch (SQLException e) {
			e.printStackTrace();
				throw new AppException(e.getMessage(), e.getCause());
		} finally{
			
			DBUtils.closeResources(ps, rs, conn);
		}
		return reservation_List;		
	}
	
//public Reservation fetchOne(int empID) throws AppException{
//		
//		Reservation emp = null;
//		
//		Connection conn = DBUtils.getConn();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		try {
//			ps = conn.prepareStatement("SELECT * FROM employee WHERE ID=?");
//			ps.setInt(1, empID);			
//			rs = ps.executeQuery();
//			
//			if(rs.next()){
//				emp = new Reservation();
//				emp.setId_reservation(rs.getInt("ID"));
//				emp.setName(rs.getString("FIRST_NAME"));
//				emp.setEmail(rs.getString("EMAIL"));
//				emp.setPhone(rs.getString("PHONE"));
//				emp.setDate(rs.getDate("DATE"));
//				emp.setTime(rs.getDate("TIME"));
//				emp.setSize(rs.getInt("SIZE"));
//			
//			}	
//		} catch (SQLException e) {
//			e.printStackTrace();
//				throw new AppException(e.getMessage(), e.getCause());
//		} finally{
//			
//			DBUtils.closeResources(ps, rs, conn);
//		}
//		return emp;		
//	}

public Reservation create(Reservation resv) throws AppException {

	Connection conn = DBUtils.getConn();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
		ps = conn.prepareStatement("INSERT INTO reservation_system.reservations_history(Name, Email, Phone, Date, Time, Size, Status) VALUES (?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, resv.getName());
		ps.setString(2, resv.getEmail());
		ps.setString(3, resv.getPhone());
		ps.setDate(4, (Date) resv.getDate());
		ps.setTime(5, (Time) resv.getTime());
		ps.setInt(6, resv.getSize());
		ps.setBoolean(7, resv.isStatus());
		
		ps.executeUpdate();
		rs = ps.getGeneratedKeys();
		
		if(rs.next()){
			resv.setId_reservation(rs.getInt("ID_Reservations"));
		}	
	} catch (SQLException e) {
		e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
	} 
	
	try {
		
		ps = conn.prepareStatement("UPDATE INTO reservation_system.table_reservations(ID_Reservations, Name, Email, Phone, Date, Time, Size, Status) VALUES (?,?,?,?,?,?,?,?) WHERE Table_Number = ?", PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, resv.getId_reservation());
		ps.setString(2, resv.getName());
		ps.setString(3, resv.getEmail());
		ps.setString(4, resv.getPhone());
		ps.setDate(5, (Date) resv.getDate());
		ps.setTime(6, (Time) resv.getTime());
		ps.setInt(7, resv.getSize());
		ps.setBoolean(8, resv.isStatus());
		//get table number
		ps.setInt(9, resv.getTable_number_to_assign(resv));
		
		ps.executeUpdate();
		rs = ps.getGeneratedKeys();
		
		if(rs.next()){
			resv.setId_reservation(rs.getInt("ID_Reservations"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw new AppException(e.getMessage(), e.getCause());
} 
	
	
	finally{
		
		DBUtils.closeResources(ps, rs, conn);
	}
	return resv;	
}

public Reservation update(int resvID) throws AppException{

	Connection conn = DBUtils.getConn();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Reservation resv = null;
	
	try {
		resv = new Reservation();
		ps = conn.prepareStatement("UPDATE INTO reservation_system.table_reservations(Name, Email, Phone, Date, Time, Size, Status) VALUES (?,?,?,?,?,?,?) WHERE ID_Reservations = ?", PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, resv.getName());
		ps.setString(2, resv.getEmail());
		ps.setString(3, resv.getPhone());
		ps.setDate(4, (Date) resv.getDate());
		ps.setTime(5, (Time) resv.getTime());
		ps.setInt(6, resv.getSize());
		ps.setBoolean(7, resv.isStatus());
		ps.setInt(8, resv.getId_reservation());
		
		ps.executeUpdate();
		rs = ps.getGeneratedKeys();
		
		if(rs.next()){
			resv.setId_reservation(rs.getInt("ID_Reservations"));
		}	
		
	} catch(SQLException e){
		e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
	} finally{

	DBUtils.closeResources(ps, rs, conn);
	}
	return resv;
}


@SuppressWarnings("null")
public int delete(int ID_resv) throws AppException{
	
	Connection conn = DBUtils.getConn();
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
		ps = conn.prepareStatement("UPDATE INTO reservation_system.table_reservations(ID_Reservations, Name, Email, Phone, Date, Time, Size, Status) VALUES (?,?,?,?,?,?,?) WHERE ID_Reservations = ?", PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setInt(1, (Integer) null);
		ps.setString(2, null);
		ps.setString(3, null);
		ps.setString(4, null);
		ps.setDate(5, null);
		ps.setTime(6, null);
		ps.setInt(7, (Integer) null);
		ps.setBoolean(8, false);
		
		ps.executeUpdate();
		rs = ps.getGeneratedKeys();
	
		
	} catch(SQLException e){
		e.printStackTrace();
			throw new AppException(e.getMessage(), e.getCause());
	} finally{

	DBUtils.closeResources(ps, rs, conn);
	}
	
	return ID_resv;
}
}
