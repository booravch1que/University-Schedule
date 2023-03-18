<template>
    <router-link to="../student"><button class="btn btn-primary back-button">Go back</button></router-link>
<div class="formDiv">
   <form>
       <div class="form-group">
       <label for="studentName">Name</label>
       <input required type="text" class="form-control" id="studentName" placeholder="Student's Full Name"
               v-model="student.name">
     </div>
     <div class="form-group">
        <input required type="email" class="form-control" id="studentEmail" placeholder="Student's Email"
               v-model="student.email">
     </div>
     <div class="form-group">
        <input required type="text" class="form-control" id="studentPhone" placeholder="Student's Phone"
               v-model="student.phone">
     </div>
     
     <div class="form-group">
       <label for="selectDepartment">Select New Student's Group</label>
       <select required v-model="student.groupId" class="custom-select" id="selectFaculty">
           <template v-for="group in groups" :key="group">
           <option :value="group.id">{{group.name}}</option>
           </template>
       </select>
     </div>
     <template v-if="student.name !== '' && student.email !== '' && student.phone !== '' && student.groupId !== ''">
     <button type="submit" class="btn btn-success mb-2" @click="createStudent()">Submit</button>
    </template>
   </form>
   </div>    
   </template>
   <script>
   import http from '../../http-common'
   export default {
       name:"newStudent",
       data(){
           return {
               student: {
                   id:null,
                   name:"",
                   email:"",
                   phone:"",
                   groupId:"",
               },
               students: [],
               groups: []
           }
       },
       methods:{
           createStudent(){
             const data = {
               name: this.student.name,
               email: this.student.email,
               phone: this.student.phone,
               groupId: this.student.groupId
             }
             http.post('student/',data).
             then(response => {
               console.log(response.data);
             })
       },
      getGroups(){
            http.get('group/').then(response => {
               this.groups = response.data
           })
       }
   },
   
  mounted(){
    this.getGroups()
  }
   }
   </script>
   <style scoped>
   .formDiv{
     display:flex;
     justify-content: center;
     padding-top:50px;
   }
   form button{
       text-align:center;
   }
   .back-button{
 margin-left:10px;
 margin-top: 20px;
 position: absolute;
} 
   </style>