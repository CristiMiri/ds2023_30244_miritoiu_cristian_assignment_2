import axios, { AxiosResponse } from "axios";
import { User } from "../models/user";

const apiBaseUrl = "http://localhost:8080/users";
const createApiUrl = (endpoint: string) => `${apiBaseUrl}/${endpoint}`;

const handleError = (error: any) => {
  console.error("Error:", error);
  throw error;
};

const handleResponse = (response: AxiosResponse) => {
  console.table(response.data);
  return response.data;
};

//localhost:8080/users
//Returns data from the database users as an array of users
export const getAllUsers = async () => {
  return axios
    .get("http://localhost:8080/users")
    .then(handleResponse)
    .catch(handleError);
};

//Returns data from the database user with the given id
export const getUserById = async (id: number) => {
  return axios
    .get(createApiUrl(`getuserbyid/${id}`))
    .then(handleResponse)
    .catch(handleError);
};

//Returns http response from database after inserting a user
//Need to check status code to see if it was successful
export const registerUser = async (user: User) => {
  return axios
    .post(createApiUrl("register"), user)
    .then(handleResponse)
    .catch(handleError);
};

//Returns http response from database after updating a user
//Need to check status code to see if it was successful
export const updateUser = async (user: User) => {
  return axios
    .put(createApiUrl("update"), user)
    .then(handleResponse)
    .catch(handleError);
};

//Returns http response from database after deleting a user
//Need to check status code to see if it was successful
export const deleteUser = async (id: number) => {
  return axios
    .delete(createApiUrl(`delete/${id}`))
    .then(handleResponse)
    .catch(handleError);
};

export const loginUser = async (email: string, password: string) => {
  const userData = {
    email: email,
    password: password,
  };
  const url = "http://localhost:8080/users/login";
  return axios
    .post(url, userData)
    .then((response) => {
      // console.log(response);
      if (response.status === 200) {
        return response.data;
      } else {
        alert("Invalid email or password");
      }
    })
    .catch((error) => {
      alert("Invalid email or password");
    });
};
