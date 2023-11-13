import { useState } from "react";
import './styles/App.css';

function SignUp(){

    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [repeatPassword, setRepeatPassword] = useState('')
    const [city, setCity] = useState('')
    const [country, setCountry] = useState('')
    const [phoneNumber, setPhoneNumber] = useState('')
    const [profession, setProfession] = useState('')
    const [companyInformation, setCompanyInfo] = useState('')
    const [passwordsMatch, setPasswordsMatch] = useState(true)

    const backgroundColor = !passwordsMatch ? 'red' : ''

    const handleSignUp = (e) => {
        e.preventDefault()

        const newUser = {firstName, lastName, email, password, phoneNumber, city, country, profession, companyInformation}

        if(passwordsMatch){

            fetch("http://localhost:8080/api/user/create", {
                headers: {
                    'Content-Type':'application/json'
                },
                method:'POST',
                body:JSON.stringify(newUser)
            }).then(res => res.json()).then((result) => {
                console.log(result)
                window.alert('Successfuly created new account.')
                window.location.href='/Login'
            }).catch((err) => {
                console.log(err)
                window.alert('Something went wrong.')
                window.location.reload()
            })
        }else{
            window.alert("Passwords do not match. Try again.")
            window.location.reload()
        }
    }

    return(
        <body>
            <div className="navbar">
                <a href="/">Home</a>
            </div>
            <div className="signup-form-container">
                <form className="signup-form" onSubmit={handleSignUp}>
                    <h2>Create an account</h2>
                    <div className="inputs-div">
                        <div className="column">
                            <input
                                type="text"
                                value={firstName}
                                placeholder="First Name"
                                onChange={(e) => setFirstName(e.target.value)}
                                required
                            />
                            <input
                                type="text"
                                value={lastName}
                                placeholder="Last Name"
                                onChange={(e) => setLastName(e.target.value)}
                                required
                            />
                            <input
                                type="email"
                                value={email}
                                placeholder="Email"
                                onChange={(e) => setEmail(e.target.value)}
                                required
                            />
                            <input
                                type="password"
                                value={password}
                                placeholder="Password"
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                            <input
                                type="password"
                                value={repeatPassword}
                                placeholder="Repeat Password"
                                onChange={(e) => {
                                    setRepeatPassword(e.target.value)

                                    if(password !== e.target.value){
                                        setPasswordsMatch(false)
                                    }else{
                                        setPasswordsMatch(true)
                                    }

                                }}
                                required
                                style={{backgroundColor}}
                            />
                        </div>
                        <div className="column">
                            <input
                                type="text"
                                value={phoneNumber}
                                placeholder="Phone Number"
                                onChange={(e) => setPhoneNumber(e.target.value)}
                                required
                            />
                            <input
                                type="text"
                                value={city}
                                placeholder="City"
                                onChange={(e) => setCity(e.target.value)}
                                required
                            />
                            <input
                                type="text"
                                value={country}
                                placeholder="Country"
                                onChange={(e) => setCountry(e.target.value)}
                                required
                            />
                            <input
                                type="text"
                                value={profession}
                                placeholder="Profession"
                                onChange={(e) => setProfession(e.target.value)}
                            />
                            <input
                                type="text"
                                value={companyInformation}
                                placeholder="Company Information"
                                onChange={(e) => setCompanyInfo(e.target.value)}
                            />
                        </div>
                    </div>
                    <button type="submit">Sign Up</button>
                    <p>Already have an account? <a href="/Login">Sign In</a></p> 
                </form>
            </div>
        </body>
    )
}

export default SignUp;

