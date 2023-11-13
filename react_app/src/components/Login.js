import { useState } from "react";
import './styles/App.css';

function Login(){

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    return(
        <body>
            <div className="navbar">
                <a href="/">Home</a>
            </div>
            <div className="form-container">
                <form className="login-form">
                    <h2>Login</h2>
                    <input
                        type="text"
                        placeholder="Email"
                        onChange={(e) => setEmail(e.target.value)}>           
                    </input>
                    <input
                        type="text"
                        placeholder="Password"
                        onChange={(e) => setPassword(e.target.value)}>
                    </input>
                    <button>Login</button>
                    <p>Don't have an account? <a href="/SignUp">Create account</a></p> 
                </form>            
            </div>
        </body>
    )
}

export default Login;