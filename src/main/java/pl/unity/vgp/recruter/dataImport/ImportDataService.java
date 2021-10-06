package pl.unity.vgp.recruter.dataImport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.unity.vgp.recruter.domain.model.Client;
import pl.unity.vgp.recruter.domain.model.Employee;
import pl.unity.vgp.recruter.domain.model.Project;
import pl.unity.vgp.recruter.domain.service.ClientService;
import pl.unity.vgp.recruter.domain.service.EmployeeService;
import pl.unity.vgp.recruter.domain.service.ProjectService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportDataService {

    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final ProjectService projectService;

    @Autowired
    public ImportDataService(ClientService clientService, EmployeeService employeeService,
                             ProjectService projectService){

        this.clientService = clientService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    public void importDataFromFile(String path) throws IOException {
        File file = new File(path);
        String text;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            text = br.lines().collect(Collectors.joining(System.lineSeparator()));
        }

        String filename = file.getName();
        String extension = filename.substring(filename.lastIndexOf('.') + 1);

        switch (extension) {
            case "xml":
                deserializeXml(text);
                break;
            case "json":
                deserializeJson(text);
                break;
            case "csv":
                deserializeCsv(text);
                break;
        }
    }

    public void deserializeXml(String xml) throws JsonProcessingException {
        if(xml.endsWith("</projects>")){
            deserializeProjects(xml);
        } else {
            deserializeClients(xml);
        }
    }

    public void deserializeClients(String xml) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        CollectionType list = xmlMapper
                .getTypeFactory()
                .constructCollectionType(ArrayList.class, Client.class);

        List<Client> clients
                = xmlMapper.readValue(xml, list);

        clientService.saveClientsData(clients);
    }

    public void deserializeProjects(String xml) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        CollectionType list = xmlMapper
                .getTypeFactory()
                .constructCollectionType(ArrayList.class, Project.class);

        List<Project> projects
                = xmlMapper.readValue(xml, list);

        projectService.saveProjectsData(projects);
    }

    public void deserializeJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType list = objectMapper
                .getTypeFactory()
                .constructCollectionType(ArrayList.class, Employee.class);

        List<Employee> employees = objectMapper.readValue(json, list);

        employeeService.saveEmployeesData(employees);
    }

    public void deserializeCsv(String csv) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema
                .emptySchema()
                .withHeader()
                .withColumnSeparator(';');

        MappingIterator<Employee> iterator = csvMapper
                .readerWithTypedSchemaFor(Employee.class)
                .with(schema)
                .readValues(csv);

        List<Employee> employees = iterator.readAll();

        employeeService.saveEmployeesWithAddress(employees);
    }
}
