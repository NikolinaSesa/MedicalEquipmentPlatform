import { Grid, Paper, Typography } from '@mui/material'

const Companies = ({companies, onClick}) => {

    return(
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
                            <p className="blue-text" onClick={() => onClick(val.id)}>Click for more</p>
                        </Typography>
                    </Paper>
                </Grid>
            ))}      
        </Grid>
    )
}

export default Companies