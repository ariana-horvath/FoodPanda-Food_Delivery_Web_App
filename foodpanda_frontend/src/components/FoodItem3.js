import React from 'react'
import "./../css/FoodItem.css"; 

const FoodItem3 = ({food}) => {    
    return (
        <ul className='food'>
            <li className='food-component'>
                <div>
                    <h3>{food.name}</h3>
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

export default FoodItem3