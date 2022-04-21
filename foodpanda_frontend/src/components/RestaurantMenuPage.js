import React from 'react'
import "./../css/RestaurantMenuPage.css"; 
import HeaderCustomer from "./HeaderCustomer";
import { useState, useEffect } from 'react'
import {useLocation} from "react-router-dom"
import MenuItem2 from './MenuItem2';

const RestaurantMenuPage = () => {
    const {state} = useLocation()
    const customerName = state.customerName
    const restaurant = state.restaurant
    const [menuItems, setMenuItems] = useState([])
    
    var price = 0.0
    var myFoods = []

    var shoppingCart = {
        customer:customerName,
        restaurant:restaurant.name,
        foods:myFoods,
        price: price,
        date: null,
        time: null,
        status: null,
    }

    const fetchMenuItems = async () => {
        const response = await fetch(`http://localhost:8080/foodpanda/foods/${restaurant.name}`)
        const data = await response.json()
        return data
    }

    useEffect(() => {
        const getMenuItems = async () => {
            const menuItemsResponse = await fetchMenuItems()
            setMenuItems(menuItemsResponse)
        }
    
        getMenuItems()
    }, [])

    console.log(shoppingCart)

    return (
        <div className="main-admin-page">
            <HeaderCustomer customerName={customerName} shoppingCart={shoppingCart}/>
            <label className='restaurant-title'>{restaurant.name}</label>
            <label className='menu-label'>Menu</label>

            <div className = 'container'>
                {menuItems.map((menuItem) => (
                    <MenuItem2 
                        menuItem={menuItem}
                        shoppingCart={shoppingCart}
                    />
                ))}
            </div>
        </div>
    )
}

export default RestaurantMenuPage