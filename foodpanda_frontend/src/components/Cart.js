import React from 'react'
import "./../css/Cart.css";
import HeaderCustomer from "./HeaderCustomer";
import FoodItem3 from './FoodItem3';
import { useLocation } from 'react-router-dom';
import Popup from "./Popup";
import { useState, useRef } from 'react'
import emailjs from "emailjs-com";

const Cart = () => {
    const {state} = useLocation()
    const shoppingCart = state.shoppingCart
    const customerName = state.customerName
    const [popup, setPopup] = useState({active: false, title: "", message: ""});
    const [address, setAddress] = useState("")
    const [comments, setComments] = useState("")
    console.log(shoppingCart)
    
    function sendEmail(e) {
        e.preventDefault();

        const emailContent = {
            restaurant: shoppingCart.restaurant,
            customer: customerName,
            price: shoppingCart.price,
            address: address,
            details: comments,
            foods: shoppingCart.foods.map((food) => " " + food.name + " - " + food.price)
        }

        emailjs.send('gmail', 'template_23hq41a', emailContent, 'aNnRWNS0J8AfO1LpK')
        .then((result) => {
            console.log(result.text);
        }, (error) => {
            console.log(error.text);
        });
    }

    const onPlaceOrderClick = async(e) => {
        if(!address) {
            setPopup({active: true, title: "Oops!", 
                message: "Delivery address can't be empty"})
            return false
        }
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
            sendEmail(e)             
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

            <form className="cart-form">
                <div className="data">
                    <label className="data-label">Delivery Address:</label>
                    <input
                        className="data-input" 
                        type="text" 
                        placeholder="Address"
                        value={address} 
                        onChange={(e) => setAddress(e.target.value)} 
                    />
                </div>
                <div className="data">
                    <label className="data-label">Additional Details:</label>
                    <input
                    className="data-input" 
                        type="text" 
                        placeholder="Details"
                        value={comments} 
                        onChange={(e) => setComments(e.target.value)} 
                    />
                </div>
            </form>  

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