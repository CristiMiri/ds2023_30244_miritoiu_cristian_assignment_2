/*
import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddDevice() {
  let navigate = useNavigate();

  const [device, setDevice] = useState({
    description: "",
    address: "",
    maxEnergyConsPerHour: "",
    userId: "",
  });

  const { description, address, maxEnergyConsPerHour, userId } = device;

  const onInputChange = (e) => {
    setDevice({ ...device, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:8081/devices", device);
    navigate("/Home");
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Register Device</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Description" className="form-label">
                Name
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter the description"
                name="description"
                value={description}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Address" className="form-label">
                Address
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter the address"
                name="address"
                value={address}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="maxEnergyConsPerHour" className="form-label">
                Maximum Energy Consumption / Hour
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter the maximum energy consumption"
                name="maxEnergyConsPerHour"
                value={maxEnergyConsPerHour}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="userId" className="form-label">
                User
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter the user id"
                name="userId"
                value={userId}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/Home">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
*/

// -------------------------------------------------------------

/*
import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditDevice() {
  let navigate = useNavigate();

  const { id } = useParams();

  const [device, setDevice] = useState({
    description: "",
    address: "",
    maxEnergyConsPerHour: "",
    userId: "",
  });

  const { description, address, maxEnergyConsPerHour, userId } = device;

  const onInputChange = (e) => {
    setDevice({ ...device, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    loadDevice();
  }, []);

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.put(`http://localhost:8081/devices/${id}`, device);
    navigate("/Home");
  };

  const loadDevice = async () => {
    const result = await axios.get(`http://localhost:8081/devices/${id}`);
    setDevice(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Edit Device</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Description" className="form-label">
                Name
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter the description"
                name="description"
                value={description}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Address" className="form-label">
                Address
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter the address"
                name="address"
                value={address}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="maxEnergyConsPerHour" className="form-label">
                Maximum Energy Consumption / Hour
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter the maximum energy consumption"
                name="maxEnergyConsPerHour"
                value={maxEnergyConsPerHour}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="userId" className="form-label">
                User
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter the user id"
                name="userId"
                value={userId}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/Home">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
*/

// ----------------------------------------------------------------

/*
import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

export default function ViewDevice() {
  const [device, setDevice] = useState({
    description: "",
    address: "",
    maxEnergyConsPerHour: "",
    userId: "",
  });

  const { id } = useParams();

  useEffect(() => {
    loadDevice();
  }, []);

  const loadDevice = async () => {
    const result = await axios.get(`http://localhost:8081/devices/${id}`);
    setDevice(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Device Details</h2>

          <div className="card">
            <div className="card-header">
              Details of device : {device.id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Description:</b>
                  {device.description}
                </li>
                <li className="list-group-item">
                  <b>Address:</b>
                  {device.address}
                </li>
                <li className="list-group-item">
                  <b>Maximum Energy Consumption Per Hour:</b>
                  {device.maxEnergyConsPerHour}
                </li>
                <li className="list-group-item">
                  <b>User Id:</b>
                  {device.userId}
                </li>
              </ul>
            </div>
          </div>
          <Link className="btn btn-primary my-2" to={"/Home"}>
            Back to Home
          </Link>
        </div>
      </div>
    </div>
  );
}
*/
