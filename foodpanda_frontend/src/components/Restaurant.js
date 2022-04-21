import React from 'react'
import "./../css/Restaurant.css"; 
import { useNavigate } from 'react-router-dom';

const Restaurant = ({restaurant, customerName}) => {

    const navigate = useNavigate()
    return (
        <ul className='restaurant'>
            <li className='restaurant-component'>
                <div>
                    <a href='/customer/main-page/restaurant'
                        onClick={(e) => navigate('/customer/main-page/restaurant', {
                            state: {customerName:customerName, restaurant:restaurant}
                        })}
                    >
                        <h3 className='restaurant-component'>{restaurant.name}</h3>
                    </a>
                </div>
            </li>
            <li className='restaurant-component'>
                <div>
                    <p className='price-component'><b>Location:  </b>{restaurant.location} </p>
                </div>
            </li>
        </ul>
    )
}

export default Restaurant