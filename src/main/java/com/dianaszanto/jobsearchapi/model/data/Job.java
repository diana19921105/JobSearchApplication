package com.dianaszanto.jobsearchapi.model.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.net.URL;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @Size(max = 50, message = "Title is too long!")
    private String title;

    @Column(length = 50)
    @Size(max = 50, message = "Location is too long!")
    private String location;

    private URL url;

    public Job(String title, String location, URL url) {
        this.title = title;
        this.location = location;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Job)) return false;

        Job job = (Job) o;

        return new EqualsBuilder().append(id, job.id).append(title, job.title).append(location, job.location).append(url, job.url).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(title).append(location).append(url).toHashCode();
    }
}
