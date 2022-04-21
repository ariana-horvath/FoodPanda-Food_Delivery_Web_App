import React from 'react'
import "./../css/FoodItem.css"; 

const FoodItem = ({food}) => {
    return (
        <ul className='food'>
            <li className='food-component'>
                <div>
                    <h3>{food.name}</h3>
                    <p>{food.description}</p>
                </div>
            </li>
            <li className='food-component'>
                <div>
                    <h3 className='price-component'>{food.price + " RON"} </h3>
                </div>
            </li>
        </ul>
    )
}

export default FoodItem