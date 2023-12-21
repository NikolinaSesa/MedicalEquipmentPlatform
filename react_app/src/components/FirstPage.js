import React, { useEffect, useState } from "react"
import './styles/App.css'
import Companies from "./Companies"

function FirstPage(){

    const[companies, setCompanies] = useState([])

    useEffect(() => {
        fetch("http://localhost:8080/api/company/", {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            },
            method : "GET"
        }).then(res => res.json()
        ).then((result) => {
            setCompanies(result)
        }).catch((err) => {
            console.log(err)
        })
    }, [])

    const handleClick = val => {
        localStorage.setItem('companyId', val);
        window.location.href='/EquipmentByCompany';
    }

    return(
        <body>
            <div className="navbar">
                <button className="btn" onClick={() => {window.location.href='/Login'}}>Log In</button>
                <button className="btn" onClick={() => {window.location.href='/SignUp'}}>Sign Up</button>
            </div>
            <div className="caption">
                <h1>Welcome to Our Website</h1>
                <p>Discover companies and medical equipment that they offer</p>
            </div>
            <div className="grid">
                <Companies companies={companies} onClick={handleClick}></Companies>
            </div>

        </body>
    )
}

export default FirstPage;