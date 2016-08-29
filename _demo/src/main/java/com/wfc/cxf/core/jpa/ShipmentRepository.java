package com.wfc.cxf.core.jpa;

import java.util.List;

import com.wfc.cxf.core.domain.Shipment;



public interface ShipmentRepository {

	void addShipment( Shipment  shipment );
	
	void deleteShipment(Long id);
	
	void updateShipment(Shipment  shipment);
	
	List<Shipment> loadShipments();
	
	Shipment findShipmentById(Long id);
}
