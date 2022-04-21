import React from 'react'
import "./../css/DeliveryZone.css";

const DeliveryZone = ({zoneName}) => {
    return (
        <div>
            <input type="checkbox" id='zones' name="zones" value={zoneName}/>
            <label className='zone-component' for="zones">{zoneName}</label>
        </div>
    )
}

export default DeliveryZone