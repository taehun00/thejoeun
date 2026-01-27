// pages/_app.js
import React from "react";
import { wrapper } from "../store/configureStore";

import AppLayout from "../components/AppLayout"; 

import "antd/dist/antd.css";
import "../styles/global.css";

function MyApp({ Component, pageProps }) {
  return (
    <AppLayout>
      <Component {...pageProps} />
    </AppLayout>
  );
}

export default wrapper.withRedux(MyApp);