package com.patrick_laust_ayo.lapayo.services;

import com.patrick_laust_ayo.lapayo.models.Assignment;
import com.patrick_laust_ayo.lapayo.models.Phase;
import com.patrick_laust_ayo.lapayo.models.Project;
import com.patrick_laust_ayo.lapayo.models.Task;
import com.patrick_laust_ayo.lapayo.repositories.ProjectRepository;

//Author Laust
public class ProjectEditor {

    private ProjectRepository repo = new ProjectRepository();
    private ProjectCreator creator = new ProjectCreator();


    public Project updateProject(String title, String formerTitle) {
        repo.updateProject(title,formerTitle);
        return creator.getProject(title);
    }


    public void deleteProject(String title) {
        repo.removeProject(title);
    }

    public void deletePhase(String phaseTitle,String projectTitle) {
        repo.removePhase(phaseTitle,projectTitle);
    }

    public void deleteAssignment(String assignmentTitle,String phaseTitle) {
        repo.removeAssignment(assignmentTitle,phaseTitle);
    }

    public void deleteTask(String taskTitle,String assignmentTitle) {
        repo.removeTask(taskTitle,assignmentTitle);
    }

    public Phase updatePhase(String newPhaseTitle, String formerPhaseTitle, String projectTitle) {
        repo.updatePhase(newPhaseTitle,projectTitle,formerPhaseTitle);
        return creator.getPhase(newPhaseTitle,projectTitle);
    }

    public Assignment updateAssignment(String title, String start, String end, String formerTitle, String phaseTitle) {
        repo.updateAssignment(title, start, end, formerTitle, phaseTitle);
        return creator.getAssignment(title,phaseTitle);
    }

    public Assignment changeIsCompletedAssignment(boolean isCompleted,String assignmentTitle,String phaseTitle) {
        if (isCompleted) {
            repo.updateAssignmentIsCompleted("true",assignmentTitle,phaseTitle);
        }
        else {
            repo.updateAssignmentIsCompleted("false",assignmentTitle,phaseTitle);
        }

        return creator.getAssignment(assignmentTitle,phaseTitle);
    }

    public Task updateTask(String title, String start, String end, double workHours, String formerTitle, String assignmentTitle) {
        repo.updateTask(title, start, end, String.valueOf(workHours), formerTitle, assignmentTitle);
        return creator.getTask(title,start,end);
    }

    public Task changeIsCompletedTask(boolean isCompleted,String taskTitle,String taskStart, String taskEnd,String phaseTitle) {

        Task task = new ProjectCreator().getTask(taskTitle, taskStart, taskEnd);
        if (isCompleted) {
            repo.updateTaskIsCompleted("true",task);
        }
        else {
            repo.updateTaskIsCompleted("false",task);
        }

        return task;
    }
}
