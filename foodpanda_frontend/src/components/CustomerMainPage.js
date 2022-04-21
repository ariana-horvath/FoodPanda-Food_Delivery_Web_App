import React from 'react'
import "./../css/CustomerMainPage.css"; 
import HeaderCustomer from "./HeaderCustomer";
import { useState, useEffect } from 'react'
import {useLocation} from "react-router-dom"
import Restaurant from './Restaurant';
import Popup from "./Popup";

const CustomerMainPage = () => {
    const {state} = useLocation()
    const customerName = state.customerName
    const [restaurants, setRestaurants] = useState([])
    const [restaurantsCopy, setRestaurantsCopy] = useState([])
    const [restaurantName, setRestaurantName] = useState("")
    const [popup, setPopup] = useState({active: false, title: "", message: ""});
    
    const fetchRestaurants = async () => {
        const response = await fetch(`http://localhost:8080/foodpanda/restaurants`)
        const data = await response.json()
        return data
    }

    useEffect(() => {
        const getRestaurants = async () => {
            const restaurantsResponse = await fetchRestaurants()
            setRestaurants(restaurantsResponse)
            setRestaurantsCopy(restaurantsResponse)
        }
    
        getRestaurants()
    }, [])

    const onSearchClick = async(e) => {
        e.preventDefault()     
        const filteredRestaurants = restaurants.filter(restaurant => restaurant.name.toLowerCase().includes(restaurantName.toLocaleLowerCase()))
        setRestaurantsCopy(filteredRestaurants)
    }

    return (
        <div className='customer-admin-page'>
            <HeaderCustomer customerName={customerName} shoppingCart={null}/>
            <label className='restaurant-title'>Restaurants</label>

            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
            <form class="searchbar" action="action_page.php">
                <button 
                    type="submit"
                    onClick={(e) => onSearchClick(e)}
                ><i class="fa fa-search"></i></button>
                <input 
                    type="text" 
                    placeholder="Search restaurant..." 
                    name="search"
                    value={restaurantName}
                    onChangeCapture={(e) => setRestaurantName(e.target.value)}
                />    
            </form>

            <div className = 'container'>
                {restaurantsCopy.map((restaurant) => (
                    <Restaurant 
                        restaurant={restaurant}
                        customerName={customerName}
                    />
                ))}
            </div>

            <Popup 
                trigger={popup.active}
                setPopup={setPopup}
                title={popup.title}
                message={popup.message}
            />
        </div>
    )
}

export default CustomerMainPage