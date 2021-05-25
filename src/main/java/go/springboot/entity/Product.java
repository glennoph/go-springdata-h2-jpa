package go.springboot.entity;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

//@Data
@Getter
@Setter
@Jacksonized
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String type;
    private String category;
    private String description;
    private Double price;


}
