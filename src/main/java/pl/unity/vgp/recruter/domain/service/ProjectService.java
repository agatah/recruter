package pl.unity.vgp.recruter.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.unity.vgp.recruter.domain.model.*;
import pl.unity.vgp.recruter.domain.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final ToolRepository toolRepository;
    private final FrameworkRepository frameworkRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ClientRepository clientRepository,
                          EmployeeRepository employeeRepository, ToolRepository toolRepository,
                          FrameworkRepository frameworkRepository){

        this.projectRepository = projectRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.toolRepository = toolRepository;
        this.frameworkRepository = frameworkRepository;
    }

    public void saveProjectsData(Iterable<Project> projects){
        for(Project project: projects){

            // if client already exists in db
            if(project.getTeam().getClient() != null){
                Optional<Client> clientOpt = clientRepository.findByName(project.getTeam().getClient().getName());
                clientOpt.ifPresent(client -> project.getTeam().setClient(client));
            }

            // if manager employee exists in db
            Optional<Employee> managerEmployeeOpt = employeeRepository
                    .findEmployeeByNameAndSurname(project.getTeam().getManager().getName(),
                            project.getTeam().getManager().getSurname());

            if(managerEmployeeOpt.isPresent()){
                project.getTeam().setManager(managerEmployeeOpt.get());
                managerEmployeeOpt.get().addProject(project);

            } else {
                project.getTeam().getManager().addProject(project);
            }

            // if team member employee exists in db
            for(TeamMember teamMember: project.getTeam().getTeamMembers()){
                Optional<Employee> teamEmployeeOpt = employeeRepository
                        .findEmployeeByNameAndSurname(teamMember.getEmployee().getName(), teamMember.getEmployee().getSurname());

                if(teamEmployeeOpt.isPresent()){
                    teamMember.setEmployee(teamEmployeeOpt.get());
                    teamEmployeeOpt.get().addProject(project);

                } else {
                    teamMember.getEmployee().addProject(project);
                }
            }


            for(Technology technology: project.getTeam().getTechnologies()){

                // if tools exists in db
                List<Tool> tools = technology.getTools();
                List<Tool> toolsToSave = new ArrayList<>();

                for(Tool tool: tools){
                    Optional<Tool> toolOpt = toolRepository.findByName(tool.getName());

                    if(toolOpt.isPresent()){
                        toolsToSave.add(toolOpt.get());

                    } else {
                        toolsToSave.add(tool);
                    }
                }
                technology.setTools(toolsToSave);

                // if framework exists in db
                List<Framework> frameworks = technology.getFrameworks();
                List<Framework> frameworksToSave = new ArrayList<>();

                for(Framework framework: frameworks){
                    Optional<Framework> frameworkOpt = frameworkRepository.findFrameworkByName(framework.getName());

                    if(frameworkOpt.isPresent()){
                        frameworksToSave.add(frameworkOpt.get());

                    } else {
                        frameworksToSave.add(framework);
                    }
                }
                technology.setFrameworks(frameworksToSave);
            }

            projectRepository.save(project);
        }
    }

    public List<Project> exportProjects(){
        return projectRepository.findAll();
    }

    public boolean ifProjectExist(String projectName){
        return projectRepository.existsProjectByName(projectName);
    }

}
