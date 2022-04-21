import React from 'react'
import "./../css/OrdersCustomerPage.css"; 
import HeaderCustomer from "./HeaderCustomer";
import { useState, useEffect } from 'react'
import {useLocation, useNavigate} from "react-router-dom"
import OrderItemCustomer from './OrderItemCustomer';

const OrdersCustomerPage = () => {
    const {state} = useLocation()
    const shoppingCart = state.shoppingCart
    const customerName = state.customerName
    const [orders, setOrders] = useState([])
    const [ordersCopy, setOrdersCopy] = useState([])

    const fetchOrders = async () => {
        const response = await fetch(`http://localhost:8080/foodpanda/orders-customer/${customerName}`)
        const data = await response.json()
        return data
    }

    useEffect(() => {
        const getOrders = async () => {
            const ordersResponse = await fetchOrders()
            setOrders(ordersResponse)
            setOrdersCopy(ordersResponse)
        }
    
        getOrders()
    }, [])

    return(
        <div className="orders-customer-page">
            <HeaderCustomer customerName={customerName} shoppingCart={shoppingCart}/>
            <label className='order-customer-label'>My Orders</label>

            <div className = 'container'>
                {ordersCopy.map((order) => (
                    <OrderItemCustomer 
                        order={order}
                    />
                ))}
            </div>
        </div>
    )
}

export default OrdersCustomerPage