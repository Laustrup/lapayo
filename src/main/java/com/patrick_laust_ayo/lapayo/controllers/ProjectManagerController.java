package com.patrick_laust_ayo.lapayo.controllers;

import com.patrick_laust_ayo.lapayo.models.Participant;
import com.patrick_laust_ayo.lapayo.models.Project;
import com.patrick_laust_ayo.lapayo.models.ProjectManager;
import com.patrick_laust_ayo.lapayo.services.ExceptionHandler;
import com.patrick_laust_ayo.lapayo.services.ProjectCreator;
import com.patrick_laust_ayo.lapayo.services.UserCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//Author Patrick
@Controller
public class  ProjectManagerController {

    private UserCreator userCreator = new UserCreator();
    private ExceptionHandler exceptionHandler = new ExceptionHandler();

    @GetMapping("/create_new_projectmanager")
    public String renderCreateProjectManager(){
        return "create_projectmanager.html";
    }

    @PostMapping("/create_projectmanager")
    public String createProjectManagerLogin(@RequestParam(name="username") String username,
                                            @RequestParam(name="password") String password,
                                            HttpServletRequest request, Model model){


        if (exceptionHandler.doesUserIdExist(username)) {
            model.addAttribute("Exception", "This username already exist. Please choose another.");
            return "create_projectmanager.html";
        }

        HttpSession session = request.getSession();

        String inputException = exceptionHandler.isLengthAllowedInDatabase(username,"username");

        if (!(inputException.equals("Input is allowed"))) {
            model.addAttribute("Exception",inputException);
            return "create_projectmanager.html";
        }

        inputException = exceptionHandler.isLengthAllowedInDatabase(password,"participant_password");
        if (!(inputException.equals("Input is allowed"))) {
            model.addAttribute("Exception",inputException);
            return "create_projectmanager.html";
        }

        ProjectManager projectManager = userCreator.createManager(username);
        session.setAttribute("username", username);
        session.setAttribute("projectmanager_password", password);
        session.setAttribute("projectManager", projectManager);

        model.addAttribute("projectManager",projectManager);

        return "redirect:/create_project/" + projectManager.getUsername();

    }

    @GetMapping("/projectmanager_login")
    public String renderProjectManagerLogin(){
        return "projectmanager_login";
    }

    @PostMapping("/allow_password")
    public String loginProjectManager(@RequestParam(name="manager_password") String password,
                                        @RequestParam(name="manager_username") String username,
                                      HttpServletRequest request,Model model) {

        ExceptionHandler handler = new ExceptionHandler();

        if (handler.allowLogin(username, password)) {

            HttpSession session = request.getSession();

            session.setAttribute("projectManager",userCreator.getProjectManager(username));
            session.setAttribute("participant",userCreator.getParticipant(username));

            return "redirect:/manager_dashboard/" + username;
        }
        else {
            model.addAttribute("Exception","Wrong username or password!");
            return "redirect:/projectmanager_login";
        }
    }

    @GetMapping("/manager_dashboard/{manager-id}")
    public String renderDashboard(@PathVariable("manager-id") String userId, Model model) {

        ProjectCreator projectCreator = new ProjectCreator();

        model.addAttribute("projectManager", userCreator.getProjectManager(userId));
        model.addAttribute("participant",userCreator.getParticipant(userId));
        model.addAttribute("projects", projectCreator.getProjects(userId));
        model.addAttribute("is_project_manager","true");

        return "dashboard";
    }

    @PostMapping("/add_participants")
    public String addParticipantsToProject(@RequestParam(name = "chosen_department") String departmentName,
                                           @RequestParam(name = "amount") int amount, HttpServletRequest request,
                                           Model model) {

        HttpSession session = request.getSession();
        String projectTitle = ((Project) session.getAttribute("project")).getTitle();

        userCreator.createParticipants("Enter user-ID",projectTitle,departmentName,amount);


        Participant participant = (Participant) session.getAttribute("participant");
        session.setAttribute("current_project","start");
        model.addAttribute("current_project", session.getAttribute("current_project"));

        return "redirect:/project_page-" + projectTitle + "/" + participant.getId();
    }
}
