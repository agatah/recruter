package pl.unity.vgp.recruter.dataImport;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/import")
public class ImportController {

    private final ImportDataService importDataService;

    @Autowired
    public ImportController(ImportDataService importDataService){
        this.importDataService = importDataService;
    }

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> importDataFromPath(@RequestBody String path) {
        if(path.isEmpty()){
            return new ResponseEntity<>("File path can't be empty", HttpStatus.BAD_REQUEST);
        }

        try {
            importDataService.importDataFromFile(path);

        } catch (FileNotFoundException e) {
            return new ResponseEntity<>("File can't be found under path: " + path, HttpStatus.NOT_FOUND);

        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Error occurred during deserialization", HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (IOException e){
            return new ResponseEntity<>("Error occurred during reading a file", HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<>("File successfully imported", HttpStatus.OK);

    }
}
