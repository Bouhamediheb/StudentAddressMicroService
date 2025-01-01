<template>
  <div id="app">
    <CreateAddress @addressCreated="fetchAddresses" />
    <CreateStudent :addresses="addresses" @studentCreated="fetchStudents" />
    <StudentList :students="students" />
  </div>
</template>

<script>
import CreateAddress from './components/CreateAddress.vue';
import CreateStudent from './components/CreateStudent.vue';
import StudentList from './components/StudentList.vue';
import api from './services/api';

export default {
  name: 'App',
  components: {
    CreateAddress,
    CreateStudent,
    StudentList
  },
  data() {
    return {
      addresses: [],
      students: []
    };
  },
  created() {
    this.fetchAddresses();
    this.fetchStudents();
  },
  methods: {
    async fetchAddresses() {
      try {
        const response = await api.get('/api/address');
        this.addresses = response.data;
      } catch (error) {
        console.error('Error fetching addresses:', error);
      }
    },
    async fetchStudents() {
      try {
        const response = await api.get('/api/student');
        this.students = response.data;
      } catch (error) {
        console.error('Error fetching students:', error);
      }
    }
  }
};
</script>

<style>
</style>
