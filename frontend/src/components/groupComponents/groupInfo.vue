<template>
    <div>
        <div class="headerDiv">
        <h1>Group: <span class="badge badge-primary">{{ group.name }} / Course: {{ group.course }}</span> in department
            <span class="badge badge-primary"><router-link :to="'../department/' + department.id">{{department.name}}</router-link></span></h1>
        </div>
        <h1>Students:</h1>
    <div class="groupListClass">
        <template v-if="group.studentList.length">

                <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Phone</th>
                    </tr>
                </thead>
                <tbody>
                    <template
                    v-for="(student,index) in group.studentList"
                    :key="student"
                    >
                    <tr>
                    <td scope="row">{{ index+1 }}</td>
                    <td>{{ student.name }}</td>
                    <td>{{student.email}}</td>
                    <td>{{student.phone}}</td>
                    </tr>
                    </template>
                </tbody>
                </table>

            
        </template>
        <template v-else>
            <h1>Currently this group does not have any students in it</h1>
        </template>
    </div>
    </div>
</template>
<script>
import http from '../../http-common'
export default {
    name:"groupInfo",
    data(){
        return {
           group:{
            name:'',
            shortName:'',
            departmentId: '',
            studentList: []
           },
           department: {},

        }
    },
    methods:{
        getGroup(id){
            http.get(`group/${id}`)
            .then(response => {
                this.group = response.data
            })
        },
        getDepartmentById(id){
            http.get(`department/${id}`)
            .then(response => {
                this.department = response.data
            })
        }
    },
    mounted(){
        this.getGroup(this.$route.params.id);
        setTimeout(()=>{this.getDepartmentById(this.group.departmentId)},100)
        
    }
}   
</script>
<style scoped>
.headerDiv a{

    text-decoration: none;
    color:white;
}
.headerDiv a.visited{
    text-decoration: none;
    color:white;
}
.headerDiv{
    margin-left: 10px;
    margin-top: 10px;
}
.headerDiv h1{
    font-size: 80px;
}
</style>