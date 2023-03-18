<template>
    <br>
    <div>
      <div class="newGroup">
        <router-link to="group/new"><button class="btn btn-primary">Create new Group</button></router-link>
    </div>
      <br>
          <table class="table table-striped">
  <thead class="thead-dark">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Course</th>
      <th scope="col">Department</th>
      <th colspan="2">Actions</th>
    </tr>
  </thead>
  <tbody>
    <template
    v-for="(group,index) in groups"
    :key="group">
    
    <template v-if="group.id !== updatingGroup.id">
  
    <tr>
      <td scope="row">{{ index+1 }}</td>
      <td><router-link :to="'group/' + group.id">{{ group.name }}</router-link></td>
      <td>{{ group.course }}</td>
      <td><router-link :to="'department/' + group.departmentId">Department..</router-link></td>
      <td><button @click="setActiveGroup(group.name,group.course,group.id)"
        class="btn btn-primary">Edit</button></td>
      <td><button @click="deleteGroup(group.id)"    
                  class="btn btn-danger">Delete</button></td>
    </tr>
    </template>
    <template v-else>
      <tr>
      <th scope="row">{{ index+1 }}</th>
      <td> <input v-model="updatingGroup.name"/></td>
      <td><input v-model="updatingGroup.course"/></td>
      <td><select v-model="updatingGroup.departmentId">
        <template v-for="department in departments" :key="department">
            <option :value="department.id">{{department.name}}</option>
            </template>
        </select></td>
      <td><button  @click="updateGroup(updatingGroup.name,updatingGroup.course,group.id)"
          class="btn btn-success">Update</button></td>
          <td><button @click="removeActiveGroup()"
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
    name: "groupList",
    data(){
        return {
          updatingGroup : {

            name: "",
            course: -1,
            id: -1,
            departmentId: -1,
            department: {

            }
            },
            groups: [],
            departments: [],
        }
    },
    methods:{
     retrieveGroups(){
            
            http.get("group/")
            .then(response => {
                this.groups = response.data
               
            })
            
        },
        setActiveGroup(name,course,id){
          this.updatingGroup = { name,course,id}
        },


        updateGroup(name,course,id){
        
          let data = {
            name: name,
            course: course
          }
          http.put(`group/${id}`,data)
          this.updatingGroup = {}
          setTimeout(() => { this.retrieveGroups() }, 500);
        },
        deleteGroup(id){
          http.delete(`group/${id}`)
          setTimeout(() => { this.retrieveGroups() }, 500);
        },

        getDepartments(){
            http.get('department/').then(response => {
                this.departments = response.data
            })
        },
        findGroupDepartment(group){{
            for (let department of this.departments){
                if(group.departmentId == department.id){
                    group.department = department
                    continue
                }
            }
        }       
    },
    removeActiveGroup(){
      this.updatingGroup = {}
    }
},
created(){
        this.retrieveGroups()
        this.getDepartments()
    },
    
}
</script>
<style scoped>
.newGroup{
    margin-left:10px;
}
</style>