import { Grid, Paper, Typography } from '@mui/material'

const Equipment = ({company, address, equipment, onClick}) => {
    return(
        <>
            <div className="caption2">
                <h2>{company.companyName}</h2>
                <p><b>Average rating:</b> {company.averageRating}</p>
                <p><b>Address:</b> {address.address}, {address.city}, {address.zipCode}, {address.country}</p>
                <p><b>Description:</b> {company.description}</p>
            </div>
            <div className="grid">
                <Grid container spacing={4}>
                    {equipment.map((val) => (
                        <Grid item key={val.id} xs={4}>
                            <Paper className="paper" elevation={6} onClick={() => onClick(val)}>
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
        </>
    )
}

export default Equipment;