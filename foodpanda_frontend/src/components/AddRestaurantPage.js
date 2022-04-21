import React from 'react'
import "./../css/AddRestaurantPage.css"; 
import HeaderAdmin from "./HeaderAdmin";
import { useState } from 'react'
import {useLocation, useNavigate} from "react-router-dom"
import DeliveryZone from './DeliveryZone';
import Popup from "./Popup";

const AddRestaurantPage = () => {
    const {state} = useLocation()
    const admin = state.admin
    const zones = state.zones
    const [name, setName] = useState("")
    const [location, setLocation] = useState("")
    const [popup, setPopup] = useState({active: false, title: "", message: ""});
    const [deliveryZonesStrings, setDeliveryZonesStrings] = useState([])
    const navigate = useNavigate()

    const validateInputSignUp = async () => {
        if(!name) {
            setPopup({active: true, title: "Oops!", 
                message: "Name can't be empty"})
            return false
        }
        if(!location) {
            setPopup({active: true, title: "Oops!", 
                message: "Location can't be empty"})
            return false
        }
        return true
    }

    const addRestaurant = async(restaurant) => {
        console.log(restaurant)
        const response = await fetch(`http://localhost:8080/foodpanda/restaurants`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(restaurant)
        })
        
        const data = await response.json()
        if(data.success === true) {
            setPopup({active: true, title: "Yaay!", 
                    message: data.message})
            navigate('/admin/main-page', {
                state: {admin: admin, restaurant: restaurant}
            })       
        }
        else 
            setPopup({active: true, title: "Oops!", 
                    message: data.message})
    }

    const onAddRestaurantClick = async (e) => {
        e.preventDefault()
        
        let notEmpty = await validateInputSignUp()
        if (notEmpty) { 
            var checkboxes = document.querySelectorAll('input[type=checkbox]:checked')
        
            for (var i = 0; i < checkboxes.length; i++) {
                deliveryZonesStrings.push(checkboxes[i].value)
            }
            addRestaurant({"name":name, "location":location, "deliveryZonesStrings":deliveryZonesStrings, "adminUsername":admin.username})
        }    
    }

    return (
        <div className="add-restaurant-page">
            <HeaderAdmin/>
            <div className='add-restaurant-texts'>
                <p>Add the restaurant you will manage:</p>
            </div>
            <form className="add-restaurant-form">
                <div className="restaurant-data">
                    <label className="restaurant-data-label">Name:</label>
                    <input
                        className="restaurant-data-input" 
                        type="text" 
                        placeholder="Name"
                        value={name} 
                        onChange={(e) => setName(e.target.value)} 
                    />
                </div>
                <div className="restaurant-data">
                    <label className="restaurant-data-label">Location:</label>
                    <input
                    className="restaurant-data-input" 
                        type="text" 
                        placeholder="Location"
                        value={location} 
                        onChange={(e) => setLocation(e.target.value)} 
                    />
                </div>

                <div className='zones-list'>
                    <br></br>
                    <label className='restaurant-data-label'>Delivery Zones:</label>
                    <br></br>
                    {zones.map((zone) => (
                        <DeliveryZone
                            zoneName={zone}
                        />
                    ))}
                </div>
                <button 
                    className='btn' 
                    id='btn-add-restaurant' 
                    type='submit'
                    onClick={(e) => onAddRestaurantClick(e)}
                >
                    Add Restaurant        
                </button> 
            </form>
            <Popup 
                trigger={popup.active}
                setPopup={setPopup}
                title={popup.title}
                message={popup.message}
            />
        </div>
    )
}

export default AddRestaurantPage