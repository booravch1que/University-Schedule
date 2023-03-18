<template>
    <router-link to="../group"><button class="btn btn-primary back-button">Go back</button></router-link>
<div class="formDiv">
   <form>
       <div class="form-group">
       <label for="groupName">Name</label>
       <input required type="text" class="form-control" id="groupName" placeholder="Group Full Name"
               v-model="group.name">
     </div>
     <div class="form-group">
       <label for="groupCourse">Course</label>
       <br>
       <select v-model="group.course">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
       </select>
     </div>
     <div class="form-group">
       <label for="selectDepartment">Select New Group's Department</label>
       <select required v-model="group.departmentId" class="custom-select" id="selectFaculty">
           <template v-for="department in departments" :key="department">
           <option :value="department.id">{{department.name}}</option>
           </template>
       </select>
     </div>
     <template v-if="group.name !== '' && group.course !== '' && group.departmentId !== ''">
     <button type="submit" class="btn btn-success mb-2" @click="createGroup()">Submit</button>
    </template>
    </form>
   </div>    
   </template>
   <script>
   import http from '../../http-common'
   export default {
       name:"newGroup",
       data(){
           return {
               group: {
                   id:null,
                   name:"",
                   course:"",
                   departmentId:"",
               },
               students: [],
               departments: []
           }
       },
       methods:{
           createGroup(){
             const data = {
               name: this.group.name,
               course: this.group.course,
               departmentId: this.group.departmentId
             }
             http.post('group/',data).
             then(response => {
               console.log(response.data);
             })
       },
      getDepartments(){
            http.get('department/').then(response => {
               this.departments = response.data
           })
       }
   },
   
  mounted(){
    this.getDepartments()
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
