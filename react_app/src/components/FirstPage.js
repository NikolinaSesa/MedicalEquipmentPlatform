import React, { useEffect, useState } from "react"
import './styles/App.css'
import { Grid, Paper, Typography } from '@mui/material'

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
            <Grid container spacing={4}>
                {companies.map((val) => (
                    <Grid item xs={4} key={val.id}>
                        <Paper className="paper" elevation={6}>
                            <Typography variant="h5" bgcolor='#008CBA' borderRadius='5px' color='white' padding='5px' component="div">
                                {val.companyName}
                            </Typography>
                            <Typography variant="h6" padding='5px' component="div">
                                {val.description}
                            </Typography>
                            <Typography variant="h6" padding='5px' component="div">
                                <b>Address:</b> {val.addressDTO.address}, {val.addressDTO.city}, {val.addressDTO.zipCode}, {val.addressDTO.country}
                            </Typography>
                            <Typography component="div">
                                <p className="blue-text" onClick={() => {
                                    localStorage.setItem('companyId', val.id)
                                    window.location.href='/EquipmentByCompany'
                                }}>Click for more</p>
                            </Typography>
                        </Paper>
                    </Grid>
                ))}
            </Grid>
            </div>

        </body>
    )
}

export default FirstPage;