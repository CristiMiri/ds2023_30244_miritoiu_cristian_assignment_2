import React, { useEffect } from "react";
import UserTable from "../Components/UserTable";
import { User } from "../models/user";
import { deleteUser, getAllUsers, registerUser } from "../Api/UserService";

type Props = {};

const UserPage = (props: Props) => {
  const [users, setUsers] = React.useState([]);

  const handleSave = (user: User) => {
    registerUser(user).then((data) => {
      console.log(data);
    });
  };
  const handleDeleteUser = (id: number) => {
    deleteUser(id).then((data) => {
      console.log(data);
    });
  };

  useEffect(() => {
    getAllUsers().then((data) => {
      setUsers(data);
    });
  }, [users]);

  function handleUpdateUser(user: User): void {
    // setShow(true);
    // updateUser(user).then((data) => {
    //   console.log(data);
    // });
  }

  return (
    <div className="">
      <h1 className="text-center mt-3 text-primary">Users</h1>
      <UserTable
        users={users}
        handleDeleteUser={handleDeleteUser}
        // handleAddUser={() => {
        //   // setShow(true);
        // }}
        handleUpdateUser={handleUpdateUser}
        handleSave={handleSave}
      />
    </div>
  );
};

export default UserPage;
