package com.wfc.cxf.core.service;

import java.util.List;

import com.wfc.cxf.core.dto.ShipmentDTO;
import com.wfc.cxf.core.exception.CheckShipmentException;



public interface IShipmentService {

	void addShipment( ShipmentDTO  shipment ) throws CheckShipmentException;
	
	void deleteShipment(Long id) throws CheckShipmentException;
	
	void updateShipment(ShipmentDTO  shipment) throws CheckShipmentException;
	
	List<ShipmentDTO> loadShipments() throws CheckShipmentException;
	
	ShipmentDTO findShipmentById(Long id) throws CheckShipmentException;
}
