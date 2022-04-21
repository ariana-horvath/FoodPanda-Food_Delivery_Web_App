import React from 'react'
import "./../css/OrdersAdminPage.css"; 
import HeaderAdmin from "./HeaderAdmin";
import { useState, useEffect } from 'react'
import {useLocation, useNavigate} from "react-router-dom"
import OrderItem from './OrderItem';

const OrdersAdminPage = () => {
    const {state} = useLocation()
    const admin = state.admin
    const restaurant = state.restaurant
    const [orders, setOrders] = useState([])
    const [ordersCopy, setOrdersCopy] = useState([])
    const [status, setStatus] = useState("")

    const fetchOrders = async () => {
        const response = await fetch(`http://localhost:8080/foodpanda/orders-restaurant/${restaurant}`)
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

    const onSearchClick = async(e) => {
        e.preventDefault()     
        const filteredOrders = orders.filter(order => status.toLocaleLowerCase().includes(order.status.toLowerCase()))
        setOrdersCopy(filteredOrders)
    }

    return(
        <div className="orders-admin-page">
            <HeaderAdmin admin={admin} restaurant={restaurant}/>
            <label className='menu-label'>Orders</label>

            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
            <form class="searchbar2" action="action_page.php">
                <button 
                    type="submit"
                    onClick={(e) => onSearchClick(e)}
                ><i class="fa fa-search"></i></button>
                <input 
                    type="text" 
                    placeholder="Filter by status..." 
                    name="search"
                    value={status}
                    onChangeCapture={(e) => setStatus(e.target.value)}
                />    
            </form>

            <div className = 'container'>
                {ordersCopy.map((order) => (
                    <OrderItem 
                        order={order}
                    />
                ))}
            </div>
        </div>
    )
}

export default OrdersAdminPage