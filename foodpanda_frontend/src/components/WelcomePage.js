import { Navigate, useNavigate } from "react-router-dom"
import { useState } from "react";
import "./../css/WelcomePage.css"; 

const WelcomePage = () => {
    const navigate = useNavigate()
    
    const onCustomerClick = async (e) => {
        e.preventDefault()
        navigate("/customer")
    }

    const onAdminClick = async (e) => {
        e.preventDefault()
        navigate("/admin")
    }

    return(
        <div className='welcome-page'>
            <div className='welcome-texts'>
                <img className='logo-welcome-page' alt='This is a logo'/>
                <p className='logo-text-welcome-page'>Food Panda</p>
                {/* <p className='motto'>Learning as easy as i = 0;</p> */}
            </div>
            <form className="welcome-form">
                <button 
                    className='btn' 
                    id='btn-customer' 
                    type='submit'
                    onClick={(e) => onCustomerClick(e)}
                >
                    I'm a customer        
                </button>  
                <button 
                    className='btn' 
                    id='btn-admin' 
                    onClick={(e) => onAdminClick(e)}
                >
                    I'm an admin       
                </button>
            </form>
    </div>
    )
}

export default WelcomePage