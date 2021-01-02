package dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Тело запроса задачи
 */
@Data
@Builder

public class TaskDTO
{
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

    public Date getDtOpen() {
        return dtOpen;
    }

    public void setDtOpen(Date dtOpen) {
        this.dtOpen = dtOpen;
    }

    public Date getDtClose() {
        return dtClose;
    }

    public void setDtClose(Date dtClose) {
        this.dtClose = dtClose;
    }

    public Boolean getIsActual() {
        return isActual;
    }

    public void setActual(Boolean actual) {
        isActual = actual;
    }

    /**
     * id задачи
     */
    private Long id;

    /**
     * Имя задачи
     */
    private String name;

    /**
     * Дата начала
     */
    private Date dtOpen;

    /**
     * Дата окончания
     */
    private Date dtClose;

    /**
     * Актуальность
     */
    private Boolean isActual;
}
