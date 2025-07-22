package br.com.davisoares.designpatternsdio.utils;

import br.com.davisoares.designpatternsdio.dto.TaskRequestDTO;
import br.com.davisoares.designpatternsdio.model.Task;

public class Utils {
    public static Task ConvertToModel(TaskRequestDTO task) {
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setCompleted(task.isCompleted());
        return newTask;
    }
}
