import React from 'react';
import axios from "axios";
import {useEffect} from "react";


function App() {

  useEffect(()=>{
  axios.get('http://localhost:9091/couponapi/coupons/SUPERSALE').then((response) => {
  document.write("Coupon Code: "+response.data.code+"<br/>")
  document.write("Coupon Discount: "+response.data.discount+"<br/>")
  document.write("Coupon Expiry Date: "+response.data.expDate+"<br/>")
});

},[])

  return (
    <div className="App">
    </div>
  );

}

export default App;
