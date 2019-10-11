package nl.dijkrosoft.validation;

import nl.dijkrosoft.validation.groups.GroupOne;

import javax.validation.constraints.*;
import java.util.Date;

public class Bike {

    String make;

    Double price;

    Date productionDate;

    Date recycleDate;

    @NotNull(groups = GroupOne.class)
    @Size(min = 3, max = 5)
    public String getMake() {
        return make;
    }

    @NotNull
    @Digits(integer = 3, fraction = 2)
    public Double getPrice() {
        return price;
    }

    @Past
    public Date getProductionDate() {
        return productionDate;
    }

    @Future
    public Date getRecycleDate() {
        return recycleDate;
    }
}
