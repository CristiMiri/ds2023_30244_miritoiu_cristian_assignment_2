import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getUserDevices } from "../../Api/DeviceService";
import { deleteUser, updateUser } from "../../Api/UserService";
import { useUser } from "../../Hooks/UserContext";
import { Device } from "../../models/device";
import { User } from "../../models/user";

const UseLogic = () => {
  const { user, login, logout } = useUser();
  const navigate = useNavigate();
  const [devices, setDevices] = useState<Device[]>([]);
  const [formData, setFormData] = useState<User>({
    id: user?.id || 0,
    firstName: user?.firstName || "",
    lastName: user?.lastName || "",
    username: user?.username || "",
    password: user?.password || "*****",
    email: user?.email || "",
    role: user?.role || "",
  });
  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;

    setFormData({
      ...formData,
      [name]: value,
    });
    console.log(formData);
  };
  useEffect(() => {
    getUserDevices(user!.id!).then((data) => {
      setDevices(data);
    });
  }, [user]);
  const handleUpdate = () => {
    // setEditable(false);
    console.log(formData);
    login(formData!);
    updateUser(formData).then((data) => {
      console.log(data);
    });
  };
  const handleDelete = () => {
    deleteUser(user!.id!).then((data) => {
      console.log(data);
    });
    logout();
    navigate("/");
  };
  return {
    formData,
    devices,
    handleInputChange,
    handleUpdate,
    handleDelete,
  };
};

export default UseLogic;
