import { useNavigate } from "react-router-dom"
import { useState } from "react";
import "./../css/LogInAdminPage.css"; 
import Popup from "./Popup";

const LogInAdminPage = () => {
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

    const loginAdmin = async(admin) => {
        const response = await fetch(`http://localhost:8080/foodpanda/admin`, {
            method: "POST",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(admin)
        })
        const data = await response.json()
        
        if(data.success === true) {
            const receivedAdmin = await fetch(`http://localhost:8080/foodpanda/admins/${username}`)
            const adminData = await receivedAdmin.json()

            if (adminData.restaurantName === "") {
                const receivedZones = await fetch(`http://localhost:8080/foodpanda/zones`)
                const zonesData = await receivedZones.json()
                navigate('/admin/restaurant', {
                    state: {admin: adminData, zones:zonesData}
                })
            } 
            else {
                navigate('/admin/main-page', {
                    state: {admin: adminData, restaurant: adminData.restaurantName}
                })
            }       
        }
        else {
            setPopup({active: true, title: "Oops!", 
                    message: data.message})
        }
    }

    const onLoginClick = async (e) => {
        e.preventDefault()
        
        let notEmpty = await validateInputSignUp()
        if (notEmpty){
            loginAdmin({username, password})
        }
    }

    return(
        <div className='login-admin-page'>
            <div className='login-admin-texts'>
                <img className='logo-login-admin-page' alt='This is a logo'/>
                <p className='logo-text-login-admin-page'>Food Panda</p>
                {/* <p className='motto'>Learning as easy as i = 0;</p> */}
            </div>

            <form className="login-admin-form">
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
                    id='btn-login-admin' 
                    type='submit'
                    onClick={(e) => onLoginClick(e)}
                >
                    Login        
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

export default LogInAdminPage