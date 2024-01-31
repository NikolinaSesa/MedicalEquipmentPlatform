import {useState, useEffect} from "react";
//import Html from 'react-pdf-html';
import {Document, Page, PDFViewer, StyleSheet, View, Text} from '@react-pdf/renderer';
import ReactDOMServer from 'react-dom/server';

export default function AppointmentDetailsPDF(){

    const [appointment, setAppointment] = useState([]);
    const [user, setUser] = useState('');
    const [reservedEquipment, setReservedEquipment] = useState([]);
    const queryParameters = new URLSearchParams(window.location.search);
    const appointmentId = queryParameters.get("id");
    const id = 1;

    useEffect(() => {
        fetch("http://localhost:8080/api/appointment/getById/"+id, {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                Authorization: `Bearer ${localStorage.getItem("token")}`
            },
            method: 'GET'
        }).then(res => res.json()
        ).then((result) => {
            setAppointment(result);
            setUser(result.regularUserDTO);
            console.log(result);
        }).catch(error => console.log(error));
    }, []);

    //const date = new Date();
    /*
    const element = (
        <html>
            <body>
                <div id="report">
                    <h1>Appointment Details</h1>
                    
                    <h2>Your informations:</h2>
                    {appointment.regularUserDTO.firstName+" "+appointment.regularUserDTO.lastName}<br/>
                    Tel: {appointment.regularUserDTO.phoneNumber}<br/>
                    City: {appointment.regularUserDTO.city}<br/>
                    Email: {appointment.regularUserDTO.email}<br/>

                    <h2>Company Administrator informations:</h2>
                    {appointment.companyAdminDTO.firstName+" "+appointment.companyAdminDTO.lastName}<br/>
                    Tel: {appointment.companyAdminDTO.phoneNumber}<br/>
                    Email: {appointment.companyAdminDTO.email}<br/>
            
                    <h3>Your order:</h3>
                    <table style={{border: 1+'px'}}>
                        <thead>
                        <tr>
                            <th>Equipment</th>
                            <th>Quantity</th>
                        </tr>
                        </thead>
                        {appointment.reservedEquipmentDTO.map((val, key) => {
                        return(
                            <tr key={key}>
                            <td>{val.equipmentDTO.name}</td>
                            <td>{val.quantity}</td>
                            </tr>
                        )
                        })}
                    </table>
                </div>
            </body>
        </html>
    )
    */

    //const html = ReactDOMServer.renderToStaticMarkup(element);

    
    const styles = StyleSheet.create({
        page: {
            flexDirection: 'row',
        },
        section: {
            margin: 10,
            padding: 10,
            flexGrow: 1
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
                        <Text>Phone Number: {}</Text>
                        <Text>Email: {}</Text>
                    </View>
                    <View style={styles.section}>
                        <Text>Company Administrator informations:</Text>
                        <Text>{}</Text>
                        <Text>Phone Number: {}</Text>
                        <Text>Email: {}</Text>
                    </View>
                    <View style={styles.section}>
                        <Text>Your order:</Text>
                    </View>
                </Page>
            </Document>
        </PDFViewer>
    )   
}