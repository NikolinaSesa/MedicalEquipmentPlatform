import { useEffect, useState } from "react";
import { List, ListItem, ListItemButton, ListItemText } from "@mui/material";
import './styles/App.css'
import './styles/Appointments.css'

const MyReservations = () => {

    const [appointments, setAppointments] = useState([])

    const userId = localStorage.getItem('userId')

    useEffect(() => {
        fetch("http://localhost:8080/api/appointment/reservedAppointments/"+userId, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            method : "GET"
        }).then((res) => res.json()
        ).then(result => setAppointments(result)
        ).catch(err => console.log(err))
    }, [])

    const handleQuit = val => {
        console.log(val);

        fetch("http://localhost:8080/api/appointment/quitAppointment", {
            method: "PUT",
            headers: { 
                'Content-Type': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`,
            },
            body: JSON.stringify(val)
        }).then(res => res.json()
        ).then((result) => {
            console.log(result);
            window.alert("You successfully quit appointment.");
            //window.location.reload();
        }
        ).catch((err) => console.log(err))
    }

    return(
        <>
            <div className="navbar">
                <button className="btn" onClick={() => {window.location.href='#'}}>Profile</button>
                <button className="btn" onClick={() => {window.location.href='/UserFirstPage'}}>Search companies</button>
                <button className="btn" onClick={() => {window.location.href='/MyReservations'}}>My reservations</button>
                <button className="btn" onClick={() => {
                    localStorage.removeItem('token')
                    window.location.href='/Login'
                    }}>Logout</button>
            </div>
            <div className="listDiv">
                <h2>Your reservations:</h2>
                <List sx={{width: '80%', margin: '0 auto'}}>
                    {appointments.map((val) => (
                        <ListItem sx={{margin: '5px', border: '2px solid', borderRadius: '10px'}}>
                            <ListItemText primary="You: " secondary={val.regularUserDTO.firstName+" "+val.regularUserDTO.lastName}></ListItemText>
                            <ListItemText primary="Company administrator: " secondary={val.companyAdminDTO.firstName+" "+val.companyAdminDTO.lastName}></ListItemText>
                            <ListItemText primary="Date: " secondary={val.date}></ListItemText>
                            <List>
                                {val.reservedEquipmentDTOs.map((val2) => (
                                    <ListItem>
                                        <ListItemText primary="Equipment name: " secondary={val2.equipmentDTO.name}></ListItemText>
                                    </ListItem>
                                ))}
                            </List>
                            <ListItemButton sx={{width: '100px', border: '2px solid #0056b3'}}>View Details</ListItemButton>
                            <ListItemButton sx={{width: '100px', border: '2px solid #0056b3'}} onClick={handleQuit(val)}>Quit</ListItemButton>
                        </ListItem>
                    ))}
                </List>
            </div>
        </>
    )
}

export default MyReservations;