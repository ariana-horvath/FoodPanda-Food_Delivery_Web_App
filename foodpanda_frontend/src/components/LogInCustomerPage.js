import { useNavigate } from "react-router-dom"
import { useState } from "react";
import "./../css/LogInCustomerPage.css"; 
import Popup from "./Popup";

const LogInCustomerPage = () => {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [popup, setPopup] = useState({active: false, title: "", message: ""});
    const navigate = useNavigate()

    const validateInputSignUp = async () => {
        if(!username) {
            setPopup({active: true, title: "Oops!", 
                message: "Username can't be empty"})
            return false
        }
        if(!password) {
            setPopup({active: true, title: "Oops!", 
                message: "Password can't be empty"})
            return false
        }
        return true
    }

    const registerCustomer = async(customer) => {
        const response = await fetch('http://localhost:8080/foodpanda/customers', {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(customer)
        })
        const data = await response.json()
        
        if(data.success === true) {
            setPopup({active: true, title: "Yaay!", 
                    message: data.message})
        }
        else {
            setPopup({active: true, title: "Oops!", 
                    message: data.message})
        }
    }

    const loginCustomer = async(customer) => {
        const response = await fetch('http://localhost:8080/foodpanda/customer', {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(customer)
        })
        const data = await response.json()
        if(data.success === true) {
            setPopup({active: true, title: "Yaay!", 
                    message: data.message})
            navigate('/customer/main-page', {
                state: {customerName: customer.username}
            })
        }
        else {
            setPopup({active: true, title: "Oops!", 
                    message: data.message})
        }
    }

    const onRegisterClick = async (e) => {
        e.preventDefault()

        let notEmpty = await validateInputSignUp()
        if (notEmpty){
            registerCustomer({username, password})
        }
    }

    const onLoginClick = async (e) => {
        e.preventDefault()
        
        let notEmpty = await validateInputSignUp()
        if (notEmpty){
            loginCustomer({username, password})
        }
    }

    return(
        <div className='login-customer-page'>
            <div className='login-customer-texts'>
                <img className='logo-login-customer-page' alt='This is a logo'/>
                <p className='logo-text-login-customer-page'>Food Panda</p>
                {/* <p className='motto'>Learning as easy as i = 0;</p> */}
            </div>

            <form className="login-customer-form">
                <div className="user-data">
                    <label className="user-data-label">Username:</label>
                    <input
                        className="user-data-input" 
                        type="text" 
                        placeholder="Your username"
                        value={username} 
                        onChange={(e) => setUsername(e.target.value)} 
                    />
                </div>
                <div className="user-data">
                    <label className="user-data-label">Password:</label>
                    <input
                    className="user-data-input" 
                        type="password" 
                        placeholder="Your password"
                        value={password} 
                        onChange={(e) => setPassword(e.target.value)} 
                    />
                </div>
                
                <button 
                    className='btn' 
                    id='btn-login' 
                    type='submit'
                    onClick={(e) => onLoginClick(e)}
                >
                    Login        
                </button>  
                <button 
                    className='btn' 
                    id='btn-register' 
                    onClick={(e) => onRegisterClick(e)}
                >
                    Register       
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

export default LogInCustomerPage