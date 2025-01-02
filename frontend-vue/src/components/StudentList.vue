<template>
  <div class="container">
    <h2 class="title">List of Students</h2>
    <ul class="student-list">
      <li v-for="student in students" :key="student.id">
        <strong>{{ student.firstName }} {{ student.lastName }}</strong> - {{ student.addressResponse.street }}, {{ student.addressResponse.city }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  props: ['students']
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

.student-list {
  list-style-type: none;
  padding: 0;
}

.student-list li {
  padding: 12px;
  background-color: #e9ecef;
  margin-bottom: 10px;
  border-radius: 6px;
}
</style>
