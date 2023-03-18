<template>
    <div class="navigationDiv" > 
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <router-link to="/" class="navbar-brand">KPI-SCHEDULE</router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/faculty" class="nav-link">Faculties</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/department" class="nav-link">Departments</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/discipline" class="nav-link">Disciplines</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/teacher" class="nav-link">Teachers</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/group" class="nav-link">Groups</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/student" class="nav-link">Students</router-link>
        </li>
        
      </div>
    </nav>
  </div>
   
</template>