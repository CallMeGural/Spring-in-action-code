package pl.tacocloud.tacos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placedAt;

    @NotEmpty(message = "Musisz podać imię i nazwisko")
    private String name;
    @NotEmpty(message = "Musisz podać nazwę ulicy oraz numer domu")
    private String street;
    @NotEmpty(message = "Musisz podać nazwę miejscowości")
    private String city;
    @NotEmpty(message = "Musisz podać nazwę województwa, w którym znajduje się podana miejscowość")
    private String state;
    @NotEmpty(message = "Musisz podać kod pocztowy miejscowości")
    private String zip;
    @CreditCardNumber(message = "To nie jest prawidłowy numer karty kredytowej")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([0-9][0-9])$",
            message = "Wartość musi być w formacie MM/RR")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Nieprawidłowy kod CVV")
    private String ccCVV;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();
    @ManyToOne
    private User orderedBy;

    public void addDesign(Taco taco) {
        tacos.add(taco);
    }

    @PrePersist
    void placedAt() {
        this.placedAt=new Date();
    }
}
