package com.dianaszanto.jobsearchapi.model.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Name can not be empty!")
    @Size(max = 100, message = "Name is too long!")
    private String name;

    @Id
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email can not be empty!")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                      + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "Email is invalid!")
    private String email;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type="uuid-char")
    @Column(name = "api_key")
    private UUID apiKey;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        return new EqualsBuilder()
                .append(name, client.name)
                .append(email, client.email)
                .append(apiKey, client.apiKey)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(email)
                .append(apiKey)
                .toHashCode();
    }
}
