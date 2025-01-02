<template>
  <div class="home">
    <h1 class="main-title">Student Address Management</h1>

    <div class="section">
      <CreateAddress @addressCreated="fetchAddresses" />
    </div>

    <div class="section">
      <CreateStudent :addresses="addresses" @studentCreated="fetchStudents" />
    </div>

    <div class="section">
      <StudentList :students="students" />
    </div>
  </div>
</template>

<script>
import CreateAddress from '@/components/CreateAddress.vue';
import CreateStudent from '@/components/CreateStudent.vue';
import StudentList from '@/components/StudentList.vue';
import axios from 'axios';

export default {
  name: 'Home',
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
    // Fetch all addresses from the backend
    async fetchAddresses() {
      try {
        const response = await axios.get('http://localhost:8080/api/address/getAlladdress');
        this.addresses = response.data;
      } catch (error) {
        console.error('Error fetching addresses:', error);
      }
    },
    // Fetch all students from the backend
    async fetchStudents() {
      try {
        const response = await axios.get('http://localhost:8080/api/student/getAllStudents');
        this.students = response.data;
      } catch (error) {
        console.error('Error fetching students:', error);
      }
    }
  }
};
</script>

<style scoped>
.home {
  max-width: 900px;
  margin: 0 auto;
  padding: 60px;
  font-family: 'Arial', sans-serif;
  background-color: #fafafa;
  border-radius: 10px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

.main-title {
  text-align: center;
  font-size: 32px;
  margin-bottom: 40px;
  color: #333;
}

.section {
  margin-bottom: 40px;
}

.section-title {
  font-size: 28px;
  margin-bottom: 20px;
  color: #007BFF;
  text-align: center;
}

.section h2 {
  margin-bottom: 25px;
}

ul.student-list {
  padding: 0;
  list-style-type: none;
}

ul.student-list li {
  background-color: #e9ecef;
  padding: 12px;
  margin-bottom: 12px;
  border-radius: 6px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

ul.student-list li strong {
  color: #333;
}

@media (max-width: 768px) {
  .home {
    padding: 40px;
  }

  .main-title {
    font-size: 28px;
  }

  .section-title {
    font-size: 24px;
  }
}
</style>
