package tqs.airQuality.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "continents")
public class Continent {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    public Continent() {
        super();
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Continent(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
