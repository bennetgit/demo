package com.wfc.cxf.core.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.wfc.cxf.core.domain.Shipment;

@Repository
public class ShipmentRepositoryJPA implements ShipmentRepository, Serializable {

    private static final long serialVersionUID = 6738776270081477248L;

    public final static String LOAD_SHIPMENT = "load.shipment";

    public final static String GET_SHIPMENT = "get.shipment";

    @PersistenceContext(unitName = "persistenceUnitWrite")
    private EntityManager em;

    @PersistenceContext(unitName = "persistenceUnitRead")
    private EntityManager emRead;

    public void addShipment(Shipment shipment) {
        em.persist(shipment);
    }

    public void deleteShipment(Long id) {
        em.remove(em.getReference(Shipment.class, id));
    }

    public void updateShipment(Shipment shipment) {
        em.merge(shipment);
    }

    public List<Shipment> loadShipments() {

        List<Shipment> shipments = new ArrayList<Shipment>();

        TypedQuery<Shipment> query = emRead.createNamedQuery(LOAD_SHIPMENT, Shipment.class);
        shipments = query.getResultList();

        return shipments;
    }

    public Shipment findShipmentById(Long id) {

        Shipment shipment = null;
        if (id == null) {
            return shipment;
        }

        CriteriaBuilder builder = emRead.getCriteriaBuilder();
        CriteriaQuery<Shipment> query = builder.createQuery(Shipment.class);
        Root<Shipment> root = query.from(Shipment.class);
        Predicate where = builder.equal(root.get("id"), id);
        query.where(where);
        TypedQuery<Shipment> typedQuery = emRead.createQuery(query);

        shipment = typedQuery.getSingleResult();

        return shipment;
    }
}
