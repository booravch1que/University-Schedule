<template>
   <router-link to="../faculty"><button class="btn btn-primary back-button">Go back</button></router-link>
<div class="formDiv">
<form>
    <div class="form-group">
    <label for="facultyName">Name</label>
    <input required type="text" class="form-control" id="facultyName" placeholder="Faculty Full Name"
            v-model="faculty.name">
  </div>
  <div class="form-group">
    <label for="facultyShortName">ShortName</label>
    <input required type="text" class="form-control" id="facultyShortName" placeholder="Abbriviation or Short Name (Up to 5 characters)" maxlength="5"
            v-model="faculty.shortName">
  </div>
  <template v-if="faculty.name !== '' && faculty.shortName !== '' ">
  <button type="submit" class="btn btn-success mb-2" @click="createFaculty()">Submit</button>
</template>
</form>
</div>    
</template>
<script>
import http from '../../http-common'
export default {
    name:"newFaculty",
    data(){
        return {
            faculty: {
                id:null,
                name:"",
                shortName:""
            }, 
        }
    },
    methods:{
        createFaculty(){
          const data = {
            name: this.faculty.name,
            shortName: this.faculty.shortName
          }
          http.post('faculty/',data).
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
