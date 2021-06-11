package com.patrick_laust_ayo.lapayo.services;

import com.patrick_laust_ayo.lapayo.models.Assignment;
import com.patrick_laust_ayo.lapayo.models.Phase;
import com.patrick_laust_ayo.lapayo.models.Project;
import com.patrick_laust_ayo.lapayo.models.Task;
import com.patrick_laust_ayo.lapayo.repositories.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Author Laust
class ProjectCreatorTest {

    private ProjectCreator projectCreator = new ProjectCreator();

    @ParameterizedTest
    @CsvSource(value = {"Save the homeless|andy0432", "Skydiving App|cind2352"}, delimiter = '|')
    public void createProjectTest(String title, String managerUsername) {

        //Act
        Project actual = projectCreator.createProject(title,managerUsername);

        //Assert
        assertEquals(actual.getTitle(),title);
        assertEquals(actual.getProjectManager().getUsername(),managerUsername);
    }

    // Isn't parameterized because of too many maps crossed over by eachother
    @Test
    void getProject() {
        //Act
        Project appdevProject = projectCreator.getProject("Appdev");
        Project findNewEmployeesProject = projectCreator.getProject("Find New Employees");
        Project AdvertisingProject = projectCreator.getProject("Advertising");

        //Assert
        //Project titles
        assertEquals("Appdev", appdevProject.getTitle());
        assertEquals("Find New Employees", findNewEmployeesProject.getTitle());
        assertEquals("Advertising", AdvertisingProject.getTitle());

        //Projektets tasks værdier
        assertEquals(300.00,
                appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getEstimatedWorkHours());
        assertEquals("Innovate Concepts",
                appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getTitle());
        assertEquals("2021-10-15 12:30:00",
                appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getStart());
        assertEquals("2021-10-28 23:59:59",
                appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getEnd());
        assertEquals("lone9242",
                appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getParticipants().get(0).getId());
        assertEquals("ande0137",
                appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getParticipants().get(1).getId());
        assertEquals(false,
                appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).isCompleted());
        assertEquals(200.00,
                appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).getEstimatedWorkHours());
        assertEquals("Discuss Possible Clients",
                appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).getTitle());
        assertEquals("2021-10-29 12:30:00",
                appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).getStart());
        assertEquals("2021-11-14 23:59:59",
                appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).getEnd());
        assertEquals("lone9242",
                appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).getParticipants().get(0).getId());
        assertEquals("ande0137",
                appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).getParticipants().get(1).getId());
        assertEquals(false,
                appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).isCompleted());

        assertEquals(100.00,
                appdevProject.getPhases().get(1).getAssignments().get(0).getTasks().get(0).getEstimatedWorkHours());
        assertEquals("Repositories",
                appdevProject.getPhases().get(1).getAssignments().get(0).getTasks().get(0).getTitle());
        assertEquals("2021-11-15 12:30:00",
                appdevProject.getPhases().get(1).getAssignments().get(0).getTasks().get(0).getStart());
        assertEquals("2021-12-05 23:59:59",
                appdevProject.getPhases().get(1).getAssignments().get(0).getTasks().get(0).getEnd());
        assertEquals("lone9242",
                appdevProject.getPhases().get(1).getAssignments().get(0).getTasks().get(0).getParticipants().get(0).getId());
        assertEquals("ande0137",
                appdevProject.getPhases().get(1).getAssignments().get(0).getTasks().get(0).getParticipants().get(1).getId());
        assertEquals(false,
                appdevProject.getPhases().get(1).getAssignments().get(0).getTasks().get(0).isCompleted());

        assertEquals(100.00,
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(0).getEstimatedWorkHours());
        assertEquals("Services",
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(0).getTitle());
        assertEquals("2021-12-06 12:30:00",
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(0).getStart());
        assertEquals("2021-12-20 23:59:59",
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(0).getEnd());
        assertEquals("andy0432",
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(0).getParticipants().get(0).getId());
        assertEquals("lone9242",
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(0).getParticipants().get(1).getId());
        assertEquals(false,
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(0).isCompleted());

        assertEquals(100.00,
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(1).getEstimatedWorkHours());
        assertEquals("Models",
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(1).getTitle());
        assertEquals("2021-12-06 12:30:00",
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(1).getStart());
        assertEquals("2021-12-20 23:59:59",
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(1).getEnd());
        assertEquals("ande0137",
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(1).getParticipants().get(0).getId());
        assertEquals(false,
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(1).isCompleted());

        assertEquals(100.00,
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(2).getEstimatedWorkHours());
        assertEquals("Controller",
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(2).getTitle());
        assertEquals("2021-12-07 12:30:00",
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(2).getStart());
        assertEquals("2021-12-20 23:59:59",
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(2).getEnd());
        assertEquals("ande0137",
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(2).getParticipants().get(0).getId());
        assertEquals(false,
                appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(2).isCompleted());

        assertEquals(100.00,
                appdevProject.getPhases().get(1).getAssignments().get(2).getTasks().get(0).getEstimatedWorkHours());
        assertEquals("HTMLs and CSSs",
                appdevProject.getPhases().get(1).getAssignments().get(2).getTasks().get(0).getTitle());
        assertEquals("2021-12-07 12:30:00",
                appdevProject.getPhases().get(1).getAssignments().get(2).getTasks().get(0).getStart());
        assertEquals("2021-12-20 23:59:59",
                appdevProject.getPhases().get(1).getAssignments().get(2).getTasks().get(0).getEnd());
        assertEquals("ande0137",
                appdevProject.getPhases().get(1).getAssignments().get(2).getTasks().get(0).getParticipants().get(0).getId());
        assertEquals(false,
                appdevProject.getPhases().get(1).getAssignments().get(2).getTasks().get(0).isCompleted());

        assertEquals(20.15,
                appdevProject.getPhases().get(2).getAssignments().get(0).getTasks().get(0).getEstimatedWorkHours());
        assertEquals("Interviews",
                appdevProject.getPhases().get(2).getAssignments().get(0).getTasks().get(0).getTitle());
        assertEquals("2021-12-21 12:30:00",
                appdevProject.getPhases().get(2).getAssignments().get(0).getTasks().get(0).getStart());
        assertEquals("2022-01-04 23:59:59",
                appdevProject.getPhases().get(2).getAssignments().get(0).getTasks().get(0).getEnd());
        assertEquals("andy0432",
                appdevProject.getPhases().get(2).getAssignments().get(0).getTasks().get(0).getParticipants().get(0).getId());
        assertEquals(false,
                appdevProject.getPhases().get(2).getAssignments().get(0).getTasks().get(0).isCompleted());

        assertEquals(150.00,
                appdevProject.getPhases().get(2).getAssignments().get(0).getTasks().get(1).getEstimatedWorkHours());
        assertEquals("Commercials",
                appdevProject.getPhases().get(2).getAssignments().get(0).getTasks().get(1).getTitle());
        assertEquals("2021-12-21 12:30:00",
                appdevProject.getPhases().get(2).getAssignments().get(0).getTasks().get(1).getStart());
        assertEquals("2022-01-04 23:59:59",
                appdevProject.getPhases().get(2).getAssignments().get(0).getTasks().get(1).getEnd());
        assertEquals("andy0432",
                appdevProject.getPhases().get(2).getAssignments().get(0).getTasks().get(1).getParticipants().get(0).getId());
        assertEquals(false,
                appdevProject.getPhases().get(2).getAssignments().get(0).getTasks().get(1).isCompleted());

        assertEquals(150.00,
                appdevProject.getPhases().get(2).getAssignments().get(1).getTasks().get(0).getEstimatedWorkHours());
        assertEquals("Finnish Product",
                appdevProject.getPhases().get(2).getAssignments().get(1).getTasks().get(0).getTitle());
        assertEquals("2021-12-21 12:30:00",
                appdevProject.getPhases().get(2).getAssignments().get(1).getTasks().get(0).getStart());
        assertEquals("2022-01-04 23:59:59",
                appdevProject.getPhases().get(2).getAssignments().get(1).getTasks().get(0).getEnd());
        assertEquals("lone9242",
                appdevProject.getPhases().get(2).getAssignments().get(1).getTasks().get(0).getParticipants().get(0).getId());
        assertEquals("ande0137",
                appdevProject.getPhases().get(2).getAssignments().get(1).getTasks().get(0).getParticipants().get(1).getId());
        assertEquals(false,
                appdevProject.getPhases().get(2).getAssignments().get(1).getTasks().get(0).isCompleted());

        assertEquals(150.00,
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getEstimatedWorkHours());
        assertEquals("Search CVs",
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getTitle());
        assertEquals("2021-12-21 12:30:00",
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getStart());
        assertEquals("2022-12-21 12:30:00",
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getEnd());
        assertEquals("cind2352",
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getParticipants().get(0).getId());
        assertEquals(false,
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).isCompleted());

        assertEquals(150.00,
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).getEstimatedWorkHours());
        assertEquals("Setup interviews",
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).getTitle());
        assertEquals("2021-12-21 12:30:00",
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).getStart());
        assertEquals("2022-12-21 12:30:00",
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).getEnd());
        assertEquals("jame4235",
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).getParticipants().get(0).getId());
        assertEquals(false,
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).isCompleted());

        assertEquals(150.00,
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(1).getTasks().get(0).getEstimatedWorkHours());
        assertEquals("Do Interviews",
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(1).getTasks().get(0).getTitle());
        assertEquals("2021-12-21 12:30:00",
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(1).getTasks().get(0).getStart());
        assertEquals("2022-12-21 12:30:00",
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(1).getTasks().get(0).getEnd());
        assertEquals("cind2352",
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(1).getTasks().get(0).getParticipants().get(0).getId());
        assertEquals("jame4235",
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(1).getTasks().get(0).getParticipants().get(1).getId());
        assertEquals(false,
                findNewEmployeesProject.getPhases().get(0).getAssignments().get(1).getTasks().get(0).isCompleted());

        assertEquals(150.00,
                AdvertisingProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getEstimatedWorkHours());
        assertEquals("Manufactor Commercials",
                AdvertisingProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getTitle());
        assertEquals("2021-12-21 12:30:00",
                AdvertisingProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getStart());
        assertEquals("2022-12-21 12:30:00",
                AdvertisingProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getEnd());
        assertEquals("andy0432",
                AdvertisingProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getParticipants().get(0).getId());
        assertEquals("jame4235",
                AdvertisingProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getParticipants().get(1).getId());
        assertEquals(false,
                AdvertisingProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).isCompleted());

        assertEquals(150.00,
                AdvertisingProject.getPhases().get(1).getAssignments().get(0).getTasks().get(0).getEstimatedWorkHours());
        assertEquals("Send Commercials",
                AdvertisingProject.getPhases().get(1).getAssignments().get(0).getTasks().get(0).getTitle());
        assertEquals("2021-12-21 12:30:00",
                AdvertisingProject.getPhases().get(1).getAssignments().get(1).getTasks().get(0).getStart());
        assertEquals("2022-12-21 12:30:00",
                AdvertisingProject.getPhases().get(1).getAssignments().get(0).getTasks().get(0).getEnd());
        assertEquals("cind2352",
                AdvertisingProject.getPhases().get(1).getAssignments().get(0).getTasks().get(0).getParticipants().get(0).getId());
        assertEquals(false,
                AdvertisingProject.getPhases().get(1).getAssignments().get(0).getTasks().get(0).isCompleted());

        // Assignments
        assertEquals("Brainstorming", appdevProject.getPhases().get(0).getAssignments().get(0).getTitle());
        assertEquals("2021-10-15 12:30:00", appdevProject.getPhases().get(0).getAssignments().get(0).getStart());
        assertEquals("2021-11-14 23:59:59", appdevProject.getPhases().get(0).getAssignments().get(0).getEnd());
        assertEquals("Innovate Concepts", appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getTitle());
        assertEquals("Discuss Possible Clients", appdevProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).getTitle());
        assertEquals(false, appdevProject.getPhases().get(0).getAssignments().get(0).isCompleted());

        assertEquals("Database", appdevProject.getPhases().get(1).getAssignments().get(0).getTitle());
        assertEquals("2021-11-15 12:30:00", appdevProject.getPhases().get(1).getAssignments().get(0).getStart());
        assertEquals("2021-12-05 23:59:59", appdevProject.getPhases().get(1).getAssignments().get(0).getEnd());
        assertEquals("Repositories", appdevProject.getPhases().get(1).getAssignments().get(0).getTasks().get(0).getTitle());
        assertEquals(false, appdevProject.getPhases().get(1).getAssignments().get(0).isCompleted());

        assertEquals("Buiness Layer", appdevProject.getPhases().get(1).getAssignments().get(1).getTitle());
        assertEquals("2021-12-06 12:30:00", appdevProject.getPhases().get(1).getAssignments().get(1).getStart());
        assertEquals("2021-12-20 23:59:59", appdevProject.getPhases().get(1).getAssignments().get(1).getEnd());
        assertEquals("Services", appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(0).getTitle());
        assertEquals("Models", appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(1).getTitle());
        assertEquals("Controller", appdevProject.getPhases().get(1).getAssignments().get(1).getTasks().get(2).getTitle());
        assertEquals(false, appdevProject.getPhases().get(1).getAssignments().get(1).isCompleted());

        assertEquals("View", appdevProject.getPhases().get(1).getAssignments().get(2).getTitle());
        assertEquals("2021-12-07 12:30:00", appdevProject.getPhases().get(1).getAssignments().get(2).getStart());
        assertEquals("2021-12-20 23:59:59", appdevProject.getPhases().get(1).getAssignments().get(2).getEnd());
        assertEquals("HTMLs and CSSs", appdevProject.getPhases().get(1).getAssignments().get(2).getTasks().get(0).getTitle());
        assertEquals(false, appdevProject.getPhases().get(1).getAssignments().get(2).isCompleted());

        assertEquals("Promote Product", appdevProject.getPhases().get(2).getAssignments().get(0).getTitle());
        assertEquals("2021-12-21 12:30:00", appdevProject.getPhases().get(2).getAssignments().get(0).getStart());
        assertEquals("2022-01-04 23:59:59", appdevProject.getPhases().get(2).getAssignments().get(0).getEnd());
        assertEquals("Interviews", appdevProject.getPhases().get(2).getAssignments().get(0).getTasks().get(0).getTitle());
        assertEquals("Commercials", appdevProject.getPhases().get(2).getAssignments().get(0).getTasks().get(1).getTitle());
        assertEquals(false, appdevProject.getPhases().get(2).getAssignments().get(0).isCompleted());

        assertEquals("Releasing", appdevProject.getPhases().get(2).getAssignments().get(1).getTitle());
        assertEquals("2021-12-21 12:30:00", appdevProject.getPhases().get(2).getAssignments().get(1).getStart());
        assertEquals("2022-01-04 23:59:59", appdevProject.getPhases().get(2).getAssignments().get(1).getEnd());
        assertEquals("Finnish Product", appdevProject.getPhases().get(2).getAssignments().get(1).getTasks().get(0).getTitle());
        assertEquals(false, appdevProject.getPhases().get(2).getAssignments().get(1).isCompleted());

        assertEquals("Arrange Interviews", findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTitle());
        assertEquals("2021-12-21 12:30:00", findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getStart());
        assertEquals("2022-12-21 12:30:00", findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getEnd());
        assertEquals("Search CVs", findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getTitle());
        assertEquals("Setup interviews", findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).getTasks().get(1).getTitle());
        assertEquals(false, findNewEmployeesProject.getPhases().get(0).getAssignments().get(0).isCompleted());

        assertEquals("Interact in Interviews", findNewEmployeesProject.getPhases().get(0).getAssignments().get(1).getTitle());
        assertEquals("2021-12-21 12:30:00", findNewEmployeesProject.getPhases().get(0).getAssignments().get(1).getStart());
        assertEquals("2022-12-21 12:30:00", findNewEmployeesProject.getPhases().get(0).getAssignments().get(1).getEnd());
        assertEquals("Do Interviews", findNewEmployeesProject.getPhases().get(0).getAssignments().get(1).getTasks().get(0).getTitle());
        assertEquals(false, findNewEmployeesProject.getPhases().get(0).getAssignments().get(1).isCompleted());

        assertEquals("Develop", AdvertisingProject.getPhases().get(0).getAssignments().get(0).getTitle());
        assertEquals("2021-12-21 12:30:00", AdvertisingProject.getPhases().get(0).getAssignments().get(0).getStart());
        assertEquals("2022-12-21 12:30:00", AdvertisingProject.getPhases().get(0).getAssignments().get(0).getEnd());
        assertEquals("Manufactor Commercials", AdvertisingProject.getPhases().get(0).getAssignments().get(0).getTasks().get(0).getTitle());
        assertEquals(false, AdvertisingProject.getPhases().get(0).getAssignments().get(0).isCompleted());

        assertEquals("Put Commercials in action", AdvertisingProject.getPhases().get(1).getAssignments().get(0).getTitle());
        assertEquals("2021-12-21 12:30:00", AdvertisingProject.getPhases().get(1).getAssignments().get(0).getStart());
        assertEquals("2022-12-21 12:30:00", AdvertisingProject.getPhases().get(1).getAssignments().get(0).getEnd());
        assertEquals("Send Commercials", AdvertisingProject.getPhases().get(1).getAssignments().get(0).getTasks().get(0).getTitle());
        assertEquals(false, AdvertisingProject.getPhases().get(1).getAssignments().get(0).isCompleted());

        //Projektets participants værdier
        assertEquals("Andy Boss", appdevProject.getParticipants().get("andy0432").getName());
        assertEquals("Manager", appdevProject.getParticipants().get("andy0432").getPosition());
        assertEquals("andy0432", appdevProject.getParticipants().get("andy0432").getId());
        assertEquals("OSLO", appdevProject.getParticipants().get("andy0432").getDepartment().getDepName());
        assertEquals(2, appdevProject.getParticipants().get("andy0432").getDepartment().getDepartmentNo());
        assertEquals("erAsD14d", appdevProject.getParticipants().get("andy0432").getPassword());

        assertEquals("Lone Therkilsen", appdevProject.getParticipants().get("lone9242").getName());
        assertEquals("Developer", appdevProject.getParticipants().get("lone9242").getPosition());
        assertEquals("lone9242", appdevProject.getParticipants().get("lone9242").getId());
        assertEquals("OSLO", appdevProject.getParticipants().get("lone9242").getDepartment().getDepName());
        assertEquals(2, appdevProject.getParticipants().get("lone9242").getDepartment().getDepartmentNo());
        assertEquals("qscqsc*21", appdevProject.getParticipants().get("lone9242").getPassword());

        assertEquals("Anders Jensen", appdevProject.getParticipants().get("ande0137").getName());
        assertEquals("Developer", appdevProject.getParticipants().get("ande0137").getPosition());
        assertEquals("ande0137", appdevProject.getParticipants().get("ande0137").getId());
        assertEquals("NEW YORK", appdevProject.getParticipants().get("ande0137").getDepartment().getDepName());
        assertEquals(3, appdevProject.getParticipants().get("ande0137").getDepartment().getDepartmentNo());
        assertEquals("twe@faf2", appdevProject.getParticipants().get("ande0137").getPassword());

        assertEquals("Cindy Jenniferson", findNewEmployeesProject.getParticipants().get("cind2352").getName());
        assertEquals("Manager", findNewEmployeesProject.getParticipants().get("cind2352").getPosition());
        assertEquals("cind2352", findNewEmployeesProject.getParticipants().get("cind2352").getId());
        assertEquals("OSLO", findNewEmployeesProject.getParticipants().get("cind2352").getDepartment().getDepName());
        assertEquals(2, findNewEmployeesProject.getParticipants().get("cind2352").getDepartment().getDepartmentNo());
        assertEquals("4dasf24$", findNewEmployeesProject.getParticipants().get("cind2352").getPassword());

        assertEquals("James Jamerson", findNewEmployeesProject.getParticipants().get("jame4235").getName());
        assertEquals("Accountant", findNewEmployeesProject.getParticipants().get("jame4235").getPosition());
        assertEquals("jame4235", findNewEmployeesProject.getParticipants().get("jame4235").getId());
        assertEquals("LOS ANGELES", findNewEmployeesProject.getParticipants().get("jame4235").getDepartment().getDepName());
        assertEquals(5, findNewEmployeesProject.getParticipants().get("jame4235").getDepartment().getDepartmentNo());
        assertEquals("@cds765x", findNewEmployeesProject.getParticipants().get("jame4235").getPassword());

        assertEquals("Andy Boss", AdvertisingProject.getParticipants().get("andy0432").getName());
        assertEquals("Manager", AdvertisingProject.getParticipants().get("andy0432").getPosition());
        assertEquals("andy0432", AdvertisingProject.getParticipants().get("andy0432").getId());
        assertEquals("OSLO", AdvertisingProject.getParticipants().get("andy0432").getDepartment().getDepName());
        assertEquals(2, AdvertisingProject.getParticipants().get("andy0432").getDepartment().getDepartmentNo());
        assertEquals("erAsD14d", AdvertisingProject.getParticipants().get("andy0432").getPassword());

        assertEquals("Cindy Jenniferson", AdvertisingProject.getParticipants().get("cind2352").getName());
        assertEquals("Manager", AdvertisingProject.getParticipants().get("cind2352").getPosition());
        assertEquals("cind2352", AdvertisingProject.getParticipants().get("cind2352").getId());
        assertEquals("OSLO", AdvertisingProject.getParticipants().get("cind2352").getDepartment().getDepName());
        assertEquals(2, AdvertisingProject.getParticipants().get("cind2352").getDepartment().getDepartmentNo());
        assertEquals("4dasf24$", AdvertisingProject.getParticipants().get("cind2352").getPassword());

        assertEquals("James Jamerson", AdvertisingProject.getParticipants().get("jame4235").getName());
        assertEquals("Accountant", AdvertisingProject.getParticipants().get("jame4235").getPosition());
        assertEquals("jame4235", AdvertisingProject.getParticipants().get("jame4235").getId());
        assertEquals("LOS ANGELES", AdvertisingProject.getParticipants().get("jame4235").getDepartment().getDepName());
        assertEquals(5, AdvertisingProject.getParticipants().get("jame4235").getDepartment().getDepartmentNo());
        assertEquals("@cds765x", AdvertisingProject.getParticipants().get("jame4235").getPassword());

        //Projektets projectmanagers værdier
        assertEquals("Andy Boss", appdevProject.getProjectManager().getName());
        assertEquals("Manager", appdevProject.getProjectManager().getPosition());
        assertEquals("andy0432", appdevProject.getProjectManager().getId());
        assertEquals("OSLO", appdevProject.getProjectManager().getDepartment().getDepName());
        assertEquals(2, appdevProject.getProjectManager().getDepartment().getDepartmentNo());
        assertEquals("erAsD14d", appdevProject.getProjectManager().getPassword());

        assertEquals("Cindy Jenniferson", AdvertisingProject.getProjectManager().getName());
        assertEquals("Manager", AdvertisingProject.getProjectManager().getPosition());
        assertEquals("cind2352", AdvertisingProject.getProjectManager().getId());
        assertEquals("OSLO", AdvertisingProject.getProjectManager().getDepartment().getDepName());
        assertEquals(2, AdvertisingProject.getProjectManager().getDepartment().getDepartmentNo());
        assertEquals("4dasf24$", AdvertisingProject.getProjectManager().getPassword());

        assertEquals("Cindy Jenniferson", findNewEmployeesProject.getProjectManager().getName());
        assertEquals("Manager", findNewEmployeesProject.getProjectManager().getPosition());
        assertEquals("cind2352", findNewEmployeesProject.getProjectManager().getId());
        assertEquals("OSLO", findNewEmployeesProject.getProjectManager().getDepartment().getDepName());
        assertEquals(2, findNewEmployeesProject.getProjectManager().getDepartment().getDepartmentNo());
        assertEquals("4dasf24$", findNewEmployeesProject.getProjectManager().getPassword());

    }

    // Only tests title since the method getProject is being fully tested through
    @ParameterizedTest
    @CsvSource(value = {"lone9242|Appdev", "jame4235|Find New Employees_Advertising", "ande0137|Appdev"}, delimiter = '|')
    public void getProjectsTest(String userId, String expected) {

        //Act
        ArrayList<Project> actual = projectCreator.getProjects(userId);
        String [] expectations = expected.split("_");

        //Assert
        for (int i = 0; i < expectations.length; i++) {
            assertEquals(actual.get(i).getTitle(),expectations[i]);
        }
    }


    @ParameterizedTest
    @CsvSource(value = {"Appdev|Extra|1", "Appdev|Extra|2", "Advertising|SuperPhase|1"}, delimiter = '|')
    public void createPhaseTest(String projectTitle,String phaseTitle, String newestPhaseNo) {

        //Arrange
        ProjectRepository proRepo = new ProjectRepository();
        String phaseTitleFromRepo = "";

        //Phase createdPhase = projectCreator.createPhase(projectTitle,"Temp");

        //Act
        Phase updatedPhase = new ProjectEditor().updatePhase(phaseTitle,
                projectTitle + " NEW PHASE " +
                        newestPhaseNo, projectTitle);

        try {
           phaseTitleFromRepo = proRepo.findPhase(phaseTitle, projectTitle).getString("title");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        //Assert
        //assertEquals(projectTitle + " - NEW PHASE " + newestPhaseNo, proRepo.);
        assertEquals(phaseTitleFromRepo, updatedPhase.getTitle());
    }


    @Test
    public void getPhaseTest() {
        Phase phase = projectCreator.getPhase("Interviews","Find New Employees");

        assertEquals("Interviews",phase.getTitle());
        assertEquals("Arrange Interviews",phase.getAssignments().get(0).getTitle());
        assertEquals("2021-12-21 12:30:00",phase.getAssignments().get(0).getStart());
        assertEquals("2022-12-21 12:30:00",phase.getAssignments().get(0).getEnd());

        assertEquals("Interact in Interviews",phase.getAssignments().get(1).getTitle());
        assertEquals("2021-12-21 12:30:00",phase.getAssignments().get(1).getStart());
        assertEquals("2022-12-21 12:30:00",phase.getAssignments().get(1).getEnd());
    }

    @ParameterizedTest
    @CsvSource(value = {"Brainstorming|Planning|2021-10-15 12:30:00_2021-11-14 23:59:59_Innovate Concepts_2021-10-15 12:30:00_2021-10-28 23:59:59_300.0_",
            "Buiness Layer|Building Product|2021-12-06 12:30:00_2021-12-20 23:59:59_Services_2021-12-06 12:30:00_2021-12-20 23:59:59_100.0_Models_2021-12-06 12:30:00_2021-12-20 23:59:59_100.0_",
            "Put Commercials in action|Deliver Commercials|2021-12-21 12:30:00_2022-12-21 12:30:00_Send Commercials_2021-12-21 12:30:00_2022-12-21 12:30:00_150.0_"}, delimiter = '|')
    public void getAssignmentTest(String assignmentTitle,String phaseTitle,String expected) {
        // Arrange
        String[] expectations = expected.split("_");
        for (int i = 0;i<expectations.length;i++) {
            System.out.println(expectations[i]);
        }
        // Act
        Assignment actual = projectCreator.getAssignment(assignmentTitle, phaseTitle);

        // Assert
        assertEquals(assignmentTitle,actual.getTitle());
        assertEquals(expectations[0],actual.getStart());
        assertEquals(expectations[1],actual.getEnd());
        for (int i = 0; i < actual.getTasks().size()-1;i++) {
            if(actual.getTasks().size()<2+(i*4)) {
                assertEquals(expectations[2+(i*4)],actual.getTasks().get(i).getTitle());
                assertEquals(expectations[3+(i*4)],actual.getTasks().get(i).getStart());
                assertEquals(expectations[4+(i*4)],actual.getTasks().get(i).getEnd());
                assertEquals(expectations[5+(i*4)],String.valueOf(actual.getTasks().get(i).getEstimatedWorkHours()));
            }
        }
    }

    @Test
    public void getTaskTest() {

        // Act
        Task actual = projectCreator.getTask("Discuss Possible Clients","2021-10-29 12:30:00","2021-11-14 23:59:59");

        // Assert
        assertEquals("Discuss Possible Clients",actual.getTitle());
        assertEquals("2021-10-29 12:30:00",actual.getStart());
        assertEquals("2021-11-14 23:59:59",actual.getEnd());
    }

}