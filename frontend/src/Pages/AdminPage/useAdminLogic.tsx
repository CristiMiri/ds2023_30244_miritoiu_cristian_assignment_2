import React, { useEffect } from "react";
import {
  deleteDevice,
  getAllDevices,
  insertDevice,
  updateDevice,
} from "../../Api/DeviceService";
import {
  deleteUser,
  getAllUsers,
  registerUser,
  updateUser,
} from "../../Api/UserService";
import { User } from "../../models/user";
import { Device } from "../../models/device";

const useAdminLogic = () => {
  const [devices, setDevices] = React.useState<Device[]>([]);
  const [users, setUsers] = React.useState<User[]>([]);
  const handleCreateUser = (user: User) => {
    registerUser(user).then((data) => {
      console.log(data);
    });
  };
  useEffect(() => {
    getAllDevices().then((data) => {
      setDevices(data);
    });
    getAllUsers().then((data) => {
      setUsers(data);
    });
  }, []);

  const handleDeleteUser = (id: number) => {
    console.log(id);
    deleteUser(id).then((data) => {
      console.log(data);
      getAllUsers().then((data) => {
        setUsers(data);
      });
    });
  };

  const handleDeleteDevice = (serialNumber: string) => {
    console.log(serialNumber);
    deleteDevice(serialNumber).then((data) => {
      console.log(data);
      getAllDevices().then((data) => {
        setDevices(data);
      });
    });
  };

  const handleUpdateUser = (user: User) => {
    console.log(user);
    updateUser(user).then((data) => {
      console.log(data);
      getAllUsers().then((data) => {
        setUsers(data);
      });
    });
  };

  const handleUpdateDevice = (device: Device) => {
    console.log(device);
    updateDevice(device).then((data) => {
      console.log(data);
      getAllDevices().then((data) => {
        setDevices(data);
      });
    });
  };

  const handleCreateDevice = (device: Device) => {
    console.log(device);
    insertDevice(device).then((data) => {
      console.log(data);
      getAllDevices().then((data) => {
        setDevices(data);
      });
    });
  };
  return {
    users,
    handleCreateUser,
    handleUpdateUser,
    handleDeleteUser,
    devices,
    handleCreateDevice,
    handleUpdateDevice,
    handleDeleteDevice,
  };
};

export default useAdminLogic;
