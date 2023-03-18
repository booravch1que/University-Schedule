<template>
    <div class="headerDiv">
        <h1>Faculty: <span class="badge badge-primary">{{ faculty.name }} / {{ faculty.shortName }}</span></h1>
    </div>
    <br>
    <h1>Departments:</h1>
    <div class="departmentList">
        <template v-if="faculty.departmentList.length">

                            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Short Name</th>
                    </tr>
                </thead>
                <tbody>
                    <template
                    v-for="(department,index) in faculty.departmentList"
                    :key="department"
                    >
                    <tr>
                    <td scope="row">{{ index+1 }}</td>
                    <td><router-link :to="'../department/' + department.id">{{ department.name }}</router-link></td>
                    <td>{{department.shortName}}</td>
                    </tr>
                    </template>
                </tbody>
                </table>

            
        </template>
        <template v-else>
            <h1>Currently this faculty does not have any departments in it</h1>
        </template>
    </div>
</template>
<script>
import http from '../../http-common'
export default {
    name:"facultyInfo",
    data(){
        return {
           faculty:{
            name:'',
            shortName:'',
            departmentList: []
           }
        }
    },
    methods:{
        getFaculty(id){
            http.get(`faculty/${id}`)
            .then(response => {
                this.faculty = response.data
            })
        }
    },
    mounted(){
        this.getFaculty(this.$route.params.id);
    }
}   
</script>
<style scoped>
.headerDiv{
    margin-left: 10px;
    margin-top: 10px;
}
.headerDiv h1{
    font-size: 80px;
}
</style>