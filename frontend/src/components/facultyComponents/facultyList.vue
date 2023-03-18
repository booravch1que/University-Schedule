<template>
  <br>
    <div>
      <div class="newFaculty">
        <router-link to="faculty/new"><button class="btn btn-primary">Create new Faculty</button></router-link>
      </div>
      <br>
          <table class="table table-striped">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Short Name</th>
      <th colspan="2">Actions</th>
    </tr>
  </thead>
  <tbody>
    <template
    v-for="(faculty,index) in faculties"
    :key="faculty"
    >
    <template v-if="faculty.id !== updatingFaculty.id">
    <tr>
      <td scope="row">{{ index+1 }}</td>
      <td><router-link :to="'faculty/' + faculty.id"> {{ faculty.name }}</router-link></td>
      <td>{{faculty.shortName}}</td>
      <td><button @click="setActiveFaculty(faculty.name,faculty.shortName,faculty.id)"
        class="btn btn-primary">Edit</button></td>
      <td><button @click="deleteFaculty(faculty.id)"
                  class="btn btn-danger">Delete</button></td>
    </tr>
    </template>
    <template v-else>
      <tr>
      <th scope="row">{{ index+1 }}</th>
      <td> <input v-model="updatingFaculty.name"/></td>
      <td><input v-model="updatingFaculty.shortName"/></td>
      <td><button  @click.stop="updateFaculty(updatingFaculty.name,updatingFaculty.shortName,faculty.id)"
          class="btn btn-success">Update</button></td>
          <td><button @click="removeActiveFaculty()"
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
    name: "facultyList",
    data(){
        return {
          updatingFaculty : {

            name: "",
            shortName: "",
            id: -1

            },
            faculties: [],
        }
    },
    methods:{
     retrieveFaculties(){
            
            http.get("faculty/")
            .then(response => {
                this.faculties = response.data
            })
            
        },
        setActiveFaculty(name,shortName,id){
          this.updatingFaculty = { name,shortName,id}
          console.log("setting active");
        },
        updateFaculty(name,shortName,id){
          console.log("updating faculty");
          http.put(`faculty/${id}`,{
            name: name,
            shortName: shortName
          })
          this.updatingFaculty = {}
          setTimeout(() => { this.retrieveFaculties() }, 500);
        },
        deleteFaculty(id){
          http.delete(`faculty/${id}`)
          setTimeout(() => { this.retrieveFaculties() }, 500);
        },
        removeActiveFaculty(){
          this.updatingFaculty = {}
        }
        
    },
    mounted() {
   
      this.retrieveFaculties();

    }
  
}
</script>
<style scoped>
.newFaculty{
    margin-left:10px;
}
</style>