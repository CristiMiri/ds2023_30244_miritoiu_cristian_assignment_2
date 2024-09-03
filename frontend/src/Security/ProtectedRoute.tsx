// RouteComponent.tsx

import React from "react";
import { Route, RouteProps } from "react-router-dom";

interface Props {
  // Add your custom props here.
  path: string;
  component: React.FC;
}

const ProtectedRoute: React.FC<Props> = ({ component, path }) => {
  // You can include any logic or data fetching before rendering the component

  return <Route path={path} Component={component} />;
};

export default ProtectedRoute;
