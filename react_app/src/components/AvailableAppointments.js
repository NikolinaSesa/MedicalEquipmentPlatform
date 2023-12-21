import { List, ListItem, ListItemButton, ListItemText } from "@mui/material";
import './styles/Appointments.css'

const AvailableAppointments = ({appointments, selectedEquipment, onClick}) => {

    return (
        <>
            <div className="listDiv">
                <h2>Your reservation:</h2>
                <List sx={{width: '80%', margin: '0 auto', border: '2px solid', borderRadius: '10px'}}>
                    {selectedEquipment.map((val) => (
                        <ListItem sx={{margin: '5px'}}>
                            <ListItemText primary={val.equipmentDTO.name} secondary={'Selected quantity: '+val.quantity}></ListItemText>
                        </ListItem>
                    ))}
                </List>
            </div>
            <div className="listDiv">
                <h2>Choose one of available appointments:</h2>
                <List sx={{width: '80%', margin: '0 auto'}}>
                    {appointments.map((val) => (
                        <ListItem key={val.id} sx={{margin: '5px', border: '2px solid', borderRadius: '10px'}}>
                            <ListItemButton onClick={() => onClick(val.id)}>
                                <ListItemText primary={'Date: '+val.date} secondary={'Company administrator: '+val.companyAdminDTO.firstName+' '+val.companyAdminDTO.lastName}></ListItemText>
                            </ListItemButton>
                        </ListItem>
                    ))}
                </List>
            </div>
        </>
    )
}

export default AvailableAppointments;