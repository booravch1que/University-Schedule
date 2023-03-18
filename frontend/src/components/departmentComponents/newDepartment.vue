<template>
     <router-link to="../department"><button class="btn btn-primary back-button">Go back</button></router-link>
<div class="formDiv">
    <form>
        <div class="form-group">
        <label for="departmentName">Name</label>
        <input required type="text" class="form-control" id="departmentName" placeholder="Department Full Name"
                v-model="department.name">  
      </div>
      <div class="form-group">
        <label for="departmentShortName">ShortName</label>
        <input required  type="text" class="form-control" id="departmentShortName" placeholder="Abbriviation or Short Name (Up to 5 characters)" maxlength="5"
                v-model="department.shortName">
      </div>
      <div class="form-group">
        <label for="selectFaculty">Select New Department's Faculty</label>
        <select required v-model="department.facultyId" class="custom-select" id="selectFaculty">
            <template v-for="faculty in faculties" :key="faculty">
            <option :value="faculty.id">{{faculty.name}}</option>
            </template>
        </select>
      </div>
      <template v-if="department.name !== '' && department.shortName !== '' 
        && department.facultyId !== '' ">
            <button  class="btn btn-success mb-2" @click="createDepartment()">Submit</button>
        </template>
    </form>
    </div>    
    </template>
    <script>
    import http from '../../http-common'
    export default {
        name:"newDepartment",
        data(){
            return {
                department: {
                    id:null,
                    name:"",
                    shortName:"",
                    facultyId:""
                },
                faculties: [] 
            }
        },
        methods:{
            createDepartment(){
              const data = {
                name: this.department.name,
                shortName: this.department.shortName,
                facultyId: this.department.facultyId
              }
              http.post('department/',data).
              then(response => {
                console.log(response.data);
              })
        },
        getFaculties(){
            http.get('faculty/').then(response => {
                this.faculties = response.data
            })
        }
    },
    mounted(){
        this.getFaculties()
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
