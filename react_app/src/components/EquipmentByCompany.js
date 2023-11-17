import { useEffect, useState } from "react"
import './styles/App.css'
import { Grid, Paper, Typography } from '@mui/material'

function EquipmentByCompany() {

    const[company, setCompany] = useState('')
    const[equipment, setEquipment] = useState([])
    const[address, setAddress] = useState('')

    const id = localStorage.getItem('companyId')

    useEffect(() => {
        fetch("http://localhost:8080/api/company/"+id, {
            headers : {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
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

    return(
        <body>
            <div className="navbar">
                <a href="/">Home</a>
            </div>
            <div className="caption2">
                <h2>{company.companyName}</h2>
                <p><b>Average rating:</b> {company.averageRating}</p>
                <p><b>Address:</b> {address.address}, {address.city}, {address.zipCode}, {address.country}</p>
                <p><b>Description:</b> {company.description}</p>
            </div>
            <div className="grid">
                <Grid container spacing={4}>
                    {equipment.map((val) => (
                        <Grid item xs={12}>
                            <Paper className="paper" elevation={6}>
                                <Typography variant="h5" bgcolor='#008CBA' color='white' borderRadius='5px' padding='5px' component="div">
                                    {val.name}
                                </Typography>
                                <Typography variant="h6" borderRadius='5px' padding='5px' component="div">
                                    Quantity: {val.quantity}
                                </Typography>
                            </Paper>
                        </Grid>
                    ))}
                </Grid>
            </div>
        </body>
    )
}

export default EquipmentByCompany