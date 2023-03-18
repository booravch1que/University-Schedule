<template>
    <router-link to="../teacher"><button class="btn btn-primary back-button">Go back</button></router-link>
 <div class="formDiv">
 <form>
     <div class="form-group">
     <label for="TeacherName">Name</label>
     <input required type="text" class="form-control" id="TeacherName" placeholder="Teacher's Name"
             v-model="teacher.name">
    <label for="TeacherSurname">Surname</label>
    <input required type="text" class="form-control" id="TeacherSurname" placeholder="Teacher's Surname"
             v-model="teacher.surname">
    <label for="TeacherEmail">Email</label>
    <input required type="email" class="form-control" id="TeacherEmail" placeholder="Teacher's Email"
             v-model="teacher.email">  
     <label for="TeacherPhone">Phone</label>
    <input required type="text" class="form-control" id="TeacherPhone" placeholder="Teacher's Phone"
             v-model="teacher.phone">  


   </div>
   <template v-if="teacher.name !== '' && teacher.surname !== '' && teacher.email !== '' && teacher.phone !== ''">   
    <button type="submit" class="btn btn-success mb-2" @click="createTeacher()">Submit</button>
  </template>
  </form>
 </div>    
 </template>
 <script>
 import http from '../../http-common'
 export default {
     name:"newTeacher",
     data(){
         return {
             teacher: {
                 id:null,
                 name:"",
                 surname:"",
                 email:"",
                 phone:""
             }, 
         }
     },
     methods:{
         createTeacher(){
           const data = {
             name: this.teacher.name,
             surname: this.teacher.surname,
             email: this.teacher.email,
             phone: this.teacher.phone,
           }
           http.post('teacher/',data).
           then(response => {
             console.log(response.data);
           })
     }
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
 