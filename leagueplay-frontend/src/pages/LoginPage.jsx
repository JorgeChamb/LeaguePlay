import LoginForm from "../components/LoginForm";

function LoginPage() {
  const handleLogin = async (loginData) => {
    try {
      const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(loginData),
      });

      if (!response.ok) {
        throw new Error("Login fallido");
      }

      const result = await response.text();
      alert("Login correcto: " + result);
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <div>
      <h2>Iniciar sesión</h2>
      <LoginForm onSubmit={handleLogin} />
    </div>
  );
}

export default LoginPage;
