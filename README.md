# GrofersDeliveryAssignment

### Problem statement
You are running an e-commerce business like Grofers. You have received n orders for the day.
You have to deliver these n orders to the customers in 4 delivery slots ( 6-9, 9-13,16-19,19-23
hours). There are delivery partners who deliver these orders. Each delivery partner has their
own vehicle and each vehicle has a carrying capacity i.e total weight of the orders which is
dependent on the vehicle type (bike, scooter, or truck).

Each slot can take orders with an overall max weight of 100 kg. There are no trucks available in
the morning slot (6-9 hours) and there are no scooters and bikes available in the evening slot
(19-23 hours). All vehicle types are available for the other two slots (9-13,16-19 hours). You
can’t order more than 1 truck, 3 bikes, 2 scooters a day. Also, the vehicles’ capacity should be
used optimally.

### Constraints

Bike capacity - 30 kg per trip

Scooter capacity - 50 kg per trip

Truck capacity - 100 kg per trip

slot capacity - 100 Kg 

maximum 4 slots allowed

### Approach

Though this problem can be solved using many approaches each of them have a trade off 
you can either assign the orders then reshuffle each time you get a new order for calculating new optimal state.
or one can use greedy strategy to assigned on First Come First Serve basis.

I have used the greedy strategy

#### Assumptions:
1. orders will be received in batches preferred slot can be given if not then time at which request is received is used for finding slot number
1. We cannot split the order into sub orders i.e if an order is 100Kg we cannot split it on 2 scooter 50Kg each
1. We can leave any orders which we cannot assign in un assigned state
1. For simplicity, we can roll over the slots is unavailable, but we won't roll over the day for orders    
1. At the time of receiving the order if a customer wants slot 1, and he/she can get slot 1 prioritize the slot for them.


We maintain available partners awaiting orders, this awaiting partners list is sorted on lower to higher capacity
whenever we receive orders we sort the orders on weight.
Try to assign the highest weight first. and keep repeating until all the orders are assigned a partner.


### Entities

There are following entities

Order (stores order data)
- id (order id)
- order_weight

DeliveryVehicles (stores types of delivery vehicle and related data)
- id (table primary key)
- vehicle_type (bike, scooter and truck)
- capacity (capacity on vehicle in Kg)
- daily_count (number of vehicle available to us for a day)

DeliveryPartner ( partner who owns the vehicles )

- name (name of partner)
- vehicle_type_id (reference to DeliveryVehicles entity for type of vehicle)
- id (table primary key)

Slot
slot information

- id (table primary key as well as slot number)
- start_time (start time of slot in HH:MM:SS)
- end_time (end time of slot in HH:MM:SS)

VehicleSlot
entity tells us which vehicles are available in given slot

- vehicle_type_id (reference to delivery vehicle table for vehicle type)
- slot_id (delivery slot primary key)

OrderDelivery

- order_id (refer to order id from order table)
- slot_id (refer to slot assigned to order)
- delivery_partner_id (which delivery partner is assigned to this order)

### Below in the entity diagram for better understanding
![alt](https://i.imgur.com/OhLXfHQ.png)

### App is hosted on heroku https://delivery-assignment.herokuapp.com/
### API documentation -> https://delivery-assignment.herokuapp.com/swagger-ui/#/