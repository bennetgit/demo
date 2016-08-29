package com.wfc.cxf.core.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wfc.cxf.core.domain.Shipment;
import com.wfc.cxf.core.dto.ShipmentDTO;
import com.wfc.cxf.core.enums.ShipmentEnum;
import com.wfc.cxf.core.exception.CheckShipmentException;
import com.wfc.cxf.core.jpa.ShipmentRepository;

@Service
public class ShipmentService implements IShipmentService {

    private static final Logger LOGG = LoggerFactory.getLogger(ShipmentService.class);

    @Autowired
    private ShipmentRepository shipmentRepositoryJPA;

    @Transactional
    public void addShipment(ShipmentDTO shipmentDto) throws CheckShipmentException {

        String checkMsg = checkParameter(shipmentDto);
        if (checkMsg != null)
            throw new CheckShipmentException(checkMsg);

        try {

            Shipment shipment = new Shipment();
            BeanUtils.copyProperties(shipmentDto, shipment);

            shipment.setCreateTime(new LocalDateTime());
            shipment.setStatus(0);
            shipmentRepositoryJPA.addShipment(shipment);

        } catch (CheckShipmentException ex) {
            LOGG.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    private String checkParameter(ShipmentDTO shipmentDto) {

        String errorMsg = null;

        if (shipmentDto == null) {
            errorMsg = "error ,incoming parameter is null !";
        } else if (shipmentDto.getPickUpLoc() == null) {
            errorMsg = "error ,Pick Up Location is Required";
        } else if (shipmentDto.getPickUpSLA() == null) {
            errorMsg = "error ,Pick Up SLA is Required";
        } else if (shipmentDto.getDelLoc() == null) {
            errorMsg = "error ,Deliver Location is Required";
        } else if (shipmentDto.getDelSLA() == null) {
            errorMsg = "error ,Deliver SLA is Required";
        }
        return errorMsg;
    }

    @Transactional
    public void deleteShipment(Long id) throws CheckShipmentException {
        if (id == null)
            throw new CheckShipmentException("error , incoming parameter is null !");

        try {
            shipmentRepositoryJPA.deleteShipment(id);
        } catch (CheckShipmentException ex) {
            LOGG.error("error," + ex.getMessage(), ex);
            throw new CheckShipmentException("error, when delete shipment id = " + id);
        }

    }

    @Transactional
    public void updateShipment(ShipmentDTO shipmentDto) throws CheckShipmentException {

        // Shipment Status contain Created(0), Released(1), Dispatched(2),
        // Pickup(3), Delivered(4) and Closed(5)
        if (shipmentDto == null || shipmentDto.getId() == null || shipmentDto.getStatus() == null)
            throw new CheckShipmentException("error , shipment or shiment.id or shipment.status is null !");

        Shipment shipment = shipmentRepositoryJPA.findShipmentById(shipmentDto.getId());

        if (shipment == null)
            return;
        if (!isCorrectStatus(shipment, shipmentDto)) {
            throw new CheckShipmentException(MessageFormat.format("Current status is {0} , cannot  be changed to {1}",
                    ShipmentEnum.getShipmentEnum(shipment.getStatus()).getStatus(),
                    ShipmentEnum.getShipmentEnum(shipmentDto.getStatus()).getStatus()));

        }
        try {
            shipment.setStatus(shipmentDto.getStatus());
            shipmentRepositoryJPA.updateShipment(shipment);
        } catch (Exception e) {
            LOGG.error("error," + e.getMessage(), e);
            throw new CheckShipmentException(e);
        }

    }

    private boolean isCorrectStatus(Shipment shipment, ShipmentDTO shipmentDto) {

        Integer sourceStatus = shipment.getStatus();
        Integer targetStatus = shipmentDto.getStatus();

        boolean flag = true;
        switch (targetStatus) {
        case 1:
            if (sourceStatus != 0)
                flag = false;
            break;
        case 2:
            if (sourceStatus != 1)
                flag = false;
            break;
        case 3:
            if (sourceStatus != 2) {
                flag = false;
            } else {
                shipment.setPickUpTime(new LocalDateTime());
            }
            break;
        case 4:
            if (sourceStatus != 3)
                flag = false;
            break;
        case 5:
            if (sourceStatus != 4) {
                flag = false;
            } else {
                shipment.setDelTime(new LocalDateTime());
            }
            break;
        default:
            flag = false;
        }

        return flag;
    }

    @Transactional(value = "transactionManagerRead", readOnly = true)
    public List<ShipmentDTO> loadShipments() {

        List<ShipmentDTO> sDtos = new ArrayList<ShipmentDTO>();
        List<Shipment> ss = shipmentRepositoryJPA.loadShipments();

        if (ss == null) {
            return sDtos;
        }

        for (Shipment s : ss) {
            ShipmentDTO sDto = new ShipmentDTO();
            BeanUtils.copyProperties(s, sDto);
            sDtos.add(sDto);
        }

        return sDtos;
    }

    @Transactional(value = "transactionManagerRead", readOnly = true)
    public ShipmentDTO findShipmentById(Long id) throws CheckShipmentException {

        Shipment shipment = shipmentRepositoryJPA.findShipmentById(id);
        ShipmentDTO sDto = new ShipmentDTO();
        if (shipment == null) {
            return sDto;
        }

        BeanUtils.copyProperties(shipment, sDto, new String[] { "createTimeStr", "pickUpTimeStr", "pickUpSLAStr",
                "delTimeStr", "delSLAStr" });
        return sDto;
    }

}
