addEventListener("submit",addNewTask);
var tasks=[];

function addNewTask(event){
    event.preventDefault();
    var task = document.getElementById("task");
    tasks.push(task.value);
    task.value="";
    updateTasks();
}
function updateTasks(){
    var tasksDiv=document.getElementById("tasks");
    taskLines = generateList(tasks);
    tasksDiv.innerHTML = taskLines.join("\n");
}
function generateList(){
    taskLines=[]
    for (var i=0; i<tasks.length; i++){
        taskLine =`<button id="remove" class="remove" onclick="removeTask(${i})">  </button>` + "&nbsp; &nbsp;"+  tasks[i] + "<br> <br>";
        taskLines.push(taskLine);
    }
    return taskLines;
}
function removeTask(index){
    tasks.splice(index,1);
    updateTasks();
}
