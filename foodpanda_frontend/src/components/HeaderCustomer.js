import { useNavigate } from "react-router-dom";
import "./../css/HeaderAdmin.css"; 

const HeaderCustomer = ({customerName, shoppingCart}) => {
    const navigate = useNavigate()
    console.log(shoppingCart)
    return (
        <header className='admin-header'>
            <img className='logo-admin-page' alt='This is a logo'/>
            <p className='logo-text-admin-main-page'>Food Panda</p>
            <nav className='nav-bar'>
                    <ul>
                        <li className='nav-item'>
                            <a 
                                href='/customer/main-page'
                                onClick={(e) => navigate('/customer/main-page', {
                                    state: {customerName:customerName}
                                })}
                            >
                                <p className='text'>Restaurants</p>
                            </a>
                        </li>
                        {(shoppingCart != null) && <li className='nav-item'>
                            <a 
                                href='/customer/main-page/my-cart' 
                                onClick={(e) => navigate('/customer/main-page/my-cart', {
                                    state: {
                                        customerName: customerName,
                                        shoppingCart: shoppingCart
                                    }
                                })}
                            >
                                <p className='text'>My Cart</p>
                            </a>
                        </li>}

                        <li className='nav-item'>
                            <a 
                                href='/customer/orders' 
                                onClick={(e) => navigate('/customer/orders', {
                                    state: {
                                        customerName: customerName,
                                        shoppingCart: shoppingCart
                                    }
                                })}
                            >
                                <p className='text'>My Orders</p>
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

export default HeaderCustomer