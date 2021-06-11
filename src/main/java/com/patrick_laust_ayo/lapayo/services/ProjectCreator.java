package com.patrick_laust_ayo.lapayo.services;

import com.patrick_laust_ayo.lapayo.models.*;
import com.patrick_laust_ayo.lapayo.repositories.ProjectManagerRepository;
import com.patrick_laust_ayo.lapayo.repositories.ProjectRepository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Author Laust
public class ProjectCreator {

    private Project project;
    private Phase phase;
    private Assignment assignment;
    private Task task;

    private ProjectRepository projectRepo = new ProjectRepository();
    private ProjectManagerRepository projectManagerRepo = new ProjectManagerRepository();


    private String[] updateStrings(String[] strings, ResultSet res) {
        try {
            strings[0] = res.getString("location");
            strings[1] = res.getString("department_name");

            strings[2] = res.getString("user_id");
            strings[3] = res.getString("participant_password");
            strings[4] = res.getString("participant_name");
            strings[5] = res.getString("position");
        }
        catch (Exception e) {
            System.out.println("Not updated participant...\n");
        }

        try {
            strings[6] = res.getString("task_start");
            strings[7] = res.getString("task_end");
            strings[8] = res.getString("task_title");
        }
        catch (Exception e) {
            System.out.println("Not updated task...\n");
        }

        try {
            strings[9] = res.getString("assignment_start");
            strings[10] = res.getString("assignment_end");
            strings[11] = res.getString("assignment_title");
        }
        catch (Exception e) {
            System.out.println("Not updated assignment...\n");
        }

        try {
            strings[12] = res.getString("title");
        }
        catch (Exception e) {
            System.out.println("Not updated project...\n");
        }

        try {
            strings[13] = res.getString("phase_title");
        }
        catch (Exception e) {
            System.out.println("Not updated phase...\n");
        }

        try {
            strings[15] = res.getString("username");
        }
        catch (Exception e) {
            System.out.println("Not updated projectManager...\n");
        }

        return strings;
    }

    public Project createProject(String title, String managerName) {

        UserCreator userCreator = new UserCreator();
        project = new Project(title, new ArrayList<>(), new HashMap<>(), userCreator.getProjectManager(managerName));
        projectManagerRepo.closeCurrentConnection();

        try {
            projectRepo.putProjectInDatabase(project, projectManagerRepo.findId("projectmanager",
                    "username",managerName,"projectmanager_id"));
        }
        catch (Exception e) {
            System.out.println("Couldn't put project in database in createProject...\n" + e.getMessage());
            e.printStackTrace();
        }

        projectManagerRepo.closeCurrentConnection();
        return project;
    }

