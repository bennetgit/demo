package com.wfc.cxf.server.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wfc.cxf.common.Json;
import com.wfc.cxf.core.dto.ShipmentDTO;
import com.wfc.cxf.core.json.ShipmentJson;
import com.wfc.cxf.core.service.IShipmentService;
import com.wfc.cxf.server.IShipmentApi;

@Component("shipmentApi")
public class ShipmentApi implements IShipmentApi {

    @Autowired
    private IShipmentService shipmentService;

    private final static Logger LOGG = LoggerFactory.getLogger(ShipmentApi.class);

    public Json getShipment(ShipmentDTO shipmentDto) {
        Json json = new ShipmentJson();

        if (shipmentDto == null) {
            json.setFlag(false);
            return json;
        }
        ShipmentDTO sDto = shipmentService.findShipmentById(shipmentDto.getId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shipment", sDto);
        json.setData(map);

        return json;
    }

    public Json getShipments() {

    	/**cxf重定向
    	 * 
    	    Message message = PhaseInterceptorChain.getCurrentMessage();
        	HttpServletResponse re = (HttpServletResponse) message
         	.get(AbstractHTTPDestination.HTTP_RESPONSE);	
        	try {
				re.sendRedirect("http://localhost:8080/_demo/test");
				return null;
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
    	 */
    	
    	
        List<ShipmentDTO> sDtos = shipmentService.loadShipments();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("shipments", sDtos);
        return new ShipmentJson(true, "success", map);
    }

    public Json delShipment(Long id) {

        Json json = new ShipmentJson();

        try {
            shipmentService.deleteShipment(id);
            json.setMsg("success");
        } catch (Exception ex) {
            json.setFlag(false);
            json.setMsg(ex.getMessage());
            LOGG.error(ex.getMessage(), ex);
        }

        return json;
    }

    public Json addShipment(ShipmentDTO shipmentDto) {

        Json json = new ShipmentJson();

        if (shipmentDto == null) {
            json.setFlag(false);
            json.setMsg("fail , incoming parameter is null");
            return json;
        }

        try {
            shipmentService.addShipment(shipmentDto);
            json.setMsg("success");
        } catch (Exception ex) {
            json.setFlag(false);
            json.setMsg(ex.getMessage());
            LOGG.error(ex.getMessage(), ex);
        }

        return json;
    }

    public Json updateShipment(ShipmentDTO shipmentDto) {

        Json json = new ShipmentJson();

        if (shipmentDto == null) {
            json.setFlag(false);
            json.setMsg("fail , incoming parameter is null");
            return json;
        }

        try {
            shipmentService.updateShipment(shipmentDto);
            json.setMsg("success");
        } catch (Exception ex) {
            json.setFlag(false);
            json.setMsg(ex.getMessage());
            LOGG.error(ex.getMessage(), ex);
        }

        return json;
    }

	@Override
	public Json testShip() {
		Json json = new ShipmentJson();

		json.setFlag(false);
		json.setMsg("fail , incoming parameter is null");
		return json;
	}

}
