package ru.boostbrain;

import ru.boostbrain.domain.Order;
import ru.boostbrain.domain.Thing;
import ru.boostbrain.ejb.OrdersManagerBean;
import ru.boostbrain.ejb.ThingManagerBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class OrderBean implements Serializable {
    private Order order;
    private Thing thing;
    private String name;
    private int price;
    private int quantity;

    @EJB
   private OrdersManagerBean ordersManagerBean;
    @EJB
   private ThingManagerBean thingManagerBean;

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void createOrder( ){
        if (order==null){
 order=ordersManagerBean.createOrder();
        }
    }
    public void createThing(){

            thing=thingManagerBean.createThing(name,price);

    }

    public List<Thing> getThings(){
        return thingManagerBean.getThings();
    }

    public void addThing(Thing thing){

        if(order==null){
            return;
        }
        ordersManagerBean.addToOrder(thing.getId(),order.getId(),1);
    }
    public List<Thing> getThingInOrder(){
        if(order==null){
            return Collections.emptyList();
        }
      return   ordersManagerBean.getThingsInOrder(order.getId());
    }

}
