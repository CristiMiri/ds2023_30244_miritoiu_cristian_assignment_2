import React, { useEffect, useState } from "react";
import {
  Button,
  Form,
  FormControl,
  FormGroup,
  Modal,
  ModalBody,
  ModalFooter,
} from "react-bootstrap";
import { User } from "../models/user";

type Props = {
  show: boolean;
  selectUser: User;
  handleClose: () => void;
  handleUpdateUser: (user: User) => void;
};

const UpdateUserModal = ({
  show,
  selectUser,
  handleClose,
  handleUpdateUser,
}: Props) => {
  const [validated, setValidated] = useState(false);
  const [formData, setFormData] = useState<User>(selectUser);
  const handleChange = (e: any) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    setFormData(selectUser);
  }, [selectUser]);

  const handleSubmit = (event: any) => {
    const form = event.currentTarget;
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    }
    setValidated(true);
  };

  return (
    <Modal show={show} centered>
      <ModalBody>
        <Form noValidate validated={validated} onSubmit={handleSubmit}>
          <FormGroup>
            <Form.Label>Username</Form.Label>
            <FormControl
              type="text"
              name="username"
              defaultValue={formData.username}
              readOnly
              onChange={handleChange}
            />
            <Form.Control.Feedback type="invalid">
              Please provide a valid username.
            </Form.Control.Feedback>
          </FormGroup>

          <FormGroup>
            <Form.Label>Password</Form.Label>
            <FormControl
              type="password"
              name="password"
              required
              onChange={handleChange}
            />
            <Form.Control.Feedback type="invalid">
              Please provide a valid password.
            </Form.Control.Feedback>
          </FormGroup>

          <FormGroup>
            <Form.Label>Email</Form.Label>
            <FormControl
              type="text"
              name="email"
              required
              defaultValue={formData.email}
              onChange={handleChange}
            />
          </FormGroup>

          <FormGroup>
            <Form.Label>firstName</Form.Label>
            <FormControl
              type="text"
              name="firstName"
              defaultValue={formData.firstName}
              onChange={handleChange}
            />
          </FormGroup>
          <FormGroup>
            <Form.Label>lastName</Form.Label>
            <FormControl
              type="text"
              name="lastName"
              defaultValue={formData.lastName}
              onChange={handleChange}
            />
          </FormGroup>

          <FormGroup>
            <Form.Label>Role</Form.Label>
            <Form.Select
              aria-label="Role"
              name="role"
              onChange={handleChange}
              defaultValue={formData.role}
            >
              <option value="admin">Admin</option>
              <option value="user">User</option>
            </Form.Select>
          </FormGroup>
        </Form>
      </ModalBody>
      <ModalFooter>
        <Button variant="secondary" onClick={handleClose}>
          Close
        </Button>
        <Button
          variant="success"
          onClick={() => {
            const user = {
              ...formData,
              id: selectUser.id,
            };
            console.log(user);

            handleUpdateUser(user);
            handleClose();
          }}
        >
          Save changes
        </Button>
      </ModalFooter>
    </Modal>
  );
};

export default UpdateUserModal;
