package tqs.airquality.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String continent;

    @NotBlank
    private String flag;

    public Country() { super(); }

    public Country(@NotBlank String name, @NotBlank String continent, @NotBlank String flag) {
        this.name = name;
        this.continent = continent;
        this.flag = flag;
    }

    public Country(Long id, @NotBlank String name, @NotBlank String continent, @NotBlank String flag) {
        this.id = id;
        this.name = name;
        this.continent = continent;
        this.flag = flag;
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

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
