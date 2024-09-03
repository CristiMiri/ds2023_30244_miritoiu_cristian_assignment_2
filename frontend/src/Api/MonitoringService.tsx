// export default WebSocketComponent;
import axios, { AxiosResponse } from "axios";

const api = axios.create({
  baseURL: "http://localhost:8082",
});

const handleError = (error: any) => {
  console.error("Error:", error);
  throw error;
};

const handleResponse = (response: AxiosResponse) => {
  // for every item convert the date to a readable format
  response.data.forEach((item: any) => {
    const date = new Date(item.timestamp);
    item.timestamp = date.toLocaleString("en-US", {
      day: "2-digit",
      month: "2-digit",
      hour: "2-digit",
      minute: "2-digit",
      hour12: false,
    });
    item.measurement_value = Number(item.measurement_value);
  });
  console.table(response.data);
  return response.data;
};

export const getMessages = async () => {
  return api.get("/messages").then(handleResponse).catch(handleError);
};

export const getMessagesBySerialNumber = async (serialNumber: string) => {
  return api
    .get(`/messages/${serialNumber}`)
    .then(handleResponse)
    .catch(handleError);
};

export const getMessagesByUserId = async (userId: string) => {
  return api
    .get(`/messages/user/${userId}`)
    .then(handleResponse)
    .catch(handleError);
};
