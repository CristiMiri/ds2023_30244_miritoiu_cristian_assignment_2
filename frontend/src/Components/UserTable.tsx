import { faTrash, faUserPen } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import { Button, Table } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { User } from "../models/user";
import UpdateUserModal from "./UpdateUserModal";

type Props = {
  users: User[];
  handleDeleteUser: (id: number) => void;
  handleUpdateUser: (user: User) => void;
  handleSave: (user: User) => void;
};

const UserTable = ({ users, handleDeleteUser, handleUpdateUser }: Props) => {
  const [show, setShow] = React.useState(false);
  const [selectedUser, setSelectedUser] = React.useState<User>({} as User);
  const handleClose = () => setShow(false);
  const navigate = useNavigate();
  return (
    <>
      <UpdateUserModal
        show={show}
        selectUser={selectedUser}
        handleClose={handleClose}
        handleUpdateUser={handleUpdateUser}
      />
      <Table striped bordered hover>
        <thead>
          <tr>
            <th></th>
            <th>Username</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Role</th>
            <th></th>
          </tr>
        </thead>
        <tbody className="table-group-divider">
          {users.map((RowUser, index) => (
            <tr key={RowUser.id}>
              {/* <td>{index + 1}</td> */}
              <td>{RowUser.id}</td>
              <td
                onClick={() => {
                  setSelectedUser(RowUser);
                  navigate("/profile/" + users[index].id + "");
                  console.log(users[index].id);
                }}
              >
                {RowUser.username}
              </td>
              <td>{RowUser.firstName || "N/A"}</td>
              <td>{RowUser.lastName || "N/A"}</td>
              <td>{RowUser.email || "N/A"}</td>
              <td>{RowUser.role || "N/A"}</td>
              <td className="w-10">
                <Button
                  className="me-2"
                  variant="danger"
                  onClick={() => handleDeleteUser(RowUser.id || -1)}
                >
                  <FontAwesomeIcon icon={faTrash} />
                </Button>
                <Button
                  variant="warning"
                  className="me-2"
                  onClick={() => {
                    setSelectedUser(RowUser);

                    setShow(true);
                    console.log(RowUser);
                  }}
                >
                  <FontAwesomeIcon icon={faUserPen} />
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </>
  );
};

export default UserTable;
