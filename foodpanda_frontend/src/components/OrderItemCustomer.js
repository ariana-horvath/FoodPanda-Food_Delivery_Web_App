import React from 'react'
import "./../css/OrderItemCustomer.css"; 

const OrderItemCustomer = ({order}) => {
    return (
        <ul className='order-customer'>
            <li className='order-component-customer'>
                <div>
                    <h3>{"Order id: " + order.orderId}</h3>
                    <p>{"Restaurant: " + order.restaurant}</p>
                    <p>{order.date + " " + order.time}</p>
                </div>
            </li>
            <li className='order-component-customer'>
                <div>
                    {order.foods.map((food) => (
                        food.name + ", "   
                    ))}
                </div>
            </li>
            <li className='order-component-customer'>
                <h3 className='status-component-customer'>{order.price + " RON"} </h3>
            </li>
            <li className='order-component-customer'>
                <div>
                    <h3 className='status-component-customer'>{order.status} </h3>
                </div>
            </li>
        </ul>
    )
}

export default OrderItemCustomer