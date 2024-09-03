import { faHouse } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useEffect } from "react";
import { Button, Container, Nav, Navbar } from "react-bootstrap";
import { useUser } from "../Hooks/UserContext";
import WebSocketComponent from "./WebSocketComponent";
type Props = {};

export const Navigation = (props: Props) => {
  const { user } = useUser();
  const globalUser = localStorage.getItem("user");
  const [, setLogedInUser] = React.useState<Boolean>(false);

  useEffect(() => {
    if (globalUser) {
      setLogedInUser(true);
    }
  }, [globalUser]);

  return (
    <Navbar bg="dark" data-bs-theme="dark">
      <Container>
        <Navbar.Brand href="/">
          <FontAwesomeIcon icon={faHouse} />
          Home
        </Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link href="/devices">Devices</Nav.Link>
          <Nav.Link href="/users">Users</Nav.Link>
          <WebSocketComponent />
        </Nav>
        {user ? (
          <Nav.Link href="/">
            <Button
              variant="outline-danger mx-2"
              onClick={() => {
                localStorage.removeItem("user");
                window.location.reload();
              }}
            >
              Logout
            </Button>
          </Nav.Link>
        ) : (
          <Nav.Link href="/login">
            <Button variant="outline-success">Login</Button>
          </Nav.Link>
        )}
      </Container>
    </Navbar>
  );
};

export default Navigation;
