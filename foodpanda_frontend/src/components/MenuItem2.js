import React from 'react'
import FoodItem2 from './FoodItem2'
import "./../css/MenuItem.css";

const MenuItem2 = ({menuItem, shoppingCart}) => {
    console.log(shoppingCart)
    return (
        <div>
            <h3 className = 'category-title'>{menuItem.category}</h3>
            <div>
                {menuItem.foods.map((food) => (
                    <FoodItem2
                        food={food}
                        shoppingCart={shoppingCart}
                    />   
                ))}
            </div>
        </div>
    )
}

export default MenuItem2