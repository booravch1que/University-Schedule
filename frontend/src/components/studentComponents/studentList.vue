<template>
    <br>
    <div>
      <div class="newStudent">
        <router-link to="student/new"><button class="btn btn-primary">Create new Student</button></router-link>
    </div>
      <br>
          <table class="table table-striped">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">Phone</th>
      <th scope="col">Group</th>
      <th colspan="2">Actions</th>
    </tr>
  </thead>
  <tbody>
    <template
    v-for="(student,index) in students"
    :key="student">
    

    <template v-if="student.id !== updatingStudent.id">
  
    <tr>
      <td scope="row">{{ index+1 }}</td>
      <td>{{ student.name }}</td>
      <td>{{ student.email }}</td>
      <td>{{ student.phone }}</td>
      <td><router-link :to="'group/' + student.groupId">Group..</router-link></td>
      <td><button @click="setActiveStudent(student.name,student.email,student.phone,student.id)"
        class="btn btn-primary">Edit</button></td>
      <td><button @click="deleteStudent(student.id)"    
                  class="btn btn-danger">Delete</button></td>
    </tr>
    </template>
    <template v-else>
      <tr>
      <th scope="row">{{ index+1 }}</th>
      <td> <input v-model="updatingStudent.name"/></td>
      <td><input v-model="updatingStudent.email"/></td>
      <td><input v-model="updatingStudent.phone"/></td>
      <td><select v-model="updatingStudent.groupId">
        <template v-for="group in groups" :key="group">
            <option :value="group.id">{{group.name}}</option>
            </template>
        </select></td>
      <td><button  @click="updateStudent(updatingStudent.name,updatingStudent.email,updatingStudent.phone,updateStudent.groupId,student.id)"
          class="btn btn-success">Update</button></td>
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
    name: "studentList",
    data(){
        return {
          updatingStudent : {

            name: "",
            email:"",
            phone:"",
            id: -1,
            groupId: -1,
            group: {

            }
            },
            students: [],
            groups: [],
        }
    },
    methods:{
     retrieveStudents(){
            
            http.get("student/")
            .then(response => {
                this.students = response.data
               
            })
            
        },
        setActiveStudent(name,email,phone,id){
          this.updatingStudent = { name,email,phone,id}
        },


        updateStudent(name,email,phone,groupId,id){
        
          let data = {
            name: name,
            email: email,
            phone: phone,
            groupId: groupId,
          }
          http.put(`student/${id}`,data)
          this.updatingStudent = {}
          setTimeout(() => { this.retrieveStudents() }, 500);
        },
        deleteStudent(id){
          http.delete(`student/${id}`)
          setTimeout(() => { this.retrieveStudents() }, 500);
        },

        getGroups(){
            http.get('group/').then(response => {
                this.groups = response.data
            })
        },
        findStudentGroup(student){{
            for (let group of this.groups){
                if(student.groupId == group.id){
                    student.group = group
                    continue
                }
            }
        }       
    }
},
created(){
        this.retrieveStudents()
        this.getGroups()
    },
    
}
</script>
<style scoped>
.newStudent{
    margin-left:10px;
}
</style>