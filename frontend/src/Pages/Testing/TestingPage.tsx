import React, { useEffect, useState } from "react";
import {
  getMessages,
  getMessagesBySerialNumber,
} from "../../Api/MonitoringService";
import Graph from "../../Components/Graph";
import WebSocketComponent from "../../Components/WebSocketComponent";

type Props = {};

const TestingPage = (props: Props) => {
  return (
    <div>
      <h1>React Chart.js Example</h1>
      <Graph userId="3" />
    </div>
  );
};

export default TestingPage;
