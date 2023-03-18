<template>
    <br>
    <div>
      <div class="newTeacher">
        <router-link to="teacher/new"><button class="btn btn-primary">Create new Teacher</button></router-link>
    </div>
      <br>
  <table class="table table-striped">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>  
      <th scope="col">Full Name</th>
      <th scope="col">Email</th>
      <th scope="col">Phone</th>
      <th colspan="2">Actions</th>
    </tr>
  </thead>
  <tbody>
    <template
    v-for="(teacher,index) in teachers"
    :key="teacher"
    >   
    <template v-if="teacher.id !== updatingTeacher.id">
    <tr>
        <td scope="row">{{ index+1 }}</td>
      <td>{{ teacher.name }}  {{ teacher.surname }}</td>
        <td>{{ teacher.email }} </td>
        <td>{{ teacher.phone }} </td>
      <td><button @click="setActiveTeacher(teacher.name,teacher.surname,teacher.email,teacher.phone,teacher.id)"
        class="btn btn-primary">Edit</button></td>
      <td><button @click="deleteTeacher(teacher.id)"
                  class="btn btn-danger">Delete</button></td>
    </tr>
    </template>
    <template v-else>
    <th colspan="2"></th>
      <tr>
      <td> <input v-model="updatingTeacher.fullName"/></td>
      <td> <input v-model="updatingTeacher.email"/></td>
      <td> <input v-model="updatingTeacher.phone"/></td>
      <td><button  @click="updateTeacher(updatingTeacher.fullName,
      updatingTeacher.email,updatingTeacher.phone,teacher.id)"
          class="btn btn-success">Update</button></td>
          <td><button @click="removeActiveTeacher()"
                  class="btn btn-warning">Back</button></td>
    </tr>
    </template>
  </template>
  </tbody>
</table>
    
    </div>
</template>
<script>


import http from '../../http-common'

export default{
    name: "teacherList",
    data(){
        return {
            teachers: [],

          updatingTeacher : {

            fullName: "",
            email:"",
            phone:"",
            id: -1

            },
            
        }
    },
    methods:{
            retrieveTeachers(){

                http.get("teacher/").then(res => {
                    this.teachers = res.data

                })
            },

        setActiveTeacher(name,surname,email,phone,id){
          this.updatingTeacher = { 
            fullName:`${name} ${surname}`,
            email,phone,id}
        },


        updateTeacher(fullName,email,phone,id){
        
          let data = {
            name: fullName.split(' ')[0],
            surname: fullName.split(' ')[1],
            email: email,
            phone:phone
          }
          http.put(`teacher/${id}`,data)
          this.updatingTeacher = {}
          setTimeout(() => { this.retrieveTeachers() }, 500);
        },
        deleteTeacher(id){
          http.delete(`teacher/${id}`)
          setTimeout(() => { this.retrieveTeachers() }, 500);
        },

        removeActiveTeacher(){
            this.updatingTeacher = {}
        }
                 
            
            
    },
    
   mounted(){
        this.retrieveTeachers()
}
}

</script>
<style scoped>
.newTeacher{
    margin-left:10px;
}
</style>