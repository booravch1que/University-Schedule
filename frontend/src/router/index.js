import { createRouter, createWebHistory } from 'vue-router'
import facultyList from "../components/facultyComponents/facultyList.vue"
import newFaculty from "../components/facultyComponents/newFaculty.vue"
import facultyInfo from "../components/facultyComponents/facultyInfo.vue"
import departmentList from "../components/departmentComponents/departmentList.vue"
import newDepartment from "../components/departmentComponents/newDepartment.vue"
import departmentInfo from "../components/departmentComponents/departmentInfo.vue"
import disciplineList from "../components/disciplineComponents/disciplineList.vue"
import newDiscipline from "../components/disciplineComponents/newDiscipline.vue"
import teacherList from "../components/teacherComponents/teacherList.vue"
import newTeacher from "../components/teacherComponents/newTeacher.vue"
import groupList from "../components/groupComponents/groupList.vue"
import newGroup from "../components/groupComponents/newGroup.vue"
import groupInfo from "../components/groupComponents/groupInfo.vue"
import studentList from "../components/studentComponents/studentList.vue"
import newStudent from "../components/studentComponents/newStudent.vue"
import scheduleList from "../components/scheduleComponents/scheduleList.vue"
import newScheduledLesson from "../components/scheduleComponents/newScheduledLesson.vue"


const routes = [
  {
    path: "/schedule",
    alias: "/",
    component: scheduleList
  },
  {
    path: "/schedule/new",
    component: newScheduledLesson
  },
  {
    path: "/teacher",
    component: teacherList
  },
  {
    path: "/student/new",
    component: newStudent
  },
  {
    path: "/student",
    component: studentList
  },
  {
    path: "/group/new",
    component: newGroup
  },
  {
    path: "/group/:id",
    component: groupInfo
  },
  {
    path: "/group",
    component: groupList
  },
  {
    path: "/teacher/new",
    component: newTeacher
  },
  
  {
    path: "/faculty",
    component: facultyList
  },
  {
    path:"/faculty/new",
    component: newFaculty
  },
  {
    path:"/faculty/:id",
    component: facultyInfo
  },
  {
    path: "/department",
    component: departmentList
  },
  {
    path:"/department/new",
    component: newDepartment
  },
  {
    path:"/department/:id",
    component: departmentInfo
  },
  {
    path:"/discipline",
    component: disciplineList
  },
  {
    path:"/discipline/new",
    component: newDiscipline
  },
  
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router