    public Project getProject(String projectTitle) {

        ResultSet res = projectRepo.findProject(projectTitle);

        // ArrayLists
        ArrayList<Phase> listOfPhases = new ArrayList<>();
        ArrayList<Participant> listOfParticipants = new ArrayList<>();
        ArrayList<Task> listOfTasks = new ArrayList<>();
        ArrayList<Assignment> listOfAssignments = new ArrayList<>();

        // Map for project
        Map<String, Participant> mapOfParticipants = new HashMap<>();

        // Ints
        int currentTaskId = 0;
        int currentAssignmentId = 0;
        int currentPhaseId = 0;
        int currentParticipantId = 0;

        int formerTaskId = 0;
        int formerAssignmentId = 0;
        int formerPhaseId = 0;
        int formerParticipantId = 0;

        int departmentId = 0;

        String[] strings = new String[16];

        // Booleans and double
        boolean assignmentIsCompleted = false;
        boolean taskIsCompleted = false;
        boolean isProjectManagerCreated = false;

        double workHours = 0;


        ProjectManager projectManager = new ProjectManager(new String(),new String());

        try {
            ResultSetMetaData rsmd = res.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (res.next()) {
                // In case there is only one row
                if (res.isLast() && res.isFirst()) {
                    project = new Project(res.getString("title"));
                    projectManager = new UserCreator().getProjectManager(res.getString("username"));
                    project.setProjectManager(projectManager);
                    mapOfParticipants.put(projectManager.getId(), projectManager);
                    project.setParticipants(mapOfParticipants);
                    if (columnCount >= 20) {
                        listOfPhases.add(new Phase(res.getString("phase_title")));
                        project.setPhases(listOfPhases);
                    }
                    break;
                }

                // Updates values
                if (!res.isFirst()) {
                    // Ids
                    if (columnCount == 35) {
                        currentTaskId = res.getInt("task_id");
                    }
                    if (columnCount >= 26) {
                        currentAssignmentId = res.getInt("assignment_id");
                    }
                    if (columnCount >= 20) {
                        currentPhaseId = res.getInt("phase_id");
                    }
                    currentParticipantId = res.getInt("participant_id");


                    // projectManager
                    if (!isProjectManagerCreated && (strings[15].equals(strings[2]))) {
                        projectManager = new ProjectManager(strings[15],strings[3], strings[2],strings[4],strings[5],
                                new Department(departmentId,strings[0],strings[1]));
                        mapOfParticipants.put(projectManager.getId(), projectManager);
                        listOfParticipants.add(projectManager);
                        isProjectManagerCreated = true;
                    }


                    // Participant
                    else if (currentTaskId > formerTaskId || currentParticipantId > formerParticipantId) {
                        Participant participant = new Participant(strings[2], strings[3], strings[4], strings[5],
                                new Department(departmentId,strings[0],strings[1]));
                        mapOfParticipants.put(participant.getId(), participant);
                        listOfParticipants.add(participant);
                    }


                    // Task
                    if (currentTaskId>formerTaskId){
                        listOfTasks.add(new Task(workHours,listOfParticipants,strings[6],strings[7], strings[8],taskIsCompleted));
                        listOfParticipants = new ArrayList<>();
                    }


                    // Assignment
                    if (currentAssignmentId>formerAssignmentId) {
                        assignment = new Assignment(strings[9],strings[10],strings[11],assignmentIsCompleted,listOfTasks);
                        listOfTasks = new ArrayList<>();
                        listOfAssignments.add(assignment);
                    }


                    // Phase
                    if (currentPhaseId>formerPhaseId) {
                        phase = new Phase(strings[13],listOfAssignments);
                        listOfAssignments = new ArrayList<>();
                        listOfPhases.add(phase);
                    }
                }


                // Updates values of former row
                if (columnCount == 35) {
                    formerTaskId = res.getInt("task_id");
                    taskIsCompleted = res.getBoolean("task_is_completed");
                    workHours = res.getDouble("estimated_work_hours");
                }

                if (columnCount >= 26) {
                    formerAssignmentId = res.getInt("assignment_id");
                    assignmentIsCompleted = res.getBoolean("is_completed");
                }

                if (columnCount >= 20) {
                    formerPhaseId = res.getInt("phase_id");
                }

                formerParticipantId = res.getInt("participant_id");
                departmentId = res.getInt("department_no");
                strings = updateStrings(strings,res);


                if (res.isLast()) {
                    boolean allowAddTask = true;

                    // If ProjectManager isn't added
                    if (!isProjectManagerCreated && columnCount == 35) {
                        if (listOfTasks.size() == 0){
                            listOfTasks.add(new Task(workHours,listOfParticipants,strings[6],strings[7], strings[8],taskIsCompleted));
                            allowAddTask = false;
                        }


                        projectManager = new ProjectManager(strings[15],strings[3], strings[2],strings[4],strings[5],
                                new Department(departmentId,strings[0],strings[1]));
                        if (!mapOfParticipants.containsKey(projectManager.getId())) {
                            mapOfParticipants.put(projectManager.getId(), projectManager);
                        }


                        if (columnCount == 35) {
                            listOfTasks.get(listOfTasks.size()-1).addParticipant(projectManager);
                        }
                    }


                    // If Participant isn't added
                    else if (isProjectManagerCreated) {
                        Participant participant = new Participant(strings[2], strings[3], strings[4], strings[5],
                                                    new Department(departmentId,strings[0],strings[1]));

                        if (listOfTasks.size() == 0 && columnCount == 35){
                            listOfTasks.add(new Task(workHours,listOfParticipants,strings[6],strings[7], strings[8],taskIsCompleted));
                            allowAddTask = false;
                        }

                        mapOfParticipants.put(participant.getId(), participant);
                        if (columnCount == 35) {
                            listOfTasks.get(listOfTasks.size()-1).addParticipant(participant);
                        }
                    }


                    // Task
                    if (allowAddTask && columnCount == 35) {
                        listOfTasks.add(new Task(workHours,listOfParticipants,strings[6],strings[7], strings[8],taskIsCompleted));
                    }

                    // Phase
                    if (columnCount >= 20) {
                        phase = new Phase(strings[13],listOfAssignments);
                        listOfPhases.add(phase);
                    }

                    // Assignment
                    if (columnCount >= 26) {
                        assignment = new Assignment(strings[9],strings[10],strings[11],assignmentIsCompleted,listOfTasks);
                        listOfAssignments.add(assignment);
                        if (columnCount >= 20) {
                            listOfPhases.get(listOfPhases.size()-1).addToAssignments(assignment);
                        }
                    }

                    project = new Project(strings[12],listOfPhases, mapOfParticipants,projectManager);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Couldn't create project in getProject...\n" + e.getMessage());
            e.printStackTrace();
            project = null;
        }

        projectRepo.closeCurrentConnection();
        return project;
    }

    public ArrayList<Project> getProjects(String userId) {

        ResultSet res = projectRepo.findProjects(userId);
        ArrayList<Project> projects = new ArrayList<>();

        try {
            while (res.next()) {
                projects.add(getProject(res.getString("title")));
            }
        }
        catch (Exception e) {
            System.out.println("Couldn't gather projects in getProjects...\n" + e.getMessage());
            e.printStackTrace();
        }

        return projects;
    }

    public Phase createPhase(String projectTitle,String phaseTitle,String projectManagerUsername) {

        phase = new Phase(phaseTitle);

        int id = projectRepo.findId("project","title",projectTitle, "project_id");
        projectRepo.putPhaseInDatabase(phaseTitle, id,projectManagerUsername);

        return phase;
    }

    public Phase getPhase(String phaseTitle,String projectTitle) {

        ResultSet res = projectRepo.findPhase(phaseTitle,projectTitle);

        // Objects
        phase = new Phase(new String(),new ArrayList<>());

        // Map and Lists
        ArrayList<Assignment> assignments = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Participant> participants = new ArrayList<>();

        String[] strings = new String[16];

        int currentAssignmentId = 0;
        int currentTaskId = 0;
        int currentParticipantId = 0;

        int formerAssignmentId = 0;
        int formerTaskId = 0;
        int formerParticipantId = 0;
        int departmentId = 0;

        double workHours = 0;
        boolean isTaskCompleted = false;
        boolean isAssignmentCompleted = false;


        try {
            ResultSetMetaData rsmd = res.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (res.next()) {
                // If there is only one row
                if (res.isFirst() && res.isLast()) {
                    phase = new Phase(res.getString("phase_title"),assignments);
                    break;
                }

                if (columnCount == 33) {
                    currentTaskId = res.getInt("task_id");
                }

                if (columnCount >= 27) {
                    currentAssignmentId = res.getInt("assignment_id");
                }

                currentParticipantId = res.getInt("participant_id");


                if (!res.isFirst()) {

                    // Participant
                    if (currentParticipantId > formerParticipantId || currentTaskId > formerTaskId) {
                        participants.add(new Participant(strings[2], strings[3], strings[4], strings[5],
                                new Department(departmentId, strings[0], strings[1])));
                    }

                    // Task
                    if (currentTaskId > formerTaskId) {
                        tasks.add(new Task(workHours, participants, strings[6], strings[7], strings[8], isTaskCompleted));
                        participants = new ArrayList<>();
                    }

                    // Assignment
                    if (currentAssignmentId > formerAssignmentId) {
                        assignment = new Assignment(strings[9], strings[10], strings[11], isAssignmentCompleted, tasks);
                        assignments.add(assignment);
                    }
                }


                if (columnCount == 33) {
                    formerTaskId = res.getInt("task_id");
                }

                if (columnCount >= 27) {
                    formerAssignmentId = res.getInt("assignment_id");
                }

                formerParticipantId = res.getInt("participant_id");
                departmentId = res.getInt("department_no");
                workHours = res.getDouble("estimated_work_hours");
                isTaskCompleted = res.getBoolean("task_is_completed");
                isAssignmentCompleted = res.getBoolean("is_completed");

                strings = updateStrings(strings,res);


                if (res.isLast()) {
                    // Participant
                    boolean allowAddTask = true;
                    if(tasks.size() == 0){
                        tasks.add(new Task(workHours, participants,strings[6],strings[7], strings[8], isTaskCompleted));
                        allowAddTask = false;
                    }
                    tasks.get(tasks.size()-1).addParticipant(new Participant(strings[2], strings[3], strings[4], strings[5],
                            new Department(departmentId, strings[0], strings[1])));

                    // Task
                    if (allowAddTask) {
                        tasks.add(new Task(workHours, participants, strings[6], strings[7], strings[8], isTaskCompleted));
                    }

                    // Assignment
                    assignment = new Assignment(strings[9],strings[10],strings[11],isAssignmentCompleted,tasks);
                    assignments.add(assignment);
                    phase = new Phase(res.getString("phase_title"),assignments);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Couldn't create phase from database...\n" + e.getMessage());
            e.printStackTrace();
            phase = null;
        }

        return phase;
    }

    public Assignment createAssignment(String phaseTitle, String assignmentTitle,String start, String end,Project project) {

        assignment = new Assignment(start,end,assignmentTitle,false, new ArrayList<Task>());
        projectRepo.putAssignmentInDatabase(assignment,projectRepo.findId("phase_table","phase_title",
                                            phaseTitle,"phase_id"),project.getProjectManager().getUsername());
        return assignment;
    }

    public Assignment getAssignment(String assignmentTitle,String phaseTitle) {

        ResultSet res = projectRepo.findAssignment(assignmentTitle,phaseTitle);
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Participant> participants = new ArrayList<>();

        int currentTaskId = 0;
        int currentParticipantId = 0;

        int formerTaskId = 0;
        int formerParticipantId = 0;
        int departmentNo = 0;

        String[] strings = new String[16];

        double workHours = 0;
        boolean isTaskCompleted = false;


        try {
            while (res.next()) {
                ResultSetMetaData rsmd = res.getMetaData();
                int columnCount = rsmd.getColumnCount();

                // If there is only one row
                if (res.isFirst() && res.isLast()) {
                    assignment = new Assignment(res.getString("assignment_start"), res.getString("assignment_end"),
                            res.getString("assignment_title"),res.getBoolean("is_completed"), tasks);
                    break;
                }

                if (!res.isFirst()) {
                    if (columnCount == 27) {
                        currentParticipantId = res.getInt("participant_id");
                    }

                    if (columnCount >= 16) {
                        currentTaskId = res.getInt("task_id");
                    }

                    if (currentParticipantId > formerParticipantId) {
                        participants.add(new Participant(strings[2],strings[3], strings[4],strings[5],
                                            new Department(departmentNo,strings[0],strings[1])));
                    }

                    if (currentTaskId > formerTaskId) {
                        tasks.add(new Task(workHours, participants, strings[6], strings[7], strings[8], isTaskCompleted));
                        System.out.println(tasks.get(tasks.size()-1).getTitle());
                    }
                }


                if (columnCount == 27) {
                    formerParticipantId = res.getInt("participant_id");
                    departmentNo = res.getInt("department_no");
                }

                if (columnCount >= 16) {
                    formerTaskId = res.getInt("task_id");
                }


                workHours = res.getDouble("estimated_work_hours");
                isTaskCompleted = res.getBoolean("task_is_completed");

                strings = updateStrings(strings,res);


                if (res.isLast()) {
                    tasks.add(new Task(workHours, participants, strings[6], strings[7], strings[8], isTaskCompleted));
                    assignment = new Assignment(res.getString("assignment_start"), res.getString("assignment_end"),
                            res.getString("assignment_title"),res.getBoolean("is_completed"), tasks);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Couldn't create assignment from database...\n" + e.getMessage());
            assignment = null;
        }

        return assignment;
    }

    public Task createTask(String assignmentTitle,Task task,String projectManagerUsername) {

        projectRepo.putTaskInDatabase(projectRepo.findId("assignment","assignment_title",assignmentTitle, "assignment_id"),
                projectManagerUsername,task);

        return task;
    }

    public Task getTask(String taskTitle,String taskStart,String taskEnd) {

        ResultSet res = projectRepo.findTask(taskTitle,taskStart,taskEnd);
        ArrayList<Participant> participants = new ArrayList<>();
        Department department = new Department(0,new String(), new String());

        int currentParticipantId = 0;

        int formerParticipantId = 0;

        int departmentId = 0;

        String[] strings = new String[16];


        try {
            while (res.next()) {
                ResultSetMetaData rsmd = res.getMetaData();
                int columnCount = rsmd.getColumnCount();

                // If there is only one row
                if (res.isFirst() && res.isLast()) {
                    task = new Task(res.getDouble("estimated_work_hours"), participants, res.getString("task_start"),
                            res.getString("task_end"), res.getString("task_title"),
                            res.getBoolean("task_is_completed"));
                    break;
                }


                if (!res.isFirst()) {
                    if (columnCount > 13) {
                        currentParticipantId = res.getInt("participant_id");
                    }

                    if (currentParticipantId > formerParticipantId) {
                        department = new Department(departmentId,strings[0],strings[1]);
                        participants.add(new Participant(strings[2],strings[3],strings[4],strings[5],department));
                    }
                }


                if (columnCount > 13) {
                    formerParticipantId = res.getInt("participant_id");
                    departmentId = res.getInt("department_no");
                }


                strings = updateStrings(strings,res);


                if (res.isLast()) {
                    department = new Department(departmentId,strings[0],strings[1]);
                    participants.add(new Participant(strings[2],strings[3],strings[4],strings[5],department));
                    task = new Task(res.getDouble("estimated_work_hours"), participants, strings[6],
                                    strings[7], strings[8], res.getBoolean("task_is_completed"));
                }
            }
        }
        catch (Exception e) {
            System.out.println("Couldn't create task from database...\n" + e.getMessage());
            task = null;
        }

        return task;
    }
}