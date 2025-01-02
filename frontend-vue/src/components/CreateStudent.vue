<template>
  <div class="container">
    <h2 class="title">Create Student</h2>
    <form @submit.prevent="submitStudent" class="form">
      <div class="input-group">
        <label for="name">First Name</label>
        <input type="text" id="name" v-model="student.firstName" required />
      </div>
      <div class="input-group">
        <label for="lastName">Last Name</label>
        <input type="text" id="lastName" v-model="student.lastName" required />
      </div>
      <!-- email -->
      <div class="input-group">
        <label for="email">Email</label>
        <input type="email" id="email" v-model="student.email" required />
      </div>
      <div class="input-group">
        <label for="address">Select Address</label>
        <select v-model="student.addressId" required>
          <option v-for="address in addresses" :key="address.addressId" :value="address.addressId">
            {{ address.street }}, {{ address.city }}
          </option>
        </select>
      </div>
      <button type="submit" class="btn-submit">Create Student</button>
    </form>
    <div v-if="responseMessage" class="response-message">
      <p>{{ responseMessage }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: ['addresses'],
  data() {
    return {
      student: {
        firstName: '',
        lastName: '',
        email: '',
        addressId: null
      },
      responseMessage: ''
    };
  },
  methods: {
    async submitStudent() {
      try {
        const response = await axios.post('http://localhost:8080/api/student/create', this.student);
        this.responseMessage = 'Student created successfully!';
        
        // Emit the event to the parent to refresh the student list
        this.$emit('studentCreated', response.data);
        
        // Reset form fields after successful submission
        this.student = { firstName: '', lastName: '', email: '', addressId: null };
      } catch (error) {
        console.error('Error creating student:', error);
        this.responseMessage = 'Failed to create student.';
      }
    }
  }
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

.input-group input,
.input-group select {
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 6px;
  font-size: 14px;
  outline: none;
  width: 100%;
}

.input-group input:focus,
.input-group select:focus {
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

.response-message {
  margin-top: 20px;
  text-align: center;
  color: #28a745;
}
</style>
