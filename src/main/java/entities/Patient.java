package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Patient {
    private Long id;
    private String name;
    private Date dateNaissance;
    private int score;
    private  boolean malade;
}
