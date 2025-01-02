<template>
  <div class="container">
    <h2 class="title">Create Address</h2>
    <form @submit.prevent="submitAddress" class="form">
      <div class="input-group">
        <label for="street">Street</label>
        <input type="text" id="street" v-model="address.street" required />
      </div>
      <div class="input-group">
        <label for="city">City</label>
        <input type="text" id="city" v-model="address.city" required />
      </div>
      <button type="submit" class="btn-submit">Create Address</button>
    </form>

    <h2 class="title">All Addresses</h2>
    <ul class="address-list">
      <li v-for="(address, index) in allAddresses" :key="address.id">{{ address.addressId }}, {{ address.street }}, {{ address.city }}</li>
    </ul>

    <div v-if="responseMessage" class="response-message">
      <p>{{ responseMessage }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      address: {
        street: '',
        city: '',
      },
      allAddresses: [],
      responseMessage: '',
    };
  },
  methods: {
    submitAddress() {
      console.log('Creating address:', this.address);
      axios
        .post('http://localhost:8080/api/address/create', this.address)
        .then(() => {
          this.responseMessage = 'Address created successfully.';
          this.address.street = '';
          this.address.city = '';
          this.$emit('addressCreated');
          this.fetchAllAddresses(); // Reload the address list
        })
        .catch((error) => {
          this.responseMessage = 'Failed to create address.';
          console.error('Error creating address:', error);
        });
    },
    fetchAllAddresses() {
      axios
        .get('http://localhost:8080/api/address/getAlladdress')
        .then((response) => {
          this.allAddresses = response.data;
        })
        .catch((error) => {
          console.error('Error fetching addresses:', error);
        });
    },
  },
  created() {
    this.fetchAllAddresses();
  },
};
</script>

<style scoped>
.container {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Arial', sans-serif;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.title {
  font-size: 24px;
  margin-bottom: 20px;
  text-align: center;
}

.form {
  display: flex;
  flex-direction: column;
  margin-bottom: 30px;
}

.input-group {
  margin-bottom: 20px;
}

.input-group label {
  font-size: 14px;
  color: #333;
  margin-bottom: 5px;
}

.input-group input {
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 6px;
  font-size: 14px;
  outline: none;
  width: 100%;
}

.input-group input:focus {
  border-color: #007BFF;
}

.btn-submit {
  padding: 12px;
  background-color: #007BFF;
  color: white;
  font-size: 16px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width: 100%;
}

.btn-submit:hover {
  background-color: #0056b3;
}

.address-list {
  list-style-type: none;
  padding: 0;
}

.address-list li {
  padding: 12px;
  background-color: #e9ecef;
  margin-bottom: 10px;
  border-radius: 6px;
}

.response-message {
  margin-top: 20px;
  text-align: center;
  color: #28a745;
}
</style>
