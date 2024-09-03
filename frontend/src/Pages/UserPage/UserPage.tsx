import { useEffect, useState } from "react";
import {
  Accordion,
  Button,
  Col,
  Container,
  Form,
  FormControl,
  FormGroup,
  Row,
} from "react-bootstrap";
import ConfirmModal from "../../Components/ConfirmModal";
import DeviceTable from "../../Components/DeviceTable";
import Graph from "../../Components/Graph";
import UseLogic from "./UseLogic";
type Props = {};

const Profile = (props: Props) => {
  const [editable, setEditable] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const handleShow = () => setShowModal(true);
  const handleClose = () => setShowModal(false);

  const { formData, devices, handleInputChange, handleUpdate, handleDelete } =
    UseLogic();
  const handleConfirm = () => {
    handleDelete();
    console.log("Confirmed!");
  };

  return (
    <>
      <Container className="mx-auto mt-5  bg-info rounded border border-dark">
        <Row className="justify-content-between">
          <h2 className="col-6">Welcome {formData!.firstName}</h2>
          <Col className="col-6 d-flex justify-content-end">
            {editable ? (
              <Button
                className="col-3 mx-1"
                onClick={() => {
                  setEditable(false);
                  handleUpdate();
                }}
              >
                Save Changes
              </Button>
            ) : (
              <Button className="col-3 mx-1" onClick={() => setEditable(true)}>
                Update Profile
              </Button>
            )}
            <Button
              className="col-3 mx-1 "
              variant="danger"
              onClick={handleShow}
            >
              Delete Profile
            </Button>
            <ConfirmModal
              show={showModal}
              onClose={handleClose}
              onConfirm={handleConfirm}
            />
          </Col>
        </Row>
        <Form className="mb-3">
          <Row className="mb-3">
            <FormGroup as={Col}>
              <Form.Label>Username</Form.Label>
              <FormControl
                type="text"
                name="username"
                defaultValue={formData.username}
                onChange={handleInputChange}
                readOnly
              />
            </FormGroup>
            <FormGroup as={Col}>
              <Form.Label>First Name</Form.Label>
              <FormControl
                type="text"
                name="firstName"
                defaultValue={formData.firstName}
                onChange={handleInputChange}
                readOnly={!editable}
              />
            </FormGroup>
            <FormGroup as={Col}>
              <Form.Label>Last Name</Form.Label>
              <FormControl
                type="text"
                name="lastName"
                defaultValue={formData.lastName}
                onChange={handleInputChange}
                readOnly={!editable}
              />
            </FormGroup>
          </Row>
          <Row className="mb-">
            <FormGroup as={Col}>
              <Form.Label>Email</Form.Label>
              <FormControl
                type="text"
                name="email"
                defaultValue={formData.email}
                onChange={handleInputChange}
                readOnly={!editable}
              />
            </FormGroup>
            <FormGroup as={Col}>
              <Form.Label>Password</Form.Label>
              <FormControl
                type="password"
                name="password"
                defaultValue={formData.password}
                onChange={handleInputChange}
                readOnly={!editable}
              />
            </FormGroup>

            <FormGroup as={Col}>
              <Form.Label>Role</Form.Label>
              <Form.Select name="role" disabled defaultValue={formData.role}>
                <option value="admin">Admin</option>
                <option value="user">User</option>
              </Form.Select>
            </FormGroup>
          </Row>
        </Form>
      </Container>
      <Container className="mx-auto mt-1  bg-info rounded border border-dark">
        <Accordion>
          <Accordion.Item eventKey="0">
            <Accordion.Header>Owned Devices</Accordion.Header>
            <Accordion.Body>
              <DeviceTable
                devices={devices}
                handleDeleteDevice={() => void 0}
                handleUpdateDevice={() => void 0}
                users={[]}
              />
            </Accordion.Body>
          </Accordion.Item>
        </Accordion>
      </Container>
      <Container className="mx-auto mt-1  bg-info rounded border border-dark">
        <Accordion>
          <Accordion.Item eventKey="0">
            <Accordion.Header>Energy consumption</Accordion.Header>
            <Accordion.Body>
              <Graph userId={String(formData.id)} />
            </Accordion.Body>
          </Accordion.Item>
        </Accordion>
      </Container>
    </>
  );
};

export default Profile;
