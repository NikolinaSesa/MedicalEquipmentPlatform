import { useState } from "react";
import './styles/App.css';

function Login(){

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const handleSubmit = (e) => {
        e.preventDefault()

        const user = {email, password}

        fetch("http://localhost:8080/auth/generateToken", {
        headers: {
            'Content-Type':'application/json'
        },
        method:'POST',
        body:JSON.stringify(user)
        }).then(res => res.text())
        .then((result) => {
            if(result === ""){
                window.alert("Email or password is wrong.")
                window.location.reload()
            }else{
                localStorage.setItem('token', result)

                fetch("http://localhost:8080/auth/getUser/"+email, {
                    headers : {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                        Authorization: `Bearer ${result}`,
                    },
                    method : "GET"
                }).then(res => res.json())
                .then((reqResult) => {
                    window.alert('You are successfully logged in.')
                    if(reqResult.role === 'ROLE_USER'){
                        window.location.href = "/UserFirstPage"
                    }else if(reqResult.role === 'ROLE_SYSTEM_ADMIN'){
                        window.location.href = '/SysAdminFirstPage'
                    }else{
                        window.location.href = '/CompanyAdminFirstPage'
                    }
                }).catch((err) => {
                    console.log(err)
                    window.alert('Something went wrong.')
                    window.location.reload()
                })
            }
        }).catch((err) => {
            console.log(err)
            window.alert('Something went wrong.')
            window.location.reload()
        })
    }

    return(
        <body>
            <div className="navbar">
                <a href="/">Home</a>
            </div>
            <div className="form-container">
                <form className="login-form">
                    <h2>Login</h2>
                    <input
                        type="email"
                        placeholder="Email"
                        onChange={(e) => setEmail(e.target.value)}>           
                    </input>
                    <input
                        type="password"
                        placeholder="Password"
                        onChange={(e) => setPassword(e.target.value)}>
                    </input>
                    <button onClick={handleSubmit}>Login</button>
                    <p>Don't have an account? <a href="/SignUp">Create account</a></p> 
                </form>            
            </div>
        </body>
    )
}

export default Login;