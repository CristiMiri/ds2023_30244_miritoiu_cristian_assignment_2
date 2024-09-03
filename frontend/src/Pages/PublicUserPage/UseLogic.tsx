import React, { useEffect, useState } from "react";
import { User } from "../../models/user";
import { useUser } from "../../Hooks/UserContext";
import { getUserDevices } from "../../Api/DeviceService";
import { deleteUser, getUserById, updateUser } from "../../Api/UserService";
import { Device } from "../../models/device";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
const UseLogic = () => {
  //   const { user, login, logout } = useUser();
  const [user, setUser] = useState<User | null>(null);
  const { userId } = useParams<{ userId: string }>();
  useEffect(() => {
    getUserById(Number.parseInt(userId!)).then((data) => {
      setFormData(data);
      setUser(data);
    });
  }, []);
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
    getUserDevices(Number.parseInt(userId!)).then((data) => {
      setDevices(data);
    });
  }, []);
  const handleUpdate = () => {
    // setEditable(false);
    const { id, username, password, email, firstName, lastName, role } =
      formData;
    console.log(formData);

    updateUser(formData).then((data) => {
      console.log(data);
    });
  };
  const handleDelete = () => {
    deleteUser(user!.id!).then((data) => {
      console.log(data);
    });

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
