import { faPlug, faUserPlus } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Accordion, Button, Container } from "react-bootstrap";
import DeviceTable from "../../Components/DeviceTable";
import UserTable from "../../Components/UserTable";
import useAdminLogic from "./useAdminLogic";
import UserForm from "../../Components/UpdateUserModal";

import { useState } from "react";
import CreateUserModal from "../../Components/CreateUserModal";
import CreateDeviceModal from "../../Components/CreateDeviceModal";

type Props = {};

const AdminPage = (props: Props) => {
  const {
    devices,
    users,
    handleCreateUser,
    handleDeleteDevice,
    handleDeleteUser,
    handleUpdateDevice,
    handleUpdateUser,
    handleCreateDevice,
  } = useAdminLogic();
  const [showUserModal, setShowUserModal] = useState(false);
  const [show, setShow] = useState(false);
  const handleShow = () => setShowUserModal(true);
  const handleClose = () => setShowUserModal(false);
  // setTimeout(() => {
  //   window.location.reload();
  // }, 10_000);
  return (
    <>
      <div className="d-flex justify-content-end ">
        <Button
          variant="success"
          className="mt-4 me-2 "
          onClick={() => {
            setShow(true);
          }}
        >
          Create device
          <FontAwesomeIcon className="ms-1" icon={faPlug} />
        </Button>
        <Button
          variant="success"
          className="mt-4 me-2"
          onClick={() => {
            setShowUserModal(true);
          }}
          // onClick={() => {
          //   setShow(true);
          // }}
        >
          Create user
          <FontAwesomeIcon className="ms-1" icon={faUserPlus} />
        </Button>
      </div>
      <CreateDeviceModal
        show={show}
        handleCLose={() => {
          setShow(false);
        }}
        users={users}
        handleCreateDevice={handleCreateDevice}
      />
      <CreateUserModal
        show={showUserModal}
        handleClose={handleClose}
        handleCreateUser={handleCreateUser}
      />
      <Container
        fluid
        className="mx-auto mt-2  bg-info rounded border border-dark"
      >
        <Accordion>
          <Accordion.Item eventKey="0">
            <Accordion.Header>Devices</Accordion.Header>
            <Accordion.Body>
              <DeviceTable
                devices={devices}
                users={users}
                handleUpdateDevice={handleUpdateDevice}
                handleDeleteDevice={handleDeleteDevice}
              />
            </Accordion.Body>
          </Accordion.Item>
        </Accordion>
      </Container>
      <Container
        fluid
        className="mx-auto mt-2  bg-info rounded border border-dark"
      >
        <Accordion>
          <Accordion.Item eventKey="0">
            <Accordion.Header>Users</Accordion.Header>
            <Accordion.Body>
              <UserTable
                users={users}
                handleDeleteUser={handleDeleteUser}
                handleUpdateUser={handleUpdateUser}
                handleSave={() => void 0}
              />
            </Accordion.Body>
          </Accordion.Item>
        </Accordion>
      </Container>
    </>
  );
};

export default AdminPage;
