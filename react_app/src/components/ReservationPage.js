import { useEffect, useState } from 'react';
import './styles/App.css';
import './styles/ReservationPage.css';
import Equipment from './Equipment'
import AvailableAppointments from './AvailableAppointments';
import { List, ListItem, ListItemText } from "@mui/material";

const ReservationPage = () => {

    const[company, setCompany] = useState('')
    const[equipment, setEquipment] = useState([])
    const[address, setAddress] = useState('')

    const[selectedEquipment, setSelectedEquipment] = useState([])

    const[availableAppointments, setAvailableAppointments] = useState([])

    const[showEquipment, setShowEquipment] = useState(true)
    const[showAppointments, setShowAppointments] = useState(false)

    const id = localStorage.getItem('companyId')

    useEffect(() => {
        fetch("http://localhost:8080/api/company/"+id, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            method : "GET"
        }).then(res => res.json()
        ).then((result) => {
            setCompany(result)
            setEquipment(result.equipmentDTOs)
            setAddress(result.addressDTO)
        }).catch((err) => {
            console.log(err)
        })
    }, [])

    const handleClick = val => {
        setSelectedEquipment(selectedEquipment => [...selectedEquipment, {equipmentDTO: val, quantity: 1}])
        console.log(selectedEquipment)
    }

    const handleFinish = () => {

        fetch("http://localhost:8080/api/appointment/"+id, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            method : "GET"
        }).then(res => res.json()
        ).then((result) => {
            setAvailableAppointments(result);
            setShowEquipment(false)
            setShowAppointments(true)
        }).catch((err) => console.log(err))

    }

    const handleSelect = id => {

        const appointment = {id: id, regularUserDTO: {id: localStorage.getItem('userId')}, reservedEquipmentDTOs: selectedEquipment}

        fetch("http://localhost:8080/api/appointment/", {
            method: "PUT",
            headers: { 
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`,
            },
            body: JSON.stringify(appointment)
        }).then(res => res.json()
        ).then((result) => {
            alert('You successfully reserved appointment. Chack "My reservations" to see details.')
            window.location.href='/UserFirstPage'
        }
        ).catch((err) => console.log(err))
    }

    return(
        <body>
            <div className='navbar'>
                <button className="btn" onClick={() => {window.location.href='#'}}>Profile</button>
                <button className="btn" onClick={() => {window.location.href='/UserFirstPage'}}>Search companies</button>
                <button className="btn" onClick={() => {window.location.href='#'}}>My reservations</button>
                <button className="btn" onClick={() => {
                    localStorage.removeItem('accessToken')
                    window.location.href='/Login'
                    }}>Logout</button>
            </div>
            {showEquipment && 
                <div>
                    <Equipment company={company} address={address} equipment={equipment} onClick={handleClick}></Equipment>
                    {selectedEquipment.length !== 0 && 
                        <div className='div'>
                            <h2>Selected equipment:</h2>
                            <List sx={{width: '80%'}}>
                                {selectedEquipment.map((val) => (
                                    <ListItem sx={{margin: '5px', border: '2px solid', borderRadius: '10px'}}>
                                        <ListItemText primary={val.equipmentDTO.name} secondary={'Selected quantity: '+val.quantity}></ListItemText>
                                    </ListItem>
                                ))}
                            </List>
                            <button className='btn' onClick={handleFinish}>Finish</button>
                        </div>
                    }
                </div>
            }
            {showAppointments && <AvailableAppointments appointments={availableAppointments} selectedEquipment={selectedEquipment} onClick={handleSelect}></AvailableAppointments>}
        </body>
    )

}

export default ReservationPage;