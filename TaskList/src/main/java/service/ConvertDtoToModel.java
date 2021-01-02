package service;

import dto.TaskDTO;
import model.Task;

public class ConvertDtoToModel
{
    public static Task convertDtoToModel(TaskDTO taskDTO) {
        return Task.builder().
                id(taskDTO.getId())
                .name(taskDTO.getName())
                .dtOpen(taskDTO.getDtOpen())
                .dtClose(taskDTO.getDtClose())
                .isActual(taskDTO.getIsActual())
                .build();
    }
}
