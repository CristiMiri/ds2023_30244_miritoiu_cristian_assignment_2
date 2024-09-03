export interface Device {
  serialNumber: string;
  name?: string;
  description?: string;
  status?: string;
  address?: string;
  maxConsumption?: number;
  userId?: number;
}

export const dummyDevices: Device[] = [
  {
    serialNumber: "ABC123",
    description: "Smartphone",
    status: "active",
    address: "123 Main Street",
    maxConsumption: 100,
    userId: 1,
  },
  {
    serialNumber: "XYZ456",
    description: "Laptop",
    status: "inactive",
    address: "456 Elm Street",
    maxConsumption: 150,
    userId: 2,
  },
  // Add more dummy devices as needed
];
