import { faTrash, faUserPen } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React from "react";
import { Button, Table } from "react-bootstrap";
import { Device } from "../models/device";
import { User } from "../models/user";
import UpdateDeviceModal from "./UpdateDeviceModal";
type Props = {
  devices: Device[];
  users: User[];
  handleDeleteDevice: (serialNumber: string) => void;
  handleOpenForm?: () => void;
  handleUpdateDevice: (device: Device) => void;
};

const DeviceTable = ({
  devices,
  handleDeleteDevice,
  handleUpdateDevice,
  users,
}: Props) => {
  const [show, setShow] = React.useState(false);
  const [selectedDevice, setSelectedDevice] = React.useState<Device>({
    serialNumber: "",
    description: "",
    status: "",
    address: "",
    maxConsumption: 0,
    userId: 0,
  });
  const handleCLose = () => {
    console.log(selectedDevice);
    setShow(false);
  };

  return (
    <>
      <UpdateDeviceModal
        show={show}
        users={users}
        selectedDevice={selectedDevice}
        handleCLose={handleCLose}
        handleUpdateDevice={handleUpdateDevice}
      />
      <Table striped bordered hover responsive>
        <thead>
          <tr>
            <th>Serial Number</th>
            <th>Description</th>
            <th>Status</th>
            <th>Address</th>
            <th>Max Consumption</th>
            <th>User Name </th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {devices.map((device) => (
            <tr key={device.serialNumber}>
              <td>{device.serialNumber}</td>
              <td>{device.description || "-"}</td>
              <td>{device.status || "-"}</td>
              <td>{device.address || "-"}</td>
              <td>{device.maxConsumption || "-"}</td>
              <td>
                {users.find((user) => user.id === device.userId)?.username ||
                  "-"}
              </td>

              <td className="w-10">
                <Button
                  className="me-2"
                  variant="danger"
                  onClick={() => handleDeleteDevice(device.serialNumber)}
                >
                  <FontAwesomeIcon icon={faTrash} />
                </Button>
                <Button
                  variant="warning"
                  className="me-2"
                  onClick={() => {
                    setSelectedDevice(device);
                    setShow(true);
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

export default DeviceTable;
