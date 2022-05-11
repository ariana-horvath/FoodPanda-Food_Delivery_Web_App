import React from 'react'
import ReactToPrint from 'react-to-print';
import "./../css/AdminMainPage.css"; 
import HeaderAdmin from "./HeaderAdmin";
import { useState, useEffect } from 'react'
import {useLocation} from "react-router-dom"
import MenuItem from './MenuItem';

const AdminMainPage = () => {
    const {state} = useLocation()
    const admin = state.admin
    const restaurant = state.restaurant
    const [menuItems, setMenuItems] = useState([])
    var componentRef
    
    const fetchMenuItems = async () => {
        const response = await fetch(`http://localhost:8080/foodpanda/foods/${restaurant}`)
        const data = await response.json()
        console.log(data)
        return data
    }

    useEffect(() => {
        const getMenuItems = async () => {
            const menuItemsResponse = await fetchMenuItems()
            setMenuItems(menuItemsResponse)
        }
    
        getMenuItems()
    }, [])

    return (
        <div className="main-admin-page">
            <HeaderAdmin admin={admin} restaurant={restaurant}/>
            <label className='restaurant-title'>{restaurant}</label>
            <label className='menu-label'>Menu</label>
            <div ref={(response) => (componentRef = response)} className = 'container' id='menu'>
                {menuItems.map((menuItem) => (
                    <MenuItem 
                        menuItem={menuItem}
                    />
                ))}
            </div>
            <ReactToPrint
            content={() => componentRef}
            trigger={() => <button className="btn-save-pdf">Export as PDF</button>}
            />
        </div>
    )
}

export default AdminMainPage