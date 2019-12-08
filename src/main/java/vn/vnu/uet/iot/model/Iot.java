package vn.vnu.uet.iot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Iot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "t1")
    private String t1;

    @Column(name = "h")
    private String h;

    @Column(name = "t2")
    private String t2;

    @Column(name = "p")
    private String p;

    @Column(name = "created_on")
    private Long createdOn;
}
