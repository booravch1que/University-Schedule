<template>
    <div>
        <div class="headerDiv">
        <h1>Department: <span class="badge badge-primary">{{ department.name }} / {{ department.shortName }}</span> in faculty: 
            <span class="badge badge-primary"><router-link :to="'../faculty/' + faculty.id">{{faculty.name}}</router-link></span></h1>
        </div>
        <h1>Groups:</h1>
    <div class="departmentList">
        <template v-if="department.groupList.length">

                <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Course</th>
                    </tr>
                </thead>
                <tbody>
                    <template
                    v-for="(group,index) in department.groupList"
                    :key="group"
                    >
                    <tr>
                    <td scope="row">{{ index+1 }}</td>
                    <td><router-link :to="'../group/' + group.id">{{ group.name }}</router-link></td>
                    <td>{{group.course}}</td>
                    </tr>
                    </template>
                </tbody>
                </table>

            
        </template>
        <template v-else>
            <h1>Currently this department does not have any groups in it</h1>
        </template>
    </div>
    </div>
</template>
<script>
import http from '../../http-common'
export default {
    name:"departmentInfo",
    data(){
        return {
           department:{
            name:'',
            shortName:'',
            facultyId: '',
            groupList: []
           },
           faculty: ""
        }
    },
    methods:{
        getDepartment(id){
            http.get(`department/${id}`)
            .then(response => {
                this.department = response.data
            })
        },
        getFacultyById(id){
            http.get(`faculty/${id}`)
            .then(response => {
                this.faculty = response.data
            })
        }
    },
    mounted(){
        this.getDepartment(this.$route.params.id);
        setTimeout(()=>{this.getFacultyById(this.department.facultyId)},500)
        
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