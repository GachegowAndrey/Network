package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "TASK")
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Task
{


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * id задачи
     */
    @Id
    @Column(name = "TASK_ID")
    private Long id;

    /**
     * Имя задачи
     */
    @Column(name = "TASK_NAME")
    private String name;

    /**
     * Дата начала
     */
    @Column(name = "DT_OPEN")
    private Date dtOpen;

    /**
     * Дата окончания
     */
    @Column(name = "DT_CLOSE")
    private Date dtClose;

    /**
     * Актуальность
     */
    @Column(name = "IS_ACTUAL")
    private Boolean isActual;

    public Task() {
    }
}
