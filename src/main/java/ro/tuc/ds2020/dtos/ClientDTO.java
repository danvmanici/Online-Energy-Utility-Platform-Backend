package ro.tuc.ds2020.dtos;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class ClientDTO {

    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private Date birthdate;

    public ClientDTO() {
    }

    public ClientDTO(String name, String address, Date birthdate) {
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
    }

    public ClientDTO(UUID id, String name, String address, Date birthdate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientDTO)) return false;
        ClientDTO clientDTO = (ClientDTO) o;
        return Objects.equals(getId(), clientDTO.getId()) && Objects.equals(getName(), clientDTO.getName()) && Objects.equals(getAddress(), clientDTO.getAddress()) && Objects.equals(getBirthdate(), clientDTO.getBirthdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getBirthdate());
    }
}
