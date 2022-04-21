import React from 'react'
import "./../css/AddFoodPage.css"; 
import HeaderAdmin from "./HeaderAdmin";
import { useState, useEffect } from 'react'
import {useLocation, useNavigate} from "react-router-dom"
import Popup from "./Popup";
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';

const AddFoodPage = () => {
    const {state} = useLocation()
    const admin = state.admin
    const restaurant = state.restaurant
    const [name, setName] = useState("")
    const [description, setDescription] = useState("")
    const [price, setPrice] = useState(0.0)
    const [popup, setPopup] = useState({active: false, title: "", message: ""});
    const [categories, setCategories] = useState([])
    const navigate = useNavigate()
    const [category, setCategory] = useState("")
    const defaultOption = 'Select category from existent'

    const fetchCategories = async () => {
        const response = await fetch(`http://localhost:8080/foodpanda/categories`)
        const data = await response.json()
        return data
    }
    
    useEffect(() => {
        const getCategories = async () => {
            const categoriesNames = await fetchCategories()
            setCategories(categoriesNames)
        }
    
        getCategories()
    }, [])

    const validateInputSignUp = async () => {
        if(!name) {
            setPopup({active: true, title: "Oops!", 
                message: "Name can't be empty"})
            return false
        }
        if(!description) {
            setPopup({active: true, title: "Oops!", 
                message: "Description can't be empty"})
            return false
        }
        if(!price) {
            setPopup({active: true, title: "Oops!", 
                message: "Price can't be empty"})
            return false
        }
        if(isNaN(price)) {
            setPopup({active: true, title: "Oops!", 
                message: "Price has to be a number!"})
            return false
        }
        return true
    }

    const addFood = async(food) => {
        console.log(food)
        const response = await fetch(`http://localhost:8080/foodpanda/foods`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(food)
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

    const onAddFoodClick = async (e) => {
        e.preventDefault()
        
        let notEmpty = await validateInputSignUp()
        if (notEmpty) { 
            console.log(restaurant)
            addFood({"name":name, "description":description, "price":price, "category":category, "restaurant":restaurant})
        }    
    }

    const handleSelect = (e) => {
        setCategory(e.value);
    }

    return (
        <div className="add-food-page">
            <HeaderAdmin admin={admin} restaurant={restaurant}/>
            <div className='add-food-texts'>
                <p>Select the category and add the foods:</p>
            </div>
            <form className="add-food-form">
                <label className="food-data-label">Category:</label>
                <Dropdown options={categories.map((category) => (category.name))} onChange = {handleSelect}  
                                    value={defaultOption} placeholder="Select a category"></Dropdown>
                
                <br></br>
                <div className="food-data">
                    <label className="food-data-label">Name:</label>
                    <input
                        className="food-data-input" 
                        type="text" 
                        placeholder="Name"
                        value={name} 
                        onChange={(e) => setName(e.target.value)} 
                    />
                </div>
                <div className="food-data">
                    <label className="food-data-label">Description:</label>
                    <input
                    className="food-data-input" 
                        type="text" 
                        placeholder="Description"
                        value={description} 
                        onChange={(e) => setDescription(e.target.value)} 
                    />
                </div>
                <div className="food-data">
                    <label className="food-data-label">Price:</label>
                    <input
                    className="food-data-input" 
                        type="text" 
                        placeholder="Price"
                        value={price} 
                        onChange={(e) => setPrice(e.target.value)} 
                    />
                </div>
                
                <button 
                    className='btn' 
                    id='btn-add-food' 
                    type='submit'
                    onClick={(e) => onAddFoodClick(e)}
                >
                    Add Food        
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

export default AddFoodPage