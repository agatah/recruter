package pl.unity.vgp.recruter.dataImport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import pl.unity.vgp.recruter.domain.model.Client;
import pl.unity.vgp.recruter.domain.model.Employee;
import pl.unity.vgp.recruter.domain.model.Project;

import java.io.IOException;
import java.util.List;

class DeserializeEntitiesTest {


    @Test
    public void shouldDeserializeClient() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        Client client
                = xmlMapper.readValue("<client>\n" +
                "\t\t<name>Tea House of America</name>\n" +
                "\t\t<location>US</location>\n" +
                "\t\t<contact>\n" +
                "\t\t\t<email>office@thom.us</email>\n" +
                "\t\t\t<phone>+1 1123 456 890</phone>\n" +
                "\t\t</contact>\n" +
                "\t\t<address>\n" +
                "\t\t\t<street>1th street</street>\n" +
                "\t\t\t<streetNumber>11</streetNumber>\n" +
                "\t\t\t<zipCode>45698</zipCode>\n" +
                "\t\t\t<city>Boise</city>\n" +
                "\t\t</address>\n" +
                "\t</client>", Client.class);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(client.getName()).isEqualTo("Tea House of America");
        softly.assertThat(client.getAddress().getCity()).isEqualTo("Boise");
        softly.assertThat(client.getContact().getEmail()).isEqualTo("office@thom.us");

