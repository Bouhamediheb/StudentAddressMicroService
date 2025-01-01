import axios from 'axios';

// Set up axios instance
const api = axios.create({
  baseURL: 'http://apigateway:8080', // Use the correct IP and port for your API Gateway
});

export default api;
