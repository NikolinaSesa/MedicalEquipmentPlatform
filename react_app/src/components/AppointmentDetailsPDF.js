import {useState, useEffect} from 'react';
//import Html from 'react-pdf-html';
import {Document, Page, PDFViewer, StyleSheet, View, Text} from '@react-pdf/renderer';
import ReactDOMServer from 'react-dom/server';

export default function AppointmentDetailsPDF(){

    const [appointment, setAppointment] = useState('');
    const [regularUser, setRegularUser] = useState('');
    const [companyAdmin, setCompanyAdmin] = useState(''); 
    const [reservedEquipment, setReservedEquipment] = useState([]);
    const queryParameters = new URLSearchParams(window.location.search);
    const appointmentId = queryParameters.get("id");

    useEffect(() => {
        fetch("http://localhost:8080/api/appointment/getById/"+appointmentId, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            method: 'GET'
        }).then(res => res.json()
        ).then((result) => {
            setAppointment(result);
            setRegularUser(result.regularUserDTO);
            setCompanyAdmin(result.companyAdminDTO);
            result.reservedEquipmentDTOs.map((reservedEquipmentDTO) => {
                const resEquipment = {
                    equipment : reservedEquipmentDTO.equipmentDTO,
                    quantity: reservedEquipmentDTO.quantity
                }
                setReservedEquipment([...reservedEquipment, resEquipment]);
            });
        }).catch(error => console.log(error));
    }, []);

    const styles = StyleSheet.create({
        page: {
            flexDirection: 'column',
        },
        section: {
            margin: 10,
            padding: 10,
            width: '100%'
        },
        viewer: {
            width: window.innerWidth,
            height: window.innerHeight,
        }
    });
    
    return(
        <PDFViewer style={styles.viewer}>
            <Document>
                <Page size="A4" style={styles.page}>
                    <View style={styles.section}>
                        <Text>Your informations:</Text>
                        <Text>{}</Text>
                        <Text>Name: {regularUser.firstName+ " "+regularUser.lastName}</Text>
                        <Text>Phone Number: {regularUser.phoneNumber}</Text>
                        <Text>Email: {regularUser.email}</Text>
                    </View>
                    <View style={styles.section}>
                        <Text>Company Administrator informations:</Text>
                        <Text>{}</Text>
                        <Text>Name: {companyAdmin.firstName+" "+companyAdmin.lastName}</Text>
                        <Text>Email: {companyAdmin.email}</Text>
                    </View>
                    <View style={styles.section}>
                        <Text>Appointment details:</Text>
                        <Text>Date: {appointment.date}</Text>
                    </View>
                    <View style={styles.section}>
                        <Text>Your order:</Text>
                        {reservedEquipment.map((val) => {
                            return(
                                <View>
                                    <Text>
                                        Equipment: {val.equipment.name}
                                        
                                        ( Quantity: {val.quantity})
                                    </Text>
                                </View>
                            )
                        })}
                    </View>
                </Page>
            </Document>
        </PDFViewer>
    )   
}