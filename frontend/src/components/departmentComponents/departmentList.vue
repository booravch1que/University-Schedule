<template>
    <br>
    <div>
      <div class="newDepartment">
        <router-link to="department/new"><button class="btn btn-primary">Create new Department</button></router-link>
    </div>
      <br>
          <table class="table table-striped">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Short Name</th>
      <th scope="col">Faculty</th>
      <th colspan="2">Actions</th>
    </tr>
  </thead>
  <tbody>
    <template
    v-for="(department,index) in departments"
    :key="department"
    >
    <template v-if="department.id !== updatingDepartment.id">
    <tr>
      <td scope="row">{{ index+1 }}</td>
      <td><router-link :to="'department/' + department.id">{{ department.name }}</router-link></td>
      <td>{{department.shortName}}</td>
      <td><router-link :to="'faculty/' + department.facultyId">Faculty..</router-link></td>
      <td><button @click="setActiveDepartment(department.name,department.shortName,department.id)"
        class="btn btn-primary">Edit</button></td>
      <td><button @click="deleteDepartment(department.id)"
                  class="btn btn-danger">Delete</button></td>
    </tr>
    </template>
    <template v-else>
      <tr>
      <th scope="row">{{ index+1 }}</th>
      <td> <input v-model="updatingDepartment.name"/></td>
      <td><input v-model="updatingDepartment.shortName"/></td>
      <td>
        <select required v-model="updatingDepartment.facultyId" class="custom-select" id="selectFaculty">
            <template v-for="faculty in faculties" :key="faculty">
            <option :value="faculty.id">{{faculty.name}}</option>
            </template>
        </select>
     </td>
      <td><button  @click="updateDepartment(updatingDepartment.name,updatingDepartment.shortName,updatingDepartment.facultyId,department.id)"
          class="btn btn-success">Update</button></td>
          <td><button @click="removeActiveDepartment()"
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
    name: "departmentList",
    data(){
        return {
          updatingDepartment : {

            name: "",
            shortName: "",
            id: -1,
            facultyId: -1

            },
            departments: [],
            faculties: [],
          
        }
    },
    methods:{
     retrieveDepartments(){
            
            http.get("department/")
            .then(response => {
                this.departments = response.data
              
               
            })
            
        },
        setActiveDepartment(name,shortName,id){
          this.updatingDepartment = { name,shortName,id}
        },


        updateDepartment(name,shortName,facultyId,id){
          console.log("updating department");
          let data = {
            name: name,
            shortName: shortName,
            facultyId: facultyId
          }
          console.log(data);
          http.put(`department/${id}`,data)
          this.updatingDepartment = {}
          setTimeout(() => { this.retrieveDepartments() }, 500);
        },
        deleteDepartment(id){
          http.delete(`department/${id}`)
          setTimeout(() => { this.retrieveDepartments() }, 500);
        },

        getFaculties(){
            http.get('faculty/').then(response => {
                this.faculties = response.data
            })
        },
       
        removeActiveDepartment(){
          this.updatingDepartment = {}
        }
                 
            
            
    },
    
    mounted(){
        this.retrieveDepartments()
        this.getFaculties()
    }
}
</script>
<style scoped>
.newDepartment{
    margin-left:10px;
}
</style>