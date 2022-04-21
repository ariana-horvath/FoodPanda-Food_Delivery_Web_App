import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import LogInCustomerPage from './components/LogInCustomerPage';
import WelcomePage from './components/WelcomePage';
import LogInAdminPage from './components/LogInAdminPage';
import AdminMainPage from './components/AdminMainPage';
import AddRestaurantPage from './components/AddRestaurantPage';
import AddFoodPage from './components/AddFoodPage';
import CustomerMainPage from './components/CustomerMainPage';
import RestaurantMenuPage from './components/RestaurantMenuPage';
import Cart from './components/Cart';
import OrdersAdminPage from './components/OrdersAdminPage';
import OrdersCustomerPage from './components/OrdersCustomerPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<WelcomePage/>}/>
        <Route exact path="/customer" element={<LogInCustomerPage/>}/>
        <Route exact path="/admin" element={<LogInAdminPage/>}/>
        <Route exact path="/admin/main-page" element={<AdminMainPage/>}/>
        <Route exact path="/admin/restaurant" element={<AddRestaurantPage/>}/>
        <Route exact path="/admin/main-page/add-food" element={<AddFoodPage/>}/>
        <Route exact path="/customer/main-page" element={<CustomerMainPage/>}/>
        <Route exact path="/customer/main-page/restaurant" element={<RestaurantMenuPage/>}/>
        <Route exact path="/customer/main-page/my-cart" element={<Cart/>}/>
        <Route exact path="/admin/orders" element={<OrdersAdminPage/>}/>
        <Route exact path="/customer/orders" element={<OrdersCustomerPage/>}/>
      </Routes>
    </Router>
  );
}

export default App;
