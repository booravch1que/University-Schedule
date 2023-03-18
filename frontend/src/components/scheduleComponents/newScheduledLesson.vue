<template>
    <router-link to="../schedule"><button class="btn btn-primary back-button">Go back</button></router-link>
<div class="formDiv">
   <form>
       <div class="form-group">
       <label for="lessonTime">Time</label>
       <input required type="time" class="form-control" id="lessonTime" placeholder="Lesson's Time"
               v-model="lesson.time">
     </div>
     <div class="form-group">
       <label for="lessonGroup">Group</label>
       <select id="lessonGroup" v-model="lesson.groupId" class="custom-select">
        <template v-for="group in groups" :key="group">
            <option :value="group.id">
                {{ group.name }}
            </option>
        </template>
        </select>
     </div>
     <div class="form-group">
       <label for="lessonDiscipline">Select Discipline</label>
       <select required v-model="lesson.disciplineId" class="custom-select" id="lessonDiscipline">
           <template v-for="discipline in disciplines" :key="discipline">
           <option :value="discipline.id">{{discipline.name}}</option>
           </template>
       </select>
     </div>
     <div class="form-group">
       <label for="lessonTeacher">Select Teacher</label>
       <select required v-model="lesson.teacherId" class="custom-select" id="lessonTeacher">
           <template v-for="teacher in teachers" :key="teacher">
           <option :value="teacher.id">{{teacher.name}} {{ teacher.surname }}</option>
           </template>
       </select>
     </div>
     <div class="form-group">
       <label for="lessonClassroom">Classroom</label>
       <input required maxlength="4" type="text" class="form-control" id="lessonClassroom" placeholder="Lesson's Classroom"
               v-model="lesson.classroom">
     </div>
     <template v-if="lesson.time !== '' && lesson.classroom !== '' 
        && lesson.disciplineId !== '' && lesson.groupId !== '' && lesson.teacherId !== '' ">
            <button  class="btn btn-success mb-2" @click="scheduleLesson()">Submit</button>
        </template>
   </form>
   </div>    
   </template>
   <script>
   import http from '../../http-common'
   export default {
       name:"newLesson",
       data(){
           return {
               lesson: {
                   id:null,
                   time:"",
                   groupId: "",
                   disciplineId: "",
                   teacherId:"",
                   classroom:""
               },
               groups: [],
               disciplines:[],
               teachers: [], 
           }
       },
       methods:{
           scheduleLesson(){
             const data = {
                time: this.lesson.time,
                classroom: this.lesson.classroom,
                groupId: this.lesson.groupId,
                teacherId: this.lesson.teacherId,
                disciplineId: this.lesson.disciplineId
             }
             http.post('schedule/',data).
             then(response => {
               console.log(response.data);
             })
       },
       getGroups(){
           http.get('group/').then(response => {
               this.groups = response.data
           })
       },
       getDisciplines(){
           http.get('discipline/').then(response => {
               this.disciplines = response.data
           })
       },
       getTeachers(){
           http.get('teacher/').then(response => {
               this.teachers = response.data
           })
       }
   },
   mounted(){
       this.getDisciplines()
       this.getGroups()
       this.getTeachers()
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
