package com.wfc.cxf.server;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wfc.cxf.common.Json;
import com.wfc.cxf.core.dto.ShipmentDTO;

@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public interface IShipmentApi {

    @POST
    @Path("/load")
    public Json getShipment(ShipmentDTO shipmentDto);

    @GET
    @Path("/load")
    public Json getShipments();

    @DELETE
    @Path("/del/{id}")
    public Json delShipment(@PathParam(value = "id") Long id);

    @POST
    @Path("/add")
    public Json addShipment(ShipmentDTO shipmentDto);

    @PUT
    @Path("/update")
    public Json updateShipment(ShipmentDTO shipmentDto);
    
    @GET
    @Path("/test")
    Json testShip();

}
