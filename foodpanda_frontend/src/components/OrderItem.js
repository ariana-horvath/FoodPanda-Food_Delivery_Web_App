import React from 'react'
import "./../css/OrderItem.css"; 

const OrderItem = ({order}) => {
    var pending = false
    var status = "PENDING"
    var declined = false
    var delivered = false
    
    if (order.status === "PENDING")
        pending = true
    
    if(order.status === "DECLINED")
        declined = true

    if(order.status === "DELIVERED")
        delivered = true

    const onChangeStatusClick = async(e) => {
        e.preventDefault()     
        if (order.status === "ACCEPTED") {
            status = "IN DELIVERY"
            changeStatus()
        }
        else {
            if (order.status === "IN DELIVERY") {
                status = "DELIVERED"
                delivered = true
                changeStatus()
            }
        }
    }

    const onAcceptClick = async(e) => {
        e.preventDefault()
        status = "ACCEPTED"
        changeStatus()
    }

    const onDeclineClick = async(e) => {
        e.preventDefault()     
        status = "DECLINED"
        changeStatus()
    }

    const changeStatus = async(e) => {
        // e.preventDefault()     
        console.log(status)
        const response = await fetch(`http://localhost:8080/foodpanda/orders/${order.orderId}/${status}`, {
            method: "PUT",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(order)
        })
        window.location.reload(false);
    }
 
    return (
        <ul className='order'>
            <li className='order-component'>
                <div>
                    <h3>{"Order id: " + order.orderId}</h3>
                    <p>{"Customer: " + order.customer}</p>
                    <p>{order.date + " " + order.time}</p>
                </div>
            </li>
            <li className='order-component'>
                <div>
                    {order.foods.map((food) => (
                        food.name + ", "   
                    ))}
                </div>
            </li>
            <li className='order-component'>
                <h3 className='status-component'>{order.price + " RON"} </h3>
            </li>
            <li className='order-component'>
                <div>
                    <h3 className='status-component'>{order.status} </h3>
                </div>
            </li>
            <li className='order-component'>
                {pending &&
                <div>
                    <button 
                        className='btn-component-order'
                        onClick={(e) => onAcceptClick(e)}
                    >
                        Accept
                    </button>
                    <button 
                        className='btn-component-order'
                        onClick={(e) => onDeclineClick(e)}
                    >
                        Decline
                    </button>
                </div> 
                }
                {(!pending && !declined && !delivered) &&
                    <button 
                        className='btn-component-order'
                        onClick={(e) => onChangeStatusClick(e)}
                    >
                        Change Status
                    </button>
                }
            </li>
        </ul>
    )
}

export default OrderItem