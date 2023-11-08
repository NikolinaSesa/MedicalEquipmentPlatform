import { useState } from "react";
import './styles/App.css';

function SignUp(){

    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [passwordAgain, setPasswordAgain] = useState('')
    const [city, setCity] = useState('')
    const [country, setCountry] = useState('')
    const [phoneNumber, setPhoneNumber] = useState('')
    const [profession, setProfession] = useState('')
    const [companyInfo, setCompanyInfo] = useState('')

    return(
        <body>
            <div className="navbar">
                <a href="/">Home</a>
            </div>
            <div className="login-form-container">
                <form className="login-form">
                    <h2>Create an account</h2>
                    <input
                        type="text"
                        placeholder="First name"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}>           
                    </input>
                    <input
                        type="text"
                        placeholder="Last name"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}>           
                    </input>
                    <input
                        type="text"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}>           
                    </input>
                    <input
                        type="text"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}>
                    </input>
                    <button>Login</button>
                    <p>Don't have an account? <a href="/SignUp">Create account</a></p> 
                </form>            
            </div>
        </body>
    )
}

export default SignUp;