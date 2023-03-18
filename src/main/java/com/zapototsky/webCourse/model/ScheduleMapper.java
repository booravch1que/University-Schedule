package com.zapototsky.webCourse.model;

public class ScheduleMapper {
    private Long id;
    private String classroom;
    private String time;
    private Long groupId;
    private Long disciplineId;
    private Long teacherId;

    public ScheduleMapper(String classroom, String time, Long groupId, Long disciplineId, Long teacherId) {
        this.classroom = classroom;
        this.time = time;
        this.groupId = groupId;
        this.disciplineId = disciplineId;
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "ScheduleMapper{" +
                "id=" + id +
                ", classroom='" + classroom + '\'' +
                ", time='" + time + '\'' +
                ", groupID=" + groupId +
                ", disciplineID=" + disciplineId +
                ", teacherID=" + teacherId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getGroupID() {
        return groupId;
    }

    public void setGroupID(Long groupID) {
        this.groupId = groupID;
    }

    public Long getDisciplineID() {
        return disciplineId;
    }

    public void setDisciplineID(Long disciplineID) {
        this.disciplineId = disciplineID;
    }

    public Long getTeacherID() {
        return teacherId;
    }

    public void setTeacherID(Long teacherID) {
        this.teacherId = teacherID;
    }
}
