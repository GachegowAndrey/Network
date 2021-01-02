package service;

import model.Task;

import java.util.List;
import java.util.Map;

/**
 * Сервис для работы с задачами
 */
public interface TaskService {

    /**
     * Добавить задачу
     *
     * @param task задача
     */
    void addTask(Task task);

    /**
     * Вернуть задачи
     *
     * @return список задач
     */
    List<Task> getTasks();

    /**
     * Вернуть задачу по id
     *
     * @param id id задачи
     * @return задач
     */
    Task getTask(Long id);

    /**
     * Вернуть задачи
     *
     * @return список задач
     */
    List<Task> getTasks(Integer page, Integer pageSize, Map<String, Boolean> sortBy, Map<String, Object> filters);


    /**
     * Обновить задачу
     *
     * @param task опрос
     * @return задачу
     */
    Task updateTask(Task task);


    /**
     * Удалить задачу
     *
     * @param id id задачи
     */
    void removeTask(Long id);
}
