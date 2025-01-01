<template>
  <div>
    <h2>Create Address</h2>
    <form @submit.prevent="submitAddress">
      <div>
        <label for="street">Street</label>
        <input type="text" id="street" v-model="address.street" required />
      </div>
      <div>
        <label for="city">City</label>
        <input type="text" id="city" v-model="address.city" required />
      </div>

      <button type="submit">Create Address</button>
    </form>
    <div v-if="responseMessage" class="response-message">
      <p>{{ responseMessage }}</p>
    </div>
  </div>
</template>

<script>
import api from '@/services/api';
import axios from 'axios';

export default {
  data() {
    return {
      address: {
        street: '',
        city: '',
      },
      responseMessage: '',
    };
  },
  methods: {
    submitAddress() {
      console.log('Creating address:', this.address);
   axios.post('http://localhost:8080/api/address/create', this.address)
      .then(() => {
        this.responseMessage = 'Address created successfully.';
        this.address.street = '';
        this.address.city = '';
        this.$emit('addressCreated');
      })
      .catch((error) => {
        this.responseMessage = 'Failed to create address.';
        console.error('Error creating address:', error);
      });

      
    },
  },
};
</script>


<style scoped>
.response-message {
  margin-top: 10px;
  color: red; /* Changed to red for error messages */
}
</style>
