import axios from 'axios';

// Set up axios instance
const api = axios.create({
  baseURL: process.env.VUE_APP_API_URL
});

export default api;
