<template>
    <br>
    <div class="filters">
        <div class="newSchedule">
        <router-link to="schedule/new"><button class="btn btn-primary">Schedule new Lesson</button></router-link>
    </div>
    <br>
</div>
<div class="schedule">
    <table class="scheduleTable table table-striped">
        <thead class="thead-dark">
        <tr>
            <th>Time</th>
            <th>Group</th>
            <th>Discipline</th>
            <th>Teacher</th>
            <th>Classroom</th>
            <th colspan="2">Actions</th>
        </tr>
        </thead>
        <tbody>
            <template v-for="lesson in schedule" :key="lesson">
                <template v-if="lesson.id !== updatingLesson.id">
            <tr>
                <td>{{ lesson.time }}</td>
                <td><router-link :to="'group/' + lesson.group.id">{{ lesson.group.name }}</router-link></td>
                <td><router-link to="../discipline">{{lesson.discipline.name}}</router-link></td>
                <td><router-link to="../teacher">{{lesson.teacher.name}} {{lesson.teacher.surname}}</router-link></td>
                <td>{{lesson.classroom }}</td>
                <td><button @click="setActiveLesson(lesson.time,lesson.group.id,lesson.discipline.id,lesson.teacher.id,lesson.classroom,lesson.id)"
        class="btn btn-primary">Edit</button></td>
      <td><button @click="deleteLesson(lesson.id)"
                  class="btn btn-danger">Delete</button></td>
            </tr>
                </template>
                <template v-else>
                <tr>
                    <td> <input type="time" v-model="updatingLesson.time"/></td>
                    <td>
                        <select required v-model="updatingLesson.groupId" class="custom-select" id="groupLesson">
                            <template v-for="group in groups" :key="group">
                            <option :value="group.id">{{group.name}}</option>
                            </template>
                        </select>
                    </td>
                    <td>
                        <select required v-model="updatingLesson.disciplineId" class="custom-select" id="disciplineLesson">
                            <template v-for="discipline in disciplines" :key="discipline">
                            <option :value="discipline.id">{{discipline.name}}</option>
                            </template>
                        </select>
                    </td>
                    <td>
                        <select required v-model="updatingLesson.teacherId" class="custom-select" id="teacherLesson">
                            <template v-for="teacher in teachers" :key="teacher">
                            <option :value="teacher.id">{{teacher.name}}  {{ teacher.surname }}</option>
                            </template>
                        </select>
                    </td>
                    <td> <input maxlength="4" v-model="updatingLesson.classroom"/></td>
                    <td><button  @click="updateLesson(lesson.id)"
                        class="btn btn-success">Update</button></td>
                        <td><button @click="removeActiveLesson()"
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
export default {
    name:"scheduleList",
    data(){
        return {
            updatingLesson:{
                id: -1,
                time:'',
                groupId:'',
                disciplineId:'',
                teacherId:'',
                classroom:''
            },
            schedule: [],
            teachers: [],
            disciplines: [],
            groups: [],
        }
    },
    methods:{
        retrieveSchedule(){
            http.get("/schedule").then((res)=>{
                this.schedule = res.data
            })
        },
        updateLesson(id){
            const data = {
            time: this.updatingLesson.time,
            groupId: this.updatingLesson.groupId,
            disciplineId:this.updatingLesson.disciplineId,
            teacherId:this.updatingLesson.teacherId,
            classroom: this.updatingLesson.classroom
            }
            http.put(`/schedule/${id}`,data).then(res => {
                console.log(res.data);
            })
            this.updatingLesson = {}
            setTimeout( ()=> {
                this.retrieveSchedule()
            },100)
        },
        deleteLesson(id){
            http.delete(`/schedule/${id}`).then(res => {
                console.log(res.data);
            }),
            setTimeout( ()=> {
                this.retrieveSchedule()
            },100)
        },
        setActiveLesson(time,groupId,disciplineId,teacherId,classroom,id){
            this.updatingLesson.id = id
            this.updatingLesson.time = time,
            this.updatingLesson.groupId = groupId,
            this.updatingLesson.disciplineId = disciplineId,
            this.updatingLesson.teacherId = teacherId,
            this.updatingLesson.classroom = classroom
        },
        removeActiveLesson(){
            this.updatingLesson = {}
        },
        getGroups(){
            http.get("/group").then((res)=>{
                this.groups = res.data
            })
        },
        getDisciplines(){
            http.get("/discipline").then((res)=>{
                this.disciplines = res.data
            })
        },
        getTeachers(){
            http.get("/teacher").then((res)=>{
                this.teachers = res.data
            })
        }
},
mounted(){
        this.retrieveSchedule();
        this.getGroups();
        this.getDisciplines();
        this.getTeachers();

    }
}
</script>
<style scoped>
.newSchedule{
    margin-left:10px;
}
</style>