import React from 'react'
import "./../css/Cart.css";
import HeaderCustomer from "./HeaderCustomer";
import FoodItem3 from './FoodItem3';
import { useLocation } from 'react-router-dom';
import Popup from "./Popup";
import { useState } from 'react'

const Cart = () => {
    const {state} = useLocation()
    const shoppingCart = state.shoppingCart
    const customerName = state.customerName
    const [popup, setPopup] = useState({active: false, title: "", message: ""});
    console.log(shoppingCart)

    const onPlaceOrderClick = async(e) => {
        const response = await fetch(`http://localhost:8080/foodpanda/orders`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(shoppingCart)
        })
        
        const data = await response.json()
        if(data.success === true) {
            setPopup({active: true, title: "Yaay!", 
                    message: data.message})
        }
        else 
            setPopup({active: true, title: "Oops!", 
                    message: data.message})
    }

    return (
        <div className='cart-page'>
            <HeaderCustomer customerName={customerName} shoppingCart={shoppingCart}/>
            <label className='restaurant-title'>{shoppingCart.restaurant}</label>

            <div className = 'container'>
                {shoppingCart.foods.map((food) => (
                    <FoodItem3 
                        food={food}
                    />
                ))}
            </div>

            <label className='total-label'> {"Total amount: " + shoppingCart.price} </label>
            <button 
                    className='btn-component-cart'
                    onClick={(e) => onPlaceOrderClick(e)}
            >
               Place Order
            </button>
            <Popup 
                trigger={popup.active}
                setPopup={setPopup}
                title={popup.title}
                message={popup.message}
            />
        </div>
    )
}

export default Cart