package model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String model;
    private int series;
    private String plateNumber;

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getSeries() {
        return series;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public Car() {
    }

    public Car(String model, int series, String plateNumber) {
        this.model = model;
        this.series = series;
        this.plateNumber = plateNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Car{id=").append(id).append(", model=").append(model)
                .append(", series=").append(series)
                .append(", plate=").append(plateNumber).append("}");
        return sb.toString();
    }

}
