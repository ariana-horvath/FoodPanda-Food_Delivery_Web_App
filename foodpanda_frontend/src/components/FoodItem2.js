import React from 'react'
import "./../css/FoodItem2.css"; 

const FoodItem2 = ({food, shoppingCart}) => {

    const onAddClick = async(e) => {
        e.preventDefault()
        shoppingCart.foods.push(food)
        shoppingCart.price += food.price
        console.log(shoppingCart)
    }
    
    return (
        <ul className='food2'>
            <li className='food-component2'>
                <div>
                    <h3>{food.name}</h3>
                    <p>{food.description}</p>
                </div>
            </li>
            <li className='food-component2'>
                <div>
                    <h3 className='price-component2'>{food.price + " RON"} </h3>
                </div>
            </li>
            <li className='food-component2'>
                <button 
                    className='btn-component2'
                    onClick={(e) => onAddClick(e)}
                >
                    Add to Cart
                </button>
            </li>
        </ul>
    )
}

export default FoodItem2