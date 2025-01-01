/* eslint-disable */
<template>
    <div>
      <h2>List of Students</h2>
      <ul>
        <li v-for="student in students" :key="student.id">
          <strong>{{ student.name }}</strong> - {{ student.address.street }}, {{ student.address.city }}
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    props: ['students'],
  };
  </script>
  
  <style scoped>
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    margin: 10px 0;
  }
  </style>
  