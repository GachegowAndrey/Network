package rest;
import dto.TaskDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ConvertDtoToModel;
import service.TaskService;

import javax.validation.constraints.Min;
import java.util.Map;

/**
 * Контроллер API менеджера опросов
 */
@RestController
@RequestMapping(value = "/api/v1/poll/")
@Api(value = "Poll Manager", description = "Api для работы с опросами")

public class TaskRESTApi
{
    private final TaskService taskService;

    @Autowired
    public TaskRESTApi(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ApiOperation(value = "Создание задачи", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Задача успешно создана"),
    })
    public ResponseEntity addPoll(@RequestBody TaskDTO taskDTO) {
        Task task = ConvertDtoToModel.convertDtoToModel(taskDTO);
        taskService.removeTask(task.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{idTask}")
    @ApiOperation(value = "Получение задачи", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Задача успешно найдена"),
            @ApiResponse(code = 500, message = "Задачи с таким id не существует"),
    })
    public ResponseEntity getTasks(
            @RequestParam(name = "page", required = false) @Min(0) Integer page,
            @RequestParam(name = "pageSize", required = false) @Min(1) Integer pageSize,
            @RequestParam(name = "sortBy", required = false) Map<String, Boolean> sortBy,
            @RequestParam(name = "filters", required = false) Map<String, Object> filters,
            @PathVariable Long idTask
    ) {
        if ((page == null || pageSize == null) && (sortBy == null || sortBy.isEmpty()) && (filters == null || filters.isEmpty())) {
            if (idTask == null) {
                return ResponseEntity.ok(taskService.getTasks());
            } else {
                return ResponseEntity.ok(taskService.getTask(idTask));
            }
        } else {
            return ResponseEntity.ok(taskService.getTasks(page, pageSize, sortBy, filters));
        }
    }

    @PutMapping
    @ApiOperation(value = "Обновление задачи", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Задача успешно найдена"),
            @ApiResponse(code = 500, message = "Задачи с таким id не существует")
    })
    public ResponseEntity<Task> updateTask(@RequestBody TaskDTO taskDTO) {
        Task task = ConvertDtoToModel.convertDtoToModel(taskDTO);
        taskService.updateTask(task);
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @DeleteMapping
    @ApiOperation(value = "Удаление задачи", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Задача успешно удалена"),
            @ApiResponse(code = 500, message = "Задачи с таким id не существует")
    })
    public ResponseEntity removeTask(@RequestParam Long id) {
        taskService.removeTask(id);
        return ResponseEntity.noContent().build();
    }

}
