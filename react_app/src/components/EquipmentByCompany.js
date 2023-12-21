import { useEffect, useState } from "react"
import './styles/App.css'
import Equipment from "./Equipment"

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
            <Equipment company={company} address={address} equipment={equipment} onClick={() => {}}></Equipment>
        </body>
    )
}

export default EquipmentByCompany