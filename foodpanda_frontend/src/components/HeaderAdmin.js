import { useNavigate } from "react-router-dom";
import "./../css/HeaderAdmin.css"; 

const HeaderAdmin = ({admin, restaurant}) => {
    const navigate = useNavigate()

    return (
        <header className='admin-header'>
            <img className='logo-admin-page' alt='This is a logo'/>
            <p className='logo-text-admin-main-page'>Food Panda</p>
            <nav className='nav-bar'>
                    <ul>
                        <li className='nav-item'>
                            <a 
                                href='/admin/main-page/add-food'
                                onClick={(e) => navigate('/admin/main-page/add-food', {
                                    state: {
                                        admin: admin,
                                        restaurant: restaurant
                                    }
                                })}
                            >
                                <p className='text'>Add foods</p>
                            </a>
                        </li>
                        <li className='nav-item'>
                            <a 
                                href='/admin/main-page' 
                                onClick={(e) => navigate('/admin/main-page', {
                                    state: {
                                        admin: admin,
                                        restaurant: restaurant
                                    }
                                })}
                            >
                                <p className='text'>View Menu</p>
                            </a>
                        </li>

                        <li className='nav-item'>
                            <a 
                                href='/admin/orders' 
                                onClick={(e) => navigate('/admin/orders', {
                                    state: {
                                        admin: admin,
                                        restaurant: restaurant
                                    }
                                })}
                            >
                                <p className='text'>View Orders</p>
                            </a>
                        </li>

                        <li className='nav-item'>
                            <a 
                                href='/' 
                                onClick={(e) => navigate('/')}
                            >
                                <p className='text'>Log Out</p>
                            </a>
                        </li>
                    </ul>
                </nav>
        </header>
    )
}

export default HeaderAdmin