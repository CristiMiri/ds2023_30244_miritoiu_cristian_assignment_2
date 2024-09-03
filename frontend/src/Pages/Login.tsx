import React from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { loginUser } from "../Api/UserService";
import { useUser } from "../Hooks/UserContext";
import { User } from "../models/user";

type Props = {};
export const Login = (props: Props) => {
  const { login } = useUser();
  const navigate = useNavigate();
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");

  const handleSubmit = async (event: any) => {
    const userData: User = await loginUser(email, password);
    if (userData !== undefined) {
      login(userData);
      console.log(userData);
      userData.role === "admin" ? navigate("/admin") : navigate("/user");
    }
  };

  return (
    <Container className="mx-auto w-50 mt-5  bg-info rounded border border-dark p-5">
      <Form>
        <Form.Group controlId="formBasicEmail">
          <Form.Label>Email address</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter email"
            required
            onChange={(e) => setEmail(e.target.value)}
          />
          <Form.Control.Feedback type="invalid">
            Please enter a email.
          </Form.Control.Feedback>
        </Form.Group>

        <Form.Group controlId="formBasicPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Password"
            required
            onChange={(e) => setPassword(e.target.value)}
          />
          <Form.Control.Feedback type="invalid">
            Please enter a password.
          </Form.Control.Feedback>
        </Form.Group>

        <div className="text-center">
          <Button
            variant="primary"
            type="button"
            className="mt-3"
            onClick={handleSubmit}
          >
            Login
          </Button>
        </div>
      </Form>
    </Container>
  );
};

export default Login;
