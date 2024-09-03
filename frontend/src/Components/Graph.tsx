import React, { useEffect, useState } from "react";
import { Line } from "react-chartjs-2";
import { Chart, registerables } from "chart.js";
import { messages } from "../models/message";
import { getMessagesByUserId } from "../Api/MonitoringService";

Chart.register(...registerables);

type Props = {
  userId: string;
};

const Graph = ({ userId }: Props) => {
  const [data, setData] = useState<{ [key: string]: messages[] }>();
  const [selectedDate, setSelectedDate] = useState<Date>(new Date());
  const formattedDate = selectedDate.toISOString().split("T")[0];

  const filterMessagesByDate = (
    messages: messages[],
    selectedDate: Date
  ): messages[] => {
    return messages.filter((item: messages) => {
      const date = new Date(item.timestamp);

      return (
        // date.getFullYear() === selectedDate.getFullYear() &&
        date.getMonth() === selectedDate.getMonth() &&
        date.getDate() === selectedDate.getDate()
      );
    });
  };

  const organizeMessagesByDeviceId = (
    messages: messages[]
  ): { [key: string]: messages[] } => {
    const deviceIdArrays: { [key: string]: messages[] } = {};

    messages.forEach((item: messages) => {
      const deviceId = item.deviceId;

      if (!deviceIdArrays[deviceId]) {
        deviceIdArrays[deviceId] = [];
      }
      deviceIdArrays[deviceId].push(item);
    });

    return deviceIdArrays;
  };

  useEffect(() => {
    getMessagesByUserId(userId).then((data) => {
      if (data.length === 0) {
        setData(undefined);
        return;
      }
      //filter by date
      const filteredMessages = filterMessagesByDate(data, selectedDate);
      if (filteredMessages.length === 0) {
        alert("No data for this date");
        return;
      }

      // Organize filtered messages by deviceId
      const deviceIdArrays = organizeMessagesByDeviceId(filteredMessages);

      setData(deviceIdArrays);
    });
  }, [selectedDate]);

  useEffect(() => {
    if (data) {
      renderLineGraph();
    }
  }, [data]);

  const getRandomColor = () => {
    const letters = "0123456789ABCDEF";
    let color = "#";
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  };

  const renderLineGraph = () => {
    const chartData = {
      labels: data![Object.keys(data!)[0]].map((item) => item.timestamp),
      datasets: Object.keys(data!).map((deviceId) => ({
        label: `Device ${deviceId.substring(0, 8)}`,
        data: data![deviceId].map((item) => item.measurement_value),
        borderColor: getRandomColor(),
        fill: false,
      })),
    };
    const chartOptions = {
      scales: {
        x: {
          title: {
            display: true,
            text: "Time",
            color: "blue",
          },
        },
        y: {
          title: {
            display: true,
            text: "Measured Value",
            color: "red",
          },
        },
      },
    };
    return <Line data={chartData} options={chartOptions} />;
  };

  useEffect(() => {
    if (data) {
      renderLineGraph();
    }
  }, [selectedDate]);

  return (
    <div>
      <input
        type="date"
        className="form-control"
        value={formattedDate}
        onChange={(e) => setSelectedDate(new Date(e.target.value))}
      />
      <h2>Line Chart</h2>
      {data && renderLineGraph()}
    </div>
  );
};

export default Graph;
