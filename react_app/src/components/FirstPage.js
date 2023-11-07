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
        }, [])
    })

    return(
        <body>
            <div className="navbar">
                <button className="btn">Log In</button>
                <button className="btn">Sign Up</button>
            </div>
            <div className="caption">
                <h1>Welcome to Our Website</h1>
                <p>Discover companies and medical equipment that they offer</p>
            </div>
            <Grid container spacing={2}>
                {companies.map((val) => (
                    <Grid item xs={12} sm={6} lg={3} key={val.id}>
                        <Paper>
                            <Typography variant="h6" component="div">
                                {val.companyName}
                            </Typography>
                        </Paper>
                    </Grid>
                ))}
            </Grid>

        </body>
    )
}

export default FirstPage;