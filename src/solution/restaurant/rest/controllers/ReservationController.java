package solution.restaurant.rest.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import io.swagger.annotations.Api;
import io.swagger.annotations.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import solution.restaurant.dao.ReservationDAO;
import solution.restaurant.exceptions.AppException;
import solution.restaurant.model.Reservation;

@Path("/reservations")
@Api(tags = {"/reservations"})
public class ReservationController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(
				value = "List all reservations",
				notes = "List all current reservations"
			)
	@ApiResponses(
				value = {
						@ApiResponse (code = 200, message = "success"),
						@ApiResponse (code = 500, message = "Internal Server Error")
				}
			)
	public List<Reservation> findAll(){
		 		
		try {
			ReservationDAO dao = new ReservationDAO();
			return dao.fetchAll();
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@GET
//	@Path("/{id}") 
//	@Produces(MediaType.APPLICATION_JSON)
//	@ApiOperation(
//			value = "Find One employee",
//			notes = "Find one employees from db"
//		)
//	@ApiResponses(
//			value = {
//					@ApiResponse (code = 200, message = "Success"),
//					@ApiResponse (code = 404, message = "No Found"),
//					@ApiResponse (code = 500, message = "Internal Server Error")
//			}
//		)
//	public Reservation findOne(@PathParam("id") int empID){
//			
//		try {
//			ReservationDAO dao = new ReservationDAO();
//			Reservation emp = dao.fetchOne(empID);
//			if(emp == null){
//				throw new WebApplicationException(Status.NOT_FOUND);
//			}else {
//				return emp;
//			}
//		} catch (AppException e) {
//			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Reservation create(Reservation resv){
		
		try {
			ReservationDAO dao = new ReservationDAO();
			return dao.create(resv);
		}				
		catch (AppException e){
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);			
		}
	}

	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Reservation update(@PathParam("id") int ID_resv, Reservation resv){
		
		try {
			ReservationDAO dao = new ReservationDAO();
			return dao.update(ID_resv);
		}				
		catch (AppException e){
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@DELETE
	@Path("/{id}")
	public int delete(@PathParam("id") int ID_resv){
		
		try {
			ReservationDAO dao = new ReservationDAO();
			return dao.delete(ID_resv);
		}				
		catch (AppException e){
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);			
		}
	}
	
}
