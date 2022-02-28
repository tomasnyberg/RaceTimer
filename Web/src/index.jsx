import React from 'react';
import ReactDOM from 'react-dom';
import Registration from './pages/Registration';
import TimeRegistration from './pages/TimeRegistration';
import Configuration from './pages/Configuration';
import Result from './pages/Result';
import Layout from './Layout';
import reportWebVitals from './reportWebVitals';
import { ChakraProvider } from '@chakra-ui/react'
import { BrowserRouter, Route, Routes } from "react-router-dom";

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <ChakraProvider>
        <Layout>
          <Routes>
            <Route path="/" element={<Result />} />
            <Route path="/registrering" element={<Registration />}/>
            <Route path="/tids-registrering" element={<TimeRegistration />}/>
            <Route path="/konfiguration" element={<Configuration />}/>
          </Routes>
        </Layout>
      </ChakraProvider>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
