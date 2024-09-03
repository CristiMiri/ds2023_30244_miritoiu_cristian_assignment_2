import React, { useEffect } from "react";
import DeviceTable from "../Components/DeviceTable";
import { Device } from "../models/device";
import { Container } from "react-bootstrap";
import {
  deleteDevice,
  getAllDevices,
  getDeviceUsers,
} from "../Api/DeviceService";
import { User } from "../models/user";
import { getMessages } from "../Api/MonitoringService";

type Props = {};

const DevicesPage = (props: Props) => {
  const [devices, setDevices] = React.useState<Device[]>([]);
  const [state, setState] = React.useState(false);
  useEffect(() => {
    getAllDevices().then((data) => {
      setDevices(data);
    });
  }, [devices]);
  useEffect(() => {
    getMessages().then((data) => {
      console.log(data);
    });
  }, []);

  const handleDeleteDevice = (id: number) => {
    // deleteDevice(id).then((data) => {
    //   console.log(data);
    // });
    setState(!state);
  };

  return (
    <Container
      fluid
      className="mx-auto mt-5  bg-info rounded border border-dark"
    >
      <h1 className="text-center mt-3 text-primary">Devices</h1>

      {/* <DeviceTable devices={devices} handleDeleteDevice={() => {}}
      
      /> */}
    </Container>
  );
};

export default DevicesPage;
