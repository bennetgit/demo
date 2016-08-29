package com.wfc.cxf.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wfc.cxf.core.dto.ShipmentDTO;
import com.wfc.cxf.core.service.IShipmentService;

/**
 * Logger
 * 
 * @author fcw
 *
 */
@Component
public class LoggerAop {

    @Autowired
    private IShipmentService service;

    public void doBefore(JoinPoint proJp) {

        Object[] args = proJp.getArgs();
        for (Object o : args) {
            if (o instanceof ShipmentDTO) {
                ShipmentDTO shipment = (ShipmentDTO) o;
                ShipmentDTO reults = service.findShipmentById(shipment.getId());
                System.out.println(reults);
            }
        }

        System.out.println(Arrays.toString(args));
    }

}
