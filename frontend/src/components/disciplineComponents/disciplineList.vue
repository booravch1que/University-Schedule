
<template>
    <br>
    <div>
      <div class="newDiscipline">
        <router-link to="discipline/new"><button class="btn btn-primary">Create new Discipline</button></router-link>
    </div>
      <br>
          <table class="table table-striped">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th colspan="2">Actions</th>
    </tr>
  </thead>
  <tbody>
    <template
    v-for="(discipline,index) in disciplines"
    :key="discipline">
    <template v-if="discipline.id !== updatingDiscipline.id">
  
    <tr>
      <td scope="row">{{ index+1 }}</td>
      <td>{{ discipline.name }}</td>
      <td><button @click="setActiveDiscipline(discipline.name,discipline.id)"
        class="btn btn-primary">Edit</button></td>
      <td><button @click="deleteDiscipline(discipline.id)"
                  class="btn btn-danger">Delete</button></td>
    </tr>
    </template>
    <template v-else>
      <tr>
      <th scope="row">{{ index+1 }}</th>
      <td> <input v-model="updatingDiscipline.name"/></td>
      <td><button  @click="updateDiscipline(updatingDiscipline.name,discipline.id)"
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
    name: "disciplineList",
    data(){
        return {
          updatingDiscipline : {

            name: "",
            id: "",
            },
            disciplines: []
        }
    },
    methods:{
     retrieveDisciplines(){
            
            http.get("discipline/")
            .then(response => {
                this.disciplines = response.data
               
            })
            
        },
        setActiveDiscipline(name,id){
          this.updatingDiscipline = { name,id}
        },


        updateDiscipline(name,id){
        
          let data = {
            name: name
          }
          http.put(`discipline/${id}`,data)
          this.updatingDiscipline = {}
          setTimeout(() => { this.retrieveDisciplines() }, 500);
        },
        deleteDiscipline(id){
          http.delete(`discipline/${id}`)
          setTimeout(() => { this.retrieveDisciplines() }, 500);
        },

        getFaculties(){
            http.get('faculty/').then(response => {
                this.faculties = response.data
            })
        }
                 
            
            
    },
    
    mounted(){
        this.retrieveDisciplines()
        this.getFaculties()
    }
}
</script>
<style scoped>
.newDiscipline{
    margin-left:10px;
}
</style>