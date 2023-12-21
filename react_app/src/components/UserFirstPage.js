import { useState, useEffect } from 'react'
import './styles/App.css'
import Companies from './Companies'

function UserFirstPage(){

    const [companies, setCompanies] = useState([])
    const [searchItem, setSearchItem] = useState('');

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
        window.location.href='/ReservationPage';
    }

    const searchHandler = (e) => {
        const searchItem = e.target.value;
        setSearchItem(searchItem);

        const filteredCompanies = companies.filter((company) => company.companyName.toLowerCase().includes(searchItem.toLowerCase()));
        setCompanies(filteredCompanies)
    }

    return(
        <body>
            <div className="navbar">
                <button className="btn" onClick={() => {window.location.href='#'}}>Profile</button>
                <button className="btn" onClick={() => {window.location.href='/UserFirstPage'}}>Search companies</button>
                <button className="btn" onClick={() => {window.location.href='/MyReservations'}}>My reservations</button>
                <button className="btn" onClick={() => {
                    localStorage.removeItem('token')
                    window.location.href='/Login'
                    }}>Logout</button>
            </div>
            <div className='search'>
                <input
                    type="text"
                    placeholder="Search"
                    className="searchBox"
                    value={searchItem}
                    onChange={searchHandler}
                />
            </div>
            <div className='grid'>
                <Companies companies={companies} onClick={handleClick}></Companies>
            </div>
        </body>
    )
}
export default UserFirstPage