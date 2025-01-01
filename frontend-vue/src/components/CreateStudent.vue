/* eslint-disable */
<template>
    <div>
      <h2>Create Student</h2>
      <form @submit.prevent="submitStudent">
        <div>
          <label for="name">Name</label>
          <input type="text" id="name" v-model="student.name" required />
        </div>
        <div>
          <label for="address">Select Address</label>
          <input type="number" id="address" v-model="student.addressId" required />
        </div>
        <button type="submit">Create Student</button>
      </form>
      <div v-if="responseMessage" class="response-message">
        <p>{{ responseMessage }}</p>
      </div>
    </div>
  </template>
  
  <script>
  import api from '@/services/api';
  
  export default {
    props: ['addresses'],  // Receive address list as a prop from parent component
    data() {
      return {
        student: {
          name: '',
          addressId: null
        },
        responseMessage: ''
      };
    },
    methods: {
      async submitStudent() {
        try {
          const response = await api.post('/api/student/create', this.student);
          this.responseMessage = 'Student created successfully!';
          this.$emit('studentCreated', response.data); // Emit event to update parent
          this.student = { name: '', addressId: null }; // Reset form
        } catch (error) {
          console.error('Error creating student:', error);
          this.responseMessage = 'Failed to create student.';
        }
      }
    }
  };
  </script>
  
  <style scoped>
  .response-message {
    margin-top: 10px;
    color: green;
  }
  </style>
  