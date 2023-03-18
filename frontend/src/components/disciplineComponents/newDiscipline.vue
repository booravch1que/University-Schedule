<template>
    <router-link to="../discipline"><button class="btn btn-primary back-button">Go back</button></router-link>
 <div class="formDiv">
 <form>
     <div class="form-group">
     <label for="disciplineName">Name</label>
     <input required type="text" class="form-control" id="disciplineName" placeholder="Discipline Full Name"
             v-model="discipline.name">
   </div>
   <template v-if="discipline.name !== ''">
            <button  class="btn btn-success mb-2" @click="createDiscipline()">Submit</button>
        </template>
 </form>
 </div>    
 </template>
 <script>
 import http from '../../http-common'
 export default {
     name:"newDiscipline",
     data(){
         return {
             discipline: {
                 id:null,
                 name:"",
             }, 
         }
     },
     methods:{
         createDiscipline(){
           const data = {
             name: this.discipline.name,
             
           }
           http.post('discipline/',data).
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
 