package pl.unity.vgp.recruter.dataExport.dto;

public class ReservationDto {

    private String projectName;
    private String percentage;

    public String getProjectName() {
        return projectName;
    }

    public ReservationDto setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public String getPercentage() {
        return percentage;
    }

    public ReservationDto setPercentage(String percentage) {
        this.percentage = percentage;
        return this;
    }
}
