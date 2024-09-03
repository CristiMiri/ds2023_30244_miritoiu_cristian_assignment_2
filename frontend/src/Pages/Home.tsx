import { Button, Container } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

type Props = {};

export const Home = (props: Props) => {
  const navigate = useNavigate();
  return (
    <Container className="mt-5">
      <h1>Welcome to Energy Monitor</h1>
      <p>
        Monitor and manage your energy consumption with our innovative energy
        monitoring app.
      </p>
      <p>
        Keep track of your electricity usage, set goals to save energy, and
        contribute to a sustainable future.
      </p>
      <p>
        <Button
          variant="primary"
          onClick={() => {
            navigate("/login");
          }}
        >
          Get Started
        </Button>
      </p>
    </Container>
  );
};

export default Home;
