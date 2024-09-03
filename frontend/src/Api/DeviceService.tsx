import axios, { AxiosResponse } from "axios";
import { Device } from "../models/device";

const apiBaseUrl = "http://localhost:8081/devices";
const createApiUrl = (endpoint: string) => `${apiBaseUrl}/${endpoint}`;

const handleError = (error: any) => {
  console.error("Error:", error);
  throw error;
};

const handleResponse = (response: AxiosResponse) => {
  console.table(response.data);
  return response.data;
};

//Returns data from the database as an array of devices
export const getAllDevices = async () => {
  return axios
    .get("http://localhost:8081/devices")
    .then(handleResponse)
    .catch(handleError);
};

//Returns data from the database as an array of devices owned by a user
export const getUserDevices = async (id: number) => {
  return axios
    .get(createApiUrl(`getdevicesbyuserid/${id}`))
    .then(handleResponse)
    .catch(handleError);
};

//Returns data from the database device with the given id
export const getDeviceById = async (id: number) => {
  return axios
    .get(createApiUrl(`getdevicebyid/${id}`))
    .then(handleResponse)
    .catch(handleError);
};

//Returns http response from database after inserting a device
//Need to check status code to see if it was successful
export const insertDevice = async (device: Device) => {
  return axios
    .post(createApiUrl("insert"), device)
    .then(handleResponse)
    .catch(handleError);
};

//Returns http response from database after updating a device
//Need to check status code to see if it was successful
export const updateDevice = async (device: Device) => {
  return axios
    .put(createApiUrl("update"), device)
    .then(handleResponse)
    .catch(handleError);
};

//Returns http response from database after deleting a device
//Need to check status code to see if it was successful
export const deleteDevice = async (serialNumber: string) => {
  return axios
    .delete(createApiUrl(`delete/${serialNumber}`))
    .then(handleResponse)
    .catch(handleError);
};

//Returns data from the database as an array of users
export const getDeviceUsers = async () => {
  const apiBaseUrlusers = "http://localhost:8081/device_users";
  return axios.get(apiBaseUrlusers).then(handleResponse).catch(handleError);
};
