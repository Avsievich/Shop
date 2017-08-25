package ru.boostbrain.ejb;

import ru.boostbrain.domain.Order;
import ru.boostbrain.domain.Thing;
import ru.boostbrain.domain.ThingInOrder;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class OrdersManagerBean {
    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public Order createOrder() {
        Order order = new Order();
        entityManager.persist(order);
        return order;
    }

    public boolean addToOrder(long thingId, long orderId, int guantity) {
        Thing thing = entityManager.find(Thing.class, thingId);
        if (thing == null) {
            return false;
        }
        Order order = entityManager.find(Order.class, orderId);
        if (order == null) {
            return false;
        }
        ThingInOrder thingInOrder = new ThingInOrder();
        thingInOrder.setOrder(order);
        thingInOrder.setThing(thing);
        thingInOrder.setQuantity(guantity);
        entityManager.persist(thingInOrder);
        return true;

    }
}