        softly.assertAll();
    }

    @Test
    void shouldDeserializeProject() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        Project project = xmlMapper.readValue("<project>\n" +
                "\t\t<name>Tea shop</name>\n" +
                "\t\t<team>\n" +
                "\t\t\t<name>Tea team</name>\n" +
                "\t\t\t<code>TT</code>\n" +
                "\t\t\t<client>Tea House of America</client>\n" +
                "\t\t\t<manager>Krzystof Krawczyk</manager>\n" +
                "\t\t\t<teamMembers>\n" +
                "\t\t\t\t<member>\n" +
                "\t\t\t\t\t<name>Janusz Nosacz</name>\n" +
                "\t\t\t\t\t<reservation>50%</reservation>\n" +
                "\t\t\t\t</member>\n" +
                "\t\t\t\t<member>\n" +
                "\t\t\t\t\t<name>Joanna Ciemna</name>\n" +
                "\t\t\t\t\t<reservation>100%</reservation>\n" +
                "\t\t\t\t</member>\n" +
                "\t\t\t\t<member>\n" +
                "\t\t\t\t\t<name>Lech Tusk</name>\n" +
                "\t\t\t\t\t<reservation>100%</reservation>\n" +
                "\t\t\t\t</member>\n" +
                "\t\t\t\t<member>\n" +
                "\t\t\t\t\t<name>Grażyna Boska</name>\n" +
                "\t\t\t\t\t<reservation>100%</reservation>\n" +
                "\t\t\t\t</member>\n" +
                "\t\t\t</teamMembers>\n" +
                "\t\t\t<methodology>SCRUM</methodology>\n" +
                "\t\t\t<MPK>TT-1234</MPK>\n" +
                "\t\t\t<technologies>\n" +
                "\t\t\t\t<technology>\n" +
                "\t\t\t\t\t<name>JAVA</name>\n" +
                "\t\t\t\t\t<percentage>70%</percentage>\n" +
                "\t\t\t\t\t<frameworks>\n" +
                "\t\t\t\t\t\t<framework>\n" +
                "\t\t\t\t\t\t\t<name>SPRING-BOOT</name>\n" +
                "\t\t\t\t\t\t\t<name>HIBERNATE</name>\n" +
                "\t\t\t\t\t\t</framework>\n" +
                "\t\t\t\t\t</frameworks>\n" +
                "\t\t\t\t\t<tools>\n" +
                "\t\t\t\t\t\t<name>MYSQL</name>\n" +
                "\t\t\t\t\t\t<name>DOCKER</name>\n" +
                "\t\t\t\t\t\t<name>XML</name>\n" +
                "\t\t\t\t\t</tools>\n" +
                "\t\t\t\t</technology>\n" +
                "\t\t\t\t<technology>\n" +
                "\t\t\t\t\t<name>REACT</name>\n" +
                "\t\t\t\t\t<percentage>30%</percentage>\n" +
                "\t\t\t\t</technology>\n" +
                "\t\t\t</technologies>\n" +
                "\t\t\t<fees>\n" +
                "\t\t\t\t<hourly>120 PLN</hourly>\n" +
                "\t\t\t\t<serviceFee>\n" +
                "\t\t\t\t\t<monthly>20000 PLN</monthly>\n" +
                "\t\t\t\t</serviceFee>\n" +
                "\t\t\t</fees>\n" +
                "\t\t</team>\n" +
                "\t</project>", Project.class);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(project.getName()).isEqualTo("Tea shop");
        softly.assertThat(project.getTeam().getFees().getServiceFee().getMonthly()).isEqualTo("20000 PLN");
        softly.assertThat(project.getTeam().getTechnologies().get(0).getFrameworks().size()).isEqualTo(2);

        softly.assertAll();

    }

    @Test
    public void shouldDeserializeEmployee() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee = objectMapper.readValue("{\n" +
                "    \"name\": \"Janusz\",\n" +
                "    \"surname\": \"Nosacz\",\n" +
                "    \"empolyeeType\": \"DEVELOPER\",\n" +
                "    \"salary\": {\n" +
                "      \"brutto\": 5000,\n" +
                "      \"contractType\": \"CONTRACT\"\n" +
                "    },\n" +
                "    \"activeInAD\": true\n" +
                "  },", Employee.class);


        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(employee.getName()).isEqualTo("Janusz");
        softly.assertThat(employee.getSalary().getGross()).isEqualTo("5000");

        softly.assertAll();
    }

    @Test
    public void shouldDeserializeListOfEmployeeAddresses() throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema
                .emptySchema()
                .withHeader()
                .withColumnSeparator(';');

        MappingIterator<Employee> iterator = csvMapper
                .readerWithTypedSchemaFor(Employee.class)
                .with(schema)
                .readValues("name;surname;street;streetNumber;flatNumber;zipCode;city\n" +
                        "Janusz;Nosacz;Strzegomska;12;5;12-345;Wroclaw\n" +
                        "Krzysztof;Krawczyk;Pięknego rejsu;1;3;12-345;Łódz\n" +
                        "Joanna;Ciemna;Stosowa;1;3;324-12;Spalona\n" +
                        "Lech;Tusk;Tupolewa;2;;123-12;Brzozowa\n" +
                        "Grażyna;Boska;Lotnicza;13;1;54-091;Wrocław\n" +
                        "Mieczysław;Mgła;Ciemna;90;1;00-001;Niedziela\n" +
                        "Jan;Anderson;Królicza;1;2;11-234;Wrocław\n" +
                        "Marzena;Kowalska;Szwedzka;23;3;32-567;Wrocław\n" +
                        "Władysław;Nowak;Popowicka;134;4a;23-456;Wrocław\n" +
                        "Mariusz;Kicuch;Plac Jana z Kolna;23d;1a;23-567;Gdańsk\n" +
                        "Andrzej;Matrix;Jacksonowicka;5;;54-679;Wrocław\n" +
                        "Antoni;Skrzydło;Tajna;2;;43-345;Wrocław\n" +
                        "Borys;Szopa;Wiejska;4;33;45-980;Wrocław\n" +
                        "Angelika;Szopa;Wiejska;4;33;45-980;Wrocław\n" +
                        "Łukasz;Rabarbar;Rderstowa;24;133;67-899;Wrocław");

        List<Employee> employees = iterator.readAll();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(employees.get(0).getAddress().getCity()).isEqualTo("Wroclaw");
        softly.assertThat(employees.size()).isEqualTo(15);

        softly.assertAll();
    }
}