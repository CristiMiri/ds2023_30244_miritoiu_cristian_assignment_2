import { useState } from "react";
import {
  Button,
  Form,
  FormControl,
  FormGroup,
  FormSelect,
  Modal,
  ModalBody,
  ModalFooter,
} from "react-bootstrap";
import { Device } from "../models/device";
import { User } from "../models/user";

type Props = {
  show: boolean;
  handleCLose: () => void;
  handleCreateDevice: (formData: Device) => void;
  users: User[];
};

const CreateDeviceModal = ({
  show,
  handleCLose,
  users,
  handleCreateDevice,
}: Props) => {
  const [formData, setFormData] = useState<Device>({} as Device);
  const handleChange = (e: any) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  return (
    <Modal show={show}>
      <ModalBody>
        <Form>
          <FormGroup hidden>
            <Form.Label>Serial Number</Form.Label>
            <FormControl
              type="text"
              name="sericalNumber"
              onChange={handleChange}
            />
          </FormGroup>

          <FormGroup>
            <Form.Label>Name</Form.Label>
            <FormControl type="text" name="name" onChange={handleChange} />
          </FormGroup>

          <FormGroup>
            <Form.Label>Description</Form.Label>
            <FormControl
              type="text"
              name="description"
              onChange={handleChange}
            />
          </FormGroup>

          <FormGroup>
            <Form.Label>Status</Form.Label>
            <FormSelect
              aria-label="Status"
              name="status"
              onChange={handleChange}
            >
              <option value="">Select a status</option>
              <option value="active">Active</option>
              <option value="inactive">Inactive</option>
            </FormSelect>
          </FormGroup>

          <FormGroup>
            <Form.Label>Address</Form.Label>
            <FormControl type="text" name="address" onChange={handleChange} />
          </FormGroup>

          <FormGroup>
            <Form.Label>Max Consumption</Form.Label>
            <FormControl
              type="number"
              name="maxConsumption"
              onChange={handleChange}
            />
          </FormGroup>

          <FormGroup>
            <Form.Label>User ID</Form.Label>
            <FormSelect name="userId" onChange={handleChange}>
              <option value={0}>Select a user</option>
              {users.map((user) => (
                <option key={user.id} value={user.id}>
                  {user.username}
                </option>
              ))}
            </FormSelect>
          </FormGroup>
        </Form>
      </ModalBody>
      <ModalFooter>
        <Button variant="secondary" onClick={handleCLose}>
          Close
        </Button>
        <Button
          variant="success"
          onClick={() => {
            handleCLose();
            handleCreateDevice(formData);
            console.log(formData);
          }}
        >
          Save changes
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export default CreateDeviceModal;
function getDeviceOwners() {
  throw new Error("Function not implemented.");
}
