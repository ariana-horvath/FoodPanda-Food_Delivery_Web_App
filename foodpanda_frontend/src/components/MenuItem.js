import React from 'react'
import FoodItem from './FoodItem'
import "./../css/MenuItem.css";

const MenuItem = ({menuItem}) => {
    return (
        <div>
            <h3 className = 'category-title'>{menuItem.category}</h3>
            <div>
                {menuItem.foods.map((food) => (
                    <FoodItem
                        food={food}
                    />   
                ))}
            </div>
        </div>
    )
}

export default MenuItem