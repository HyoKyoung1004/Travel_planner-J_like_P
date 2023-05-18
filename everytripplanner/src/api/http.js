import axios from "axios";

function tripInstance() {
  const instance = axios.create({
    baseURL: "http://localhost:9999/trip",
    headers: {
      // "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
} 

export {tripInstance }